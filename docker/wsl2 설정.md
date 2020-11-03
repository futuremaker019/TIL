## wsl2를 사용하여 ubuntu를 docker에 적용시켜보자
### dokcer 설치
1. docker 사이트로 이동하여 도커 설치
    - docker for window (stable version 다운)
2. docker 설치 시 wsl2를 설치
    - wsl을 wsl2로 업데이트 해야 함
    - Microsoft에서 엡데이트 프로그램 다운 및 설치
3. 여기까지 하면 도커에서 가지고 있는 Linux로 들어가진다.
    - 아래에 ubuntu를 설치하면 powershell에서 ubuntu를 사용할 수 있다.
    - 리눅스에서만 먹히는 키워드를 사용하고 싶어서 설치함
<br>

### ubuntu 설치
1. Microsoft store에서 ubuntu 검색 후, 설치한다.

### wsl2
1. 앱 -> 앱 및 기능 -> 관련 설정, 프로그램 및 기능 클릭
2. windows 기능 켜기 / 끄기로 들어가 Linux용 windows 하위 시스템 활성화
    - Hyper-V도 같이 활성화 하자.
    - 컴퓨터 재시갖
3. powershell 실행
    - wsl -l -v
        - wsl이 실행할 수 있는 프로그램들과 그 프로그램들의 wsl 버전을 확인한다.
    - 설치된 ubuntu를 wsl2로 버전 업그레이드하기
        - wsl --set-version Ubuntu-18.03 2
    - wsl --set-default-version 2
        - 위 명령어를 사용하여 기본 아키텍쳐가 wsl2로 설정되게 만들어준다.
        - 명령어 입력 시, wsl과 wsl2의 차이점을 알아보라는 문구가 뜸

4. docker desktop을 실행
    - config -> resouce -> wsl integration ubuntu가 등록되어 있는지 확인 후 활성화
    - apply & restart 클릭 후, 컴퓨터를 재부팅하자
5. powershell에서 wsl 입력 시, 해당 리눅스 우분투로 이동한다.
    - exit를 치면 다시 돌아옴