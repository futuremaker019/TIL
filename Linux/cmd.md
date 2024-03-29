특정 단어가 포함된 문장을 찾을때

```vi
grep -rni [찾는 단어] [파일명]

ex) grep -rni "mail = " ./nohup.out
```

실행중인 nohup.out 초기화

```vi
cat /dev/null > nohup.out
```

파일 용량 표시

```vi
ls -lh
```

vi에서 라인에 숫자 표시

```vi
:set nu
```

lsof는 list open files의 약자로 시스템에서 열린 파일 목록을 알려주고 사용하는 프로세스, 디바이스 정보, 파일의 종류등 상세한 정보를 출력해 준다.

`-i` : -i 옵션은 TCP/UDP의 모든 네트워크 포트를 표시한다. -i 뒤에 프로토콜을 명시하면 해당 프로토콜 관련 포토만 표시한다.

```vi
lsof -i
lsof -i TCP
lsof -i UDP

```

`-P` : -P 옵션을 사용하면 서비스 이름대신 포트 번호로 표시해 준다. <br>
`-n` : -n 옵션은 숫자로 표시된 IP 주소와 포트 번호를 호스트 이름이나 서비스 이름으로 변환하지 않고 숫자 형태로 표시하도록 하는 옵션이다.<br>

```vi
sudo lsof -i -P -n | grep jenkins
```

`-t`: TCP 연결 정보를 보여준다..<br>
`-n`: 숫자 형태로 주소와 포트 번호를 표시한다. 호스트 이름이나 서비스 이름으로 변환하지 않는다.<br>
`-p`: 연결된 프로세스의 정보를 보여준다..<br>
`-l`: 대기 중인 연결 정보를 보여준다. 즉, 수신 대기 중인 소켓 정보를 표시한다.<br>

```
netstat -tnpl
```