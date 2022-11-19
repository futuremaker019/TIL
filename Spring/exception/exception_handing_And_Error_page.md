오류페이지 요청 흐름

```
// 컨트롤러에서 예외 발생시 was까지 요청이 전달되는 과정
was <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러 (예외발생)

// 에러 발생과 오류 페이지 요청 흐름
was `/error-page/500` 다시 요청 -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러(/error-page/500) -> view
```

오류가 발생하면 오류 페이지를 출력하기 위해 was 내부에서 다시 한번 호출이 발생한다. 이때 필터, 서블릿, 인터셉터도 모두 다시 호출된다. 이것을 방지하기위해 아래와 같이 필터와 인터셉터에 적용한다.

<br>

스프링부트에서 단순 서블릿 오류 페이지 등록 방법

```java
package hello.exception;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
  @Override
  public void customize(ConfigurableWebServerFactory factory) {
    // 404
    // http-status와 RequestMapping의 경로를 지정한다.
    ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errorpage/404");
    // 500
    ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

    // RuntimeException의 자식 exception은 모두 지정한 /error-page/500으로 보내주게 설정할수 있다.
    ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/errorpage/500");
    factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
  }
}
```

에러를 호출하는 방법

```java
@Slf4j
@Controller
public class ErrorPageController {
  @GetMapping("/error-404")
  public void error404(HttpServletResponse response) throws IOException {
    // 404 에러를 보낸다.
    response.sendError(404, "404 오류!");
  }
  @GetMapping("/error-500")
  public void error500(HttpServletResponse response) throws IOException {
    response.sendError(500);
  }

  @GetMapping("/error-ex")
  public void errorEx() {
    throw new RuntimeException("예외 발생!");
  }
}


```

<br>

Filter의 적용

```java
package hello.exception;
import hello.exception.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean logFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LogFilter());
    filterRegistrationBean.setOrder(1);
    filterRegistrationBean.addUrlPatterns("/*");

    // dispatcherType을 지정하여 해당하는 fitler가 어떤 호출에서 동작할지 지정할수 있다.
    // 현재는 클라이언트 요청과 ERROR 요청시 동작하도록 설정됨
    filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
    return filterRegistrationBean;
  }
}

// filter 구현부
package hello.exception.filter;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("log filter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();
    String uuid = UUID.randomUUID().toString();

    try {
      log.info("REQUEST [{}][{}][{}]", uuid, request.getDispatcherType(), requestURI);

      // doFilter를 호출하지 않으면 다음 fitler가 호출되지 않는다. (dispatcherServlet으로 넘어감)
      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e;
    } finally {
      log.info("RESPONSE [{}][{}][{}]", uuid, request.getDispatcherType(), requestURI);
    }
  }

  @Override
    public void destroy() {
      log.info("log filter destroy");
  }
}

// DispatcherType
public enum DispatcherType {
  FORWARD,  // MVC에서 배웠던 서블릿에서 다른 서블릿이나 JSP를 호출할때
  INCLUDE,  // 서블릿에서 다른 서블릿이나 JSP의 결과를 포함할때
  REQUEST,  // 클라이언트 요청
  ASYNC,    // 서블릿 비동기 호출
  ERROR     // 오류 요청
}

```

<br>

인터셉터 적용

```java
package hello.exception;
import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor())
          .order(1)
          .addPathPatterns("/**")
          // 오류페이지 경로를 excludePathPatterns에 추가하여 인터셉터에 걸리지 않게 만들어준다.
          .excludePathPatterns("/css/**", "/*.ico", "/error", "/error-page/**");
  }

  //@Bean
  public FilterRegistrationBean logFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LogFilter());
    filterRegistrationBean.setOrder(1);
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
    return filterRegistrationBean;
  }
}

```

<br>

스프링 부트 오류 관련 옵션

- `server.error.shitelabel.enabled=true`
  - 오류 처리 화면을 못 찾을 시, 스프링 `whtielabel오류` 페이지 적용
- `server.error.path=/error`
  - 오류 페이지 경로, 스프링이 자동 등록하는 서블릿 글로벌 오류 페이지 경로와 `BasicErrorController`오류 컨트롤러 경로에 함께 사용된다.

<br>

확장 포인트

에러 공통 처리 컨트롤러의 기능을 변경하고 싶으면 `ErrorController` 인터페이를 상속 받아서 구현하거나 `BasicErrorController` 상속 받아서 기능을 추가하면 된다.
