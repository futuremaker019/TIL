- fetch()
  - list 타입을 return 한다.
  - 여러건을 조회할 때 사용한다.

```java
@Autowired
EntityManager em;

JPAQueryFactory queryFactory;

List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .leftJoin(member.team, team)
                .on(team.name.eq("teamA"))
                .fetch();
```

<br>

- fetchOne()
  - 단건 조회시 사용
  - 조회 결과가 없으면 null을 return한다.
  - 조회 결과가 여러개면 com.querydsl.core.NonUniqueResultException 이라는 에러를 발생시킨다.

```java
Member findMember2 = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();
```

<br>

- fetchFirst()
  - 단건을 조회시 사용
  - 조회 결과가 여러개이면 처음 데이터만 결과로 반환한다.
  - 조회 결과가 없으면 null을 반환한다.
  - `limit(1).fetchOne()` 과 동일함

<br>

- fetchResults()

  - QueryResults를 반환한다.
  - 페이징 처리를 위한 함수이다.

  - 페이징 정보를 포함, total count 쿼리 추가 실행

```java
@Test
public void fetchResult() {
    QueryResults<Member> results = queryFactory
                    .selectFrom(member)
                    .fetchResults();

    results.getTotal();
    List<Member> content = results.getResults();
}
```

<br>

- fetchCount()
  - count 쿼리로 변경해서 count 수 조회
