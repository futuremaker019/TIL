## Docker

### EC2에 docker 설치

docker 설치 (Amazon Linux 2의 amazon-linux-extras package 를 이용)

```vi
// 도커 설치
sudo amazon-linux-extras install docker

// 도커 시작
sudo service docker start

// 권한 부여
sudo usermod -a -G docker ec2-user

// auto-start에 docker 등록
sudo chkconfig docker on

// 인스턴스 재시작 (재시작시 서버 연결 끊김)
sudo reboot

// (재접속 후)도커 버전 확인
docker version    // 명령어 실행 시, 도커 버전 확인되면 docker 설치 및 user 그룹에 포함 잘됬음을 표시
```

docker compose 설치

```vi
// 최신 docker compose를 해당 링크에서 받을 수 있음
sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose


// 권한 부여
sudo chmod +x /usr/local/bin/docker-compose


// 설치 확인
docker-compose version
```

출처 : [https://narup.tistory.com/223](https://narup.tistory.com/223)
