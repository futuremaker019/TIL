## Docker

### EC2에 docker 설치

docker 설치 (Amazon Linux 2의 amazon-linux-extras package 를 이용)

```vi
// 인스턴스 패키지 및 캐시 업데이트
sudo yum update -y

// 도커 설치
// Amazon Linux 2
sudo amazon-linux-extras install docker

// Amazon Linix 2023
sudo yum install -y docker

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

출처 : [https://docs.aws.amazon.com/ko_kr/serverless-application-model/latest/developerguide/install-docker.html](https://docs.aws.amazon.com/ko_kr/serverless-application-model/latest/developerguide/install-docker.html)

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

### DockerFile 작성 후 실행명령어

DockerFile 작성

```vi
FROM openjdk:11
ARG JAR_FILE=build/libs/app.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "jar", "./app.jar"]
```

명령어를 작성하여 docker container를 생성한다.

```vi
docker build -t [docker hub 아이디]/[container 명] [DockerFile 위치]
ex) docker build -t futuremaker019/application-test .
```

#### docker-compose를 이용하여 DockerFile을 컨테이너로 실행하는 방법

MySQL

```vi
<!-- 디렉토리 구조 -->
└── database/
        ├── config/
        │   └── my.conf
        └── DockerFile

<!-- my.conf -->
[client]
default-character-set=utf8mb4

[mysql]
default-character-set=utf8mb4
// lower_case_table_names=1                   // 해당 설정은 command에 명시해야 한다.

[mysqld]
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
skip-character-set-client-handshake

[mysqldump]
default-character-set=utf8mb4

<!-- DockerFile -->
FROM mysql:8
ENV TZ=Asia/Seoul
```

```vi
<!-- 디렉토리 구조 -->
└── redis/
        └── DockerFile

<!-- DockerFile -->
FROM redis:8
ENV TZ=Asia/Seoul
```

```vi
version: "3.8"
services:
  redis_local:
    container_name: redis_local
    build:
      dockerfile: Dockerfile
      context: ./redis                      // redis DockerFile 위치
    image: futuremaker019/redis_local
    ports:
      - "6379:6379"
  mysql_local:
    container_name: mysql_local
    build:
      dockerfile: Dockerfile
      context: ./database                   // mysql DockerFile 위치
    image: futuremaker019/mysql_local
    environment:
      - MYSQL_DATABASE=api_backend
      - MYSQL_ROOT_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
    volumes:
      - ./database/config:/etc/mysql/conf.d
    ports:
      - "3306:3306"
```
