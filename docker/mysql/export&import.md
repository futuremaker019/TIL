### Import

docker-compose에 export, import 할 수 있는 디렉토리를 생성하여 volume과 연결시킴

```yml
  ...

  volumes:
    - ./db/mysql/databases:/var/lib/mysql
    - ./db/mysql/config:/etc/mysql/conf.d
    - ./db/mysql/init:/docker-entrypoint-initdb.d
    - ./db/mysql/export:/home/export      # export, import시 사용될 디렉토리
```

해당 디렉토리에 `import` 할 파일을 올려둔 후, `docker exec` 에 접속하여 `mysql import` 명령어로 해당하는 데이터베이스에 들어갈 수 있도록 해준다.

<br>

### Export

export 를 home/export에서 실행하여 host와 연결된 경로에서 export 파일을 가져온다.
