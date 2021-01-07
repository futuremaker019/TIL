Amazon linux 2 를 기준으로 작성

### jdk 1.8 설치
- 설치
```vim
sudo yum install -y java-1.8.0-openjdk-devel.x86_64
```
- 기본적으로 설정되어 있는 jdk7에서 8로 변경해준다.
```vim
sudo /usr/sbin/alternative --config java
-- java8을 선택해준다.
```
- jdk7 삭제
```
sudo yum remove java-1.7.0-openjdk
```
- java 버전 확인
```
java -version
javac -version
```

### Tomcat 9 설치
- 원하는 버전의 톰캣을 검색하자
    - 톰캣 사이트 접속 후, 다운로드에서 Tomcat 9 버전 클릭
    - Archive 클릭
    - 원하는 버전의 Tomcat 클릭 후 다운로드 하자
- 리눅스에 tomcat 다운로드
```
wget 복사한 링크 경로 (tar file)
```
- 다운로드 된 파일의 압축을 풀어줌
```
tar xzf apach-tomcat-9.0.37.tar.gz
```
- 다운로드 파일은 삭제한다.
```
sudo rm 파일명
```
- 파일명 변경
```
mv '변경전 파일명' '변경할 파일명' 
```
- tomcat 파일을 적절한 디렉토리로 옮겨줌
```
sudo mv tomcat /usr/local
```

<br>

### 환경변수 등록
- profile을 vi로 실행
```
sudo vi /etc/profile
```
- JAVA 및 Tomcat의 환경 변수 등록 (맨 아래에 등록한다.)
    - i 를 눌러서 편집한다.
```
#JAVA PATH
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.265.b01-0.54.amzn1.x86_64
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.265.b01-1.amzn2.0.1.x86_64
#Tomcat 9 PATH
CATALINA_HOME=/usr/local/tomcat--9.0.37
CLASSPATH=$JAVA_HOME/jre/lib:$JAVA_HOME/lib/tools.jar:$CATALINA_HOME/lib-jsp-api.jar:$CATALINA_HOME/lib/servlet-api.jar
PATH=$PATH:$JAVA_HOME/bin:/bin:/sbin
export JAVA_HOME PATH CLASSPATH CATALINA_HOME
```
- 편집이 끝나면 :wq로 저장 후 나가기를 한다.
    - 뒤에 느낌표(!)를 붙이면 저장 후 '강제로' 나가기가 된다.
- 환경변수를 저장했다면 source 키워드 실행
```
source /etc/profile
```
- 버전 확인으로 Tomcat의 설치를 확인한다.
```
- Tomcat의 lib 디렉토리로 이동
cd /usr/local/tomcat/lib
- 명령어로 버전 확인
java -cp catalina.jar org.apache.catalina.util.ServerInfo
```
- 실행
```
/usr/local/tomcat/bin/startup.sh
$CATALINA_HOME/bin/startup.sh
```
- 종료
```
/usr/local/tomcat/bin/shutdown.sh
$CATALINA_HOME/bin/shutdown.sh
```

- ip : 8080

<br>

### Maven 설치
- [아마존 사이트에 Maven 설치를 따른다.](https://docs.aws.amazon.com/ko_kr/neptune/latest/userguide/iam-auth-connect-prerq.html)
- Maven 설치 후, jdk 1.7이 같이 설치된다.
    - jdk 변경 및 삭제를 다시 해준다.
- Maven 버전 확인
    - mvn --version

- 2020-11-06부터 아마존 리눅스 1은 더이상 프리티어 사용이 아님
    - Amazon Limux 2에서 Maven 검색 시,(yum list | grep maven) Maven이 검색됨
    - 아마존 사이트의 Maven 설치 방식으로 설치했다.
    - yum에 있는 maven도 설치해보자
<br>

### git 설치
- 설치 명령어
```
sudo yum install git
```

### 배포
- git clone을 위한 디렉토리 생성
- pom.xml 파일이 있는 디렉토리에서 maven CLI 명령어 실행
    - mvn clean
    - mvn package -DskipTests
        - 테스트를 스킵한다. (테스트 시 오류가 나면 war 파일 생성이 되지 않는다.)
    - target 디렉토리의 war 파일 이름 변경
```
mv '변경전 파일명.war' '변경할 파일명.war' 
ex) mv controller-...war library.war
```
-   - war 파일 tomcat의 webapps 디렉토리로 이동
```
mv 파일명 이동경로
ex) mv library.war /usr/local/tomcat/webapps
```
- server.xml 수정 필요
    - tomcat의 conf 디렉토리에 있는 server.xml 파일에 context.tag를 추가
```
<Context docBase="library" path="/" reloadable="true" source="org.eclipse.jst.jee.server:library"/>
```
- tomcat 실행
    - $CATALINA_HOME/bin/startup.sh

- 파일 업로드 기능을 구현했다면 root 디렉토리 및 temp 디렉토리를 생성해야 한다.
    - tomcat 내부에 upload 디렉토리, upload 디렉토리에 temp 디렉토리 생성
    - 디렉토리 명은 명시한 root 디렉토리를 따른다.

- 실행되고 있는 tomcat의 실시간 로그를 확인하고 싶다면
    - 새로운 창에서 아래의 명령어를 실행시킨다. (putty에서 새로운 창을 하나 더 실행 시킨다.)
```
tail -f /usr/local/tomcat/logs/catalina.out
```
