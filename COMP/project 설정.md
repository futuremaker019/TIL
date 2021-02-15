### 이클립스

- `.project` 파일로 들어가 프로젝트의 이름 변경 (같은 이클립스에서 실행시 변경해주어야 한다.)
- 업로드 파일 디렉토리 위치 변경
  - `globals.properties`의 업로드 파일 디렉토리 위치 설정
  - 업로드 파일 디렉토리는 되도록이면 하나의 디렉토리에 모아준다.
    - 커스터마이징, 공통으로 분리한다.

```java
// 예시
// 경로에 '\\' 2개가 들어가야 한다.
Globals.FilePath =D:\\dev\\simms\\simms25_upload\\GIT\\upload
Globals.SkeyPath =D:\\dev\\simms\\workspace_simms25\\SIMMS25\\src\\main\\webapp\\skey
```

- web.xml에 error-page 추가 (하단에)

```xml
<error-page>
  <error-code>404</error-code>
  <location>/error.html</location>
</error-page>
```

- server.xml에 context 및 valve 추가

```xml
<Host appBase="webapps" autoDeploy="true" name="localhost" unpackWARs="true">

  // Host 태그 아래에 추가해준다.
  <Context docBase="D:\dev\simms\simms25_upload\COMP\upload" path="/upload" reloadable="true"/>
  <Valve className="org.apache.catalina.valves.ErrorReportValve" showReport="false" showServerInfo="false"/>
```

<br>

### 데이터베이스 (MySQL)

- 접속

```sql
-- 비밀 번호는 입력 프롬프트가 나오면 입력한다.
mysql -u root -p
```

- DATABASE ([참고 사이트](https://offbyone.tistory.com/54))

```sql
-- 데이터베이스 생성
create database '데이터베이스 명';

-- 데이터베이스 보기
show databases;

-- 데이터베이스 선택
use '데이터베이스 명'

-- 테이블 보기
show tables;
```

- 테이블 덤프 ([참고 사이트](http://asq.kr/bug9idAJAKJ3MX))

```sql
-- 백업 명령어
mysqldump -u 계정 -p패스워드 DB명 > /디렉토리/파일명.sql

-- 복원 명령어
mysql -u 계정 -p DB명 < /디렉토리/파일명.sql
```
