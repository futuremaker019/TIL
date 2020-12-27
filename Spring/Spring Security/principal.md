### principal
- Spring Security에서의 username은 User를 상속받은 CustomUser에서 user를 식별하기 위해 지정한 userId를 의미한다.
    - userId는 email, nickname, userId등 다양하게 임의로 지정이 가능하다.
    - login 구현 시에도 ID의 input name은 username이라고 명시해야 Spring Security에서 인식이 가능하다. (비록 아이디를 이메일로 지정했다고 해도 email이라고 name을 붙이면 security가 인식하지 못한다.)
```html
principal : <sec:authentication property="principal">
<!-- 여기서 member는 Member의 객체가 아님. -->
Member(domain이다.) : <sec:authentication property="principal.member"> 
<!-- 아이디, 비밀번호가 아닌 member를 식별할 수 있는 다른 속성이다. -->
사용자 이름 : <sec:authentication property="principal.member.userName">
<!-- 아이디를 의미한다. -->
사용자 아이디 : <sec:authentication property="principal.member.userName">
<!-- 사용자의 권한을 보여준다. 권한은 Member 클래스에 정의된 authList를 따른다.-->
사용자 권한 리스트 : <sec:authentication property="principal.member.authList">
```

- `Authentication`의 `principal`은 `UserDetailsService`의 반환된 객체이다.
- `UserDetailsService`를 상속받은 `CustomUserDetailsService`를 이용했다면 `loadUserByUsername()`에서 반환된 `CustomUser`(`User`를 상속받아 Custom한) 객체가 된다.
- 즉, `principal`은 `CustomUser`를 의미하므로 `principal.member`는 `CustomUser` 객체의 `getMember()`를 호출한다는 것을 알 수 있다.
