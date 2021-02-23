### IFNULL

해당 필드의 값이 NULL을 반활할 때 다른 값으로 출력할 수 있도록 하는 함수

```sql
SELECT IFNULL(필드명, "대채할 값") FROM 테이블명
```

하나의 필드 뿐만 아니라 연속적으로도 활용 가능하다.

ex)

| keyword | common_rep | fur_rep | makeup_req |
| ------- | ---------- | ------- | ---------- |
| acorn   | 브라운     | NULL    | NULL       |
| almond  | 브라운     | NULL    | NULL       |
| amber   | 엘로우     | NULL    | 엘로우     |
| ambr    | NULL       | NULL    | 브라운     |

- 위의 데이터는 3개의 필드에서 무작위로 NULL 값이 존재
- 하지만 3개의 필드중 값이 존재한다면 그 값은 동일한 경우

```sql
SELECT keyword, IFNULL(common_rep, IFNULL(fur_rep, makeup_rep)) FROM COLOR;

-- fur_app이 NULL이면 makeup_rep의 값을 반환
IFNULL(fur_rep, makeup_rep)

-- common_rep가 NULL이면 IFNULL(fur_rep, makeup_rep)을 반환
IFNULL(common_rep, IFNULL(fur_rep, makeup_rep))
```

### CONCAT()

### REPLACE()

```sql
SELECT REPLACE(컬럼명, '변경전 문자', '변경후 문자')
```
