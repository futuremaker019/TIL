#### getClass().getResource("")

해당 클래스를 기준으로 파일 경로를 작성한다.

```java
getClass().getResource("file 경로")
ArticleController.class.getResource("ArticleController class를 기준으로 한 파일경로")
```

#### new FileReader()

```java
Reader reader = new FileReader("실행 톰캣의 bin을 기준으로 한 파일경로")
```
