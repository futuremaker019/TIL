### 단위 테스트 에러
- 문제 인식
    - JUnit 단위 테스트 시, 아래와 같은 오류 발생
- Caused by: java.lang.IllegalStateException: No ServletContext set
- 해결    
    - @WebAppConfiguration을 테스트 클래스에 추가한다.
- 설명
    - @WebAppConfiguration은 통합 테스트를 위핸 로드된 ApplicationContext가 WebApplicationContext여야 함을 선언하는 데 사용하는 클래스 레벨 어노테이션
    - 테스트 클래스에 @WebAppConfiguration이 있으면 웹 애플리케이션의 루트 경로에 대한 기본값을 사용하여 테스트를 위해 WebApplicationContext를 로드해야 함을 나타낸다. 기본값을 무시하려면 value() 속성을 통해 명식적 리소스 경로를 지정하여야 한다.
    - @WebAppConfiguration은 단일 테스트 클래스 또는 테스트 클래스 계층 내에서 @ContextConfiguration과 함께 사용해야 한다.

- [참고 사이트](https://codinglog.tistory.com/91)

<br>

- 문제 인식
    - no qualifying bean of type 'passwordEncoder'
- 해결
    - @ContextConfiguration에 SecurityConfig.class 추가
