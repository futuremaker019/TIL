### docker-compose 작성

```vim
version: "3"
services:
  db:
    image: mysql:8.0.30
    container_name: mysql-container
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: "root"
      MYSQL_DATABASE: mystock
      TZ: Asia/Seoul
    volumes:
      - ./db/mysql/databases:/var/lib/mysql               # volumn을 지정함, container가 삭제되어도 지정된 volumne에 데이터를 이용하여 초기세팅이 가능함
      - ./db/mysql/config:/etc/mysql/conf.d
      - ./db/mysql/init:/docker-entrypoint-initdb.d
        #command:
      #- --character-set-server=utf8mb4
      #- --lower_case_table_names=1
  jenkins:
    image: jenkins/jenkins:lts-jdk17
    container_name: jenkins_local
    volumes:
      - /var/run/docker.stock:/var/run/docker.stock
      - ./jenkins:/var/jenkins_home
    ports:
      - "8080:8080"
      - "50000:50000"
    privileged: true
    user: root
```

<br>

### docker-compose 명령어

docker-compose 백그라운드에서 실행

- 실행중인 컨테이너는 제외하고 실행되지 않은 컨테이너가 실행된다.

```vim
docker-compose up -d
```

- compose 이름을 지정하여 실행할 때

```vim
docker-compose -f [compose 명] up
ex) docker-compose -f docker-compose-local.yml up
```

모든 컨테이너가 삭제되며 실행되고 있는 컨테이너들이 모두 종료된다.

```vim
docker-compose down
```

컨테이너 로그 출력

```
docker-compose logs [컨테이너명]
```

<br>

### docker-compose.yml 파일 수정 후 반영 & 실행

이미 컨테이너로 서비스가 떠있는 상황에서 변경된 DockerFile, docker-compose.yml 을 적용해서 컨테이너를 재시작하는 방법

```vi
docker-compose up --build --force-recreate -d
```

- `--build` : 변경된 이미지를 다시 build
- `--force-recreate` : docker-compose up 했을시 변경사항이 적용되지 않는다면 해당 플래그를 사용
- `-d` : 배그라운드에서 컨테이너 실행
