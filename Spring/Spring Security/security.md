### Spring security에서 사용자의 비밀 번호를 대조하여 비밀번호를 변경하고자 한다.

- 참고할 만한 사이트
    - [깃헙의 몇줄 정보](https://bit.ly/37IgN3H)
    - [정리된 사이트](https://codevang.tistory.com/271)


- 정리
    - Authentication에서 패스워드를 가져오려고 시도했지만 String에 담기지는 않았다.
    - Repository에서 직접 encoding된 패스워드를 가져와 passwordEncode.match 메서드를 이용해 입력한 패스워드와 저장된 사용자의 패스워드가 같은지 비교해주었다.
    - User는 UserDetails를 상속받는다.
        - CustomUser를 만들어 User를 상속받아 생성자에 userid, password, roles를 저장해줬다.
