docker-compose에 작성된 방식데로 젠킨스 설치

- 주의 사항: Volume을 위치하고 싶은 디렉토리로 설정한다.

git pull 및 submodules 를 위한 github token 추가 (jenkins ver 2.414.3 기준)

- 해당 토큰은 webhook 및 private repository를 제어할수 있는 권한을 가진 토큰으로 만들어져야 한다.
- 등록방법
  - jenkins 관리 -> Credentails -> System -> Global credentails 이동
  - add Credentails
  - username
    - github id
  - password
    - tocken 입력
