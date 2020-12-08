- 내 정보 (MyInfo Page)
    - 비밀번호 변경
        - Spring Security를 염두하자.
        - passwordEncode가 적용된 비번은 어떻게 가져와야 하는가.
            - 현재 접속한 member는 Authentication 객체를 이용해 불러온다.
            - member의 userid(email)를 이용하여 repository에서 password를 불러온다.
        - 비교하고자 하는 비번과 member(현재 로그인한)의 비번은 어떻게 비교하여 일치하는지 알아볼수 있는가
            - passwordEncode.match(입력 password, member password encoded)

- 개발 시작
    - 프론트
        - 서버에 비번 일치 여부를 확인할 메서드를 만든다.
        - verification 
            - 현재 비밀 번호, 변경 비밀 번호, 변경 비밀 번호 확인을 작성했는가.
            - 현재 비밀 번호를 정확히 작성했는가.
                - 입력된 회원 비번의 일치여부 확인
        - 모든 verification을 만족시킨다면 submit을 통해 post한다.

- html
```html
<form id="f1" th:action="@{/user/password}" method="post"></form>
    <div class="input-fl-field">
        <span class="input-tit">비밀번호 변경</span>
        <div class="input-multi">
            <div class="inner-wrap">
                <span>
                    <input class="input" type="password" id="cur-user-pw" name="currentPassword" placeholder="현재 비밀번호">
                </span>
                <span>
                    <input class="input" type="password" id="edit-user-pw" name="editPassword" placeholder="새 비밀번호">
                </span>
                <span>
                    <input class="input" type="password" id="conf-user-pw" placeholder="새 비밀번호 확인">
                </span>
            </div>
        </div>
        <button type="submit" class="button btnpass">수정완료</button>
        <button type="button" class="button chekpassword">비밀번호 체크 버튼</button>
    </div>
</form>
```
- javascript

```javascript
//입력된 패스워드가 회원 패스워드와의 일치여부를 확인한다.
function verifyPassword(){
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    // 현재 비밀번호 체크
    var param = {"currentPassword":$("#cur-user-pw").val()};
    $.ajax({
        url:'/user/match/password',
        data:param,
        method:'post',
        success:function(data){
            if(!data) {
                passwordMsg.text("비밀번호가 일치하지 않습니다.").css("color", "red");
                return false;
            } else {
                // 회원의 비밀번호가 일치하면 비번을 변경하는 메서드 호출
                sendChangedPassword();
            }
        },
        error:function(request, status, error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function sendChangedPassword() {
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    var password = {
        currentPassword: currentPassword.val(),
        editPassword: editPassword.val()
    }

    $.ajax({
        url:'/user/password',
        data:JSON.stringify(password),
        contentType : "application/json",
        method:'post',
        success:function(data){
            if(data) {
                passwordMsg.text("비밀번호 변경 완료").css("color", "black");
            }
        },
        error:function(request, status, error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

var check = /^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20}$/;

// 모든 Verification을 확인한다.
// 확인 된다면 form을 submit 한다.
$(".btnpass").click(function(){
    if(!$("#cur-user-pw").val()){
        alert("비밀번호를 입력해주세요");
        $("#cur-user-pw").focus();
        return false;
    }
    if(!$("#edit-user-pw").val()){
        alert("새 비밀번호를 입력해주세요");
        $("#edit-user-pw").focus();
        return false;
    }
    if(!$("#conf-user-pw").val()){
        alert("새 비밀번호 확인을 입력해주세요");
        $("#conf-user-pw").focus();
        return false;
    }

    if($("#edit-user-pw").val() != $("#conf-user-pw").val()){
        alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    }

    //비밀번호타입체크.
    //비밀번호 복잡도 10자이상 20이하, 영문숫자특수문자 혼합.
    if(!check.test($("#cur-user-pw").val()) || !check.test($("#edit-user-pw").val()) || !check.test($("#conf-user-pw").val())){
        alert("비밀번호는 10자이상 20자이하, 영문, 숫자, 특수문자를 혼합해야 합니다.");
        return false;
    }

    if($("#cur-user-pw").val()){
        verifyPassword();	
    }
});
```

- - 백엔드
    - `/user/match/password (post)`와 `/user/passsword (post)`를 구현한다.
```java

//controller
@PostMapping("/match/password")
public ResponseEntity<Boolean> matchPassword(String currentPassword, Authentication authentication) {
    Boolean isPasswordVerified = memberService.matchPassword(currentPassword, authentication);
    return ResponseEntity.ok().body(isPasswordVerified);
}

@PostMapping("/password")
public ResponseEntity<Boolean> changePassword(@RequestBody PasswordDto passwordDto, Authentication authentication) {
    Boolean isPasswordChanged =  memberService.changePassword(passwordDto, authentication);
    return ResponseEntity.ok().body(isPasswordChanged);
}

//service
public Boolean changePassword(PasswordDto passwordDto, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String userEmail = userDetails.getUsername();

    memberRepository.findByEmail(userEmail)
        .ifPresent(member -> {
            member.setPassword(passwordEncoder.encode(passwordDto.getEditPassword()));
            memberRepository.save(member);
        });

    return true;
}

public Boolean matchPassword(String currentPassword, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String userEmail = userDetails.getUsername();

    Member member = memberRepository.findByEmail(userEmail).get();
    if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
        return false;
    }
    return true;
}

```
