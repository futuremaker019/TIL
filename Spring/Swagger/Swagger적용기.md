### Swagger란

> Swagger는 REST API를 설계, 빌드, 문서화 및 사용하는 데 도움되는 OpenAPI 사양을 중심으로 구축된 오픈 소스 도구세트이다.

#### 사용하는 이유

- 적용하기 쉽다.
- 테스트 할 수 있는 UI를 제공한다.

#### 적용

build.gradle

```
SpringBoot 버전 '2.7.5'

//	swagger 버전
implementation 'io.springfox:springfox-boot-starter:3.0.0'
implementation 'io.springfox:springfox-swagger-ui:3.0.0'
```

#### configuration

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String version01 = "V1";
    private String version02 = "v2";

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(apiV1Info())
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.start.open.swaggertest"))
//                .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/v1/api/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, setGlobalResponseMessage());
    }

    private ApiInfo apiV1Info() {
        return new ApiInfoBuilder()
                .title("제목")
                .description("설명")
                .version(version01)
                .contact(new Contact("이름", "홈페이지 URL", "email"))
                .build();
    }

    @Bean
    public Docket apiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(apiV2Info())
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.start.open.swaggertest"))
//                .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/v2/api/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, setGlobalResponseMessage());
    }

    private ApiInfo apiV2Info() {
        return new ApiInfoBuilder()
                .title("제목")
                .description("설명")
                .version(version02)
                .contact(new Contact("이름", "홈페이지 URL", "email"))
                .build();
    }

    //
    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json; charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;

    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json; charset=UTF-8");
        return produces;
    }

    private List<ResponseMessage> setGlobalResponseMessage() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("OK").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found Error").build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").build());

        return responseMessages;
    }
}
```

#### SwaggerTestV1Controller

```java
@Api(value = "/vi/api 입니다.", tags = {"swagger", "v1", "api"})
@RequestMapping("/v1/api")
@RestController
public class SwaggerTestV1Controller {

    @ApiOperation(value = "testMethod", notes = "noted")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Api 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/test")
    public ResponseEntity<String> testV1Post(
            @ApiParam(name = "username", value = "username value", required = true) String username,
            @ApiParam(name = "password", value = "password value", required = false) String password
            ) {

        return ResponseEntity.ok().body("hi there");
    }
}
```

궁금한점

- MVC 형태도 Swagger API 형식으로 만드는것이 가능함?
  - `consums`의 형태에 `Content-Type`이 `application/x-www-form-urlencoded`가 들어가는데 Ajax 호출 형태인지
- `globalResponseMessage`를 작성하고도 `Controller`의 각 `RequestMapping`에 등록된 `@AaipResponses`가 동작하는지? 우선순위는?
