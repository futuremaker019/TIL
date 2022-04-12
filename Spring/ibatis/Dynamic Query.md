### IsNotEqual

### iterate

INSERT나 SELECT시 반복되는 구분을 한번에 처리 하고자 할때 iterate를 사용한다.

이해

- iBatis는 `<iterate>`를 sql문의 반복적인 문구들을 효과적으로 구현 할 수 있다.

속성

| 속성       | 설명               |
| ---------- | ------------------ |
| prepend    | 쿼리로 쓰여질 문자 |
| property   | 파라미터명         |
| open       | 시작 문자          |
| close      | 종료 문자          |
| conjuction | 구분자             |

<br>

```JAVA
List<Car> cars = new ArrayList<>();

Car car1 = new Car();
cars.add(car1);

sqlMap.insert("insertCar", cars);
```

```sql
<insert id="insertCar" parameterClass="java.util.List">
INSERT INTO Car (
  car_id,
  car_name,
  car_style
)
  <iterate prepend="VALUES" open="(" close=")" conjunction=",">
      #[].car_id#,
      #[].car_name#,
      #[].car_style#
  </iterate>
</insert>
```

`<iterate>`를 이용해서 리스트의 반복 작업을 순서대로 처리할 수 있다. `[]` 표시는 각 반복에서 객체 하나를 의미한다. <br>
`conjunction` 속성은 하나의 반복의 끝나고 다음 순번으로 가기 전에 중간에 붙여지는 문자열이다. <br>
`prepend`는 맨 앞에 한번 붙여지는 문자열이다. <br>
`open`와 `close`는 각 반복 앞과 뒤에 붙여지는 문자열이다.

<br>

데이터 조회를 코드를 다음과 같이 작성한다.

```java
List<String> styles = new ArrayList<>();
styles.add("styleA");
//중략
CarQuery carQuery = new CarQuery();
carQuery.setCar_name("Small");
carQuery.setCar_styles(styles);

List<User> uses = (List<User>) sqlMap.queryForList("selectCar", carQuery);
```

selectCar에 대한 쿼리를 작성한다.

```sql
<select id="selectCar" parameterClass="com.ospace.example.CarQuery" resultClass="com.ospace.example.Car">
SELECT *
FROM Car
WHERE car_name = #car_name#
  AND car_style IN (
    <iterate property="car_styles" conjunction=",">
      #car_styles[]#
    </iterate>
  )
</select>
```

`<iterate>`의 속성인 property를 사용하여 인자로 넘어온 CarQuery의 필드인 car_styles를 접근할 수 있게 된다. 다음에 `[]` 앞에 해당 필드명을 명시하면 된다. <br>
현재는 String 값을 직접 접근하고 있지만, 만약에 다른 클래스로 되어 있다면 앞에서 사용했던 마침표(".")를 사용해서 접근하면 된다. <br>
(ex. #cars[].car_name#)

<br>
<br>
