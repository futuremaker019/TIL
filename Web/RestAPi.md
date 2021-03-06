## REST(Representational State Transfer : 자원의 상태 전달) - 네크워크 아키텍쳐

<br>

### REST 구성

- 자원(Resource)
- 행위(Verb) - Http Method
- 표현(Representation)

<br>

### REST의 특징

```
- Uniform (유니폼 인터페이스)
- Stateless (무상태성)
- Cacheable (캐시 가능)
- Self-descriptiveness (자체 표현 구조)
- Client-Server 구조
- 계층형 구조
```

1. Client, Server
   - 클라이언트와 서버가 서로 독립적으로 분리 되어 있어야 한다.
2. Stateless
   - 요청에 대해서 클라이언트의 상태를 서버에 저장하지 않는다.
3. Cache
   - 클라이언트는 서버의 응답을 Cache(임시저장)할 수 있어야 한다. 클라이언트가 Cache를 통해서 응답을 재사용할 수 있어야 하며, 이를 통해서 서버의 부하를 낮춘다.
4. 계층화 (Layered System)
   - 서버와 클라이언트 사이에 방화벽, 게이트웨이, Proxy 등 다양한 계층 형태로 구성이 가능해야 하며, 이를 확장 할 수 있어야 한다.
5. 인터페이스 일관성
   - 인터페이스의 일관성을 지키고, 아키텍처를 단순화시켜 작은 단위로 분리하여, 클라이언트, 서버가 독립적으로 개선될 수 있어야 한다.
6. Code On Demand (Optional)
   - 자바 애플릿, 자바스크립트, 플래시 등 특정한 기능을 서버로부터 클라이언트가 전달받아 코드를 실행할 수 있어야 한다.

<br>

### REST API 디자인 가이드

<br>

URI는 정보의 자원을 표현해야 한다. (리소스명은 동사보다는 명사를 사용) <br>
일반적으로 URI는 자원을 표현하는데 중점을 두어야 한다. delete과 같은 행위에 대한 표현이 들어가서는 안된다.

```
GET /members/delete/1

- 아래와 같은 방식으로 수정하자
GET /members/1
```

자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE등) 로 표현한다.

### URI 설계 시 주의할 점

1. 슬래시 구분자(/) 는 계층 관계를 나타내는 데 사용

```
http://restapi.example.com/house/apartments
http://restapi.example.com/animals/mammals/whales
```

2. URI 마지막 문자로 슬래시 (/)를 포함하지 않는다.
3. 하이픈(-)은 URI 가독성을 높이는데 사용
4. 밑줄(\_)은 URI에 사용하지 않는다.
5. URI 경로에는 소문자가 적합하다.
6. 파일 확장자는 URI에 포함시키지 않는다.

```
http://restapi.example.com/members/soccer/1/photo.jpg (x)
```

7. 세션 ID를 포함하지 않는다.
8. 프로그래밍 언어의 Method명을 이용하지 않는다.
9. 명사에 단수형 보다는 복수형을 사용해야 한다. 컬렉션에 대한 표현은 복수로 사용
10. 컨트롤러 이름으로는 동사나 동사구를 사용한다.

```
https://fastcampus.co.kr/classes/java/curriculums/web-master
```

11. 컨트롤러 이름으로는 동사나 동사구를 사용한다.

```
https://fastcampus.co.kr/classes/java/curriculums/web-master/re-order
```

12. 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.

```
... /curriculums/web-master/lessons/{lesson-id}/users/{user-id}
... /curriculums/web-master/lessons/2/users/100
```

13. CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.

```
GET : 생략 ... /curriculums/web-master/lessons/2/users/100/READ (X)
DELETE : 생략 ... /curriculums/web-master/lessons/2/users/100   (O)
```

14. URI Query Parameter 디자인
    URI 쿼리 부분으로 컬렉션 결과에 대해서 필터링 할 수 있다.

```
생략 ... /curriculums/web-master?chaper=2
```

15. URI Query는 컬렉션의 결과를 페이지로 구분하여 나타내는데 사용한다.

```
생략 ... /curriculums/web-master?chaper=2&page=0&size=10&sort=asc
```

16. API에 있어서 서브 도메인은 일관성 있게 사용해야 한다.

```
https://fastcampus.co.kr
https://api.fastcampus.co.kr
https://api-fastcampus.co.kr
```

17. 클라이언트 개발자 포탈 서브 도메인은 일관성 있게 만든다.

```
https://dev-fastcampus.co.kr
```

REST API에서는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI 안에 포함시키지 않는다. Accept header를 사용하도록 한다.

```
GET /members/soccer/1/photo HTTP/1.1 Host: restapi.example.com Accept: image/jpg
```

<br>

### 리소스간의 관계를 표현하는 방법

- REST 리소스 간에는 연관관계가 있을 수 있고, 이런 경우 다음과 같은 표현방법으로 사용한다.

```
/리소스명/리소스 ID/관계가 있는 다른 리소스명

ex) GET : /users/{userid}/devices (일반적으로 소유 'has'의 관계를 표현할 때)
```

- 만약에 관계명이 복잡하다면 이를 서브 리소스에 명시적으로 표현하는 방법이 있다. 예를 들어 사용자가 '좋아하는' 디바이스 목록을 표현해야 할 경우 다음과 같은 형태로 사용될 수 있다.

```
GET : /users/{userid}/likes/devices (관계명이 애매하거나 구체적 표현이 필요할 때)
```

### 자원을 표현하는 Collection과 Document

- Collection과 Document에 대해 알면 URI 설계가 한 층 더 쉬워진다.
  - Collection은 Ducument의 집합, 객체들의 집합이라고 생각하자.
    - Collection은 복수를 사용하여 명시하자.
  - Document는 단순히 문서, 하나의 객체로 생각하자.

```
http://restapi.example.com/sports/soccer

http://restapi.example.com/sports/soccer/player/13
- sports, players 컬렉션과 soccer, 13(13인 선수)를 의미하는 도큐먼트로 URI가 이루어지게 된다.
```

### HTTP 응답 상태 코드

- 잘 설계된 REST API는 URI만 잘 설계된 것이 아닌 그 리소스에 대한 응답을 잘 내어주는 것까지 포함되어야 한다.

<br>

| 상태 코드          | 설명                                                                                                    |
| ------------------ | ------------------------------------------------------------------------------------------------------- |
| 200 <br> (OK)      | 클라이언트의 요청을 정상적으로 수행함                                                                   |
| 201 <br> (Created) | 클라이언트가 어떠한 리소스 생성을 요청, 해당 리소스가 성공적으로 생성됨(POST를 통한 리소스 생성 작업시) |

<br>

| 상태 코드                     | 설명                                                                                                                                                                                  |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 400 <br> (bad request)        | 클라이언트의 요청이 부적절 할 경우 사용하는 응답 코드                                                                                                                                 |
| 401 <br> (Unauthorized)       | 클라이언트가 인증되지 않은 상태에서 보호된 리소스를 요청했을 때 사용하는 응답 코드                                                                                                    |
| 403 <br> (forbidden)          | - 유저 인즈앙태와 관계없이 응답하고 싶지 않은 리소스를 클라이언트가 요청했을 때 사용하는 응답 코드 <br> - 403보다는 400이나 404를 사용할 것을 권고, 403 자체가 리소스가 존재한다는 뜻 |
| 405 <br> (Method not allowed) | 클라이언트가 요청한 리소스에서는 사용 불가능한 Method를 이욯했을 경우 사용하는 응답 코드                                                                                              |

<br>

| 상태 코드                        | 설명                                                                                                                                      |
| -------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| 301 <br> (Redirect)              | - 클라이언트가 요청한 리소스에 대한 URI가 변경 되었을 때 사용하는 응답 코드 <br> - 응답 시 Location header에 변경된 URI를 적어 줘야 한다. |
| 500 <br> (Internal Server Error) | 서버에 문제가 있을 경우 사용하는 응답 코드                                                                                                |
