redis 접속 cli

```vim
docker exec -it [container명] redis-cli
ex) docker exec -it redis_local redis-cli
```

redis 비번을 포함하여 cli로 접속

```vim
docker exec -it redis redis-cli -a [비밀번호]
ex) docker exec -it redis redis-cli -a password
```

redis 컨터이너 접속 cli

```vim
docker exec -it [container명] /bin/sh
ex) docker exec -it redis_local /bin/sh
```

redis 비번설정 (redis:6.2.6-alpine 버전)

alpine 버전의 경우에는 redis.conf 파일의 위치 확인이 안됨

```
# 비밀번호 입력
127.0.0.1:6379> AUTH 'password'
(error) ... # 비밀번호가 설정되어 있지 않아 에러발생

# 비밀번호 확인
# 명령어 : config get requirepass
127.0.0.1:6379> config get requirepass
1) "requirepass"
2) ""       # 비밀번호 없음

# 비밀번호 설정
# config set requirepass [비밀번호]
127.0.0.1:6379> config get requirepass password

# 재접속
127.0.0.1:6379> exit

# 재접속 후 테스트
127.0.0.1:6379> keys *
(error) NOAUTH Authentication required

# 비밀번호 입력
127.0.0.1:6379> AUTH 'password'

# 정보확인
127.0.0.1:6379> keys *
(Empty array)
```

redis container에서 비밀번호도 함께 작성하여 접속한다.
```
/data # redis-cli -a [비밀번호]
```

host에서 비밀번호를 이용하여 docker 명령어로 바로 접속
```
docker exec -it redis redis-cli -a [비밀번호]
```




