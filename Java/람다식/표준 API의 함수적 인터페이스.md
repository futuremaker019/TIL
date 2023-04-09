### 표준 API의 함수적 인터페이스

자바에서 제공되는 표준 API에서 한 개의 추상 메서드를 가지는 인터페이스들은 모두 람다식으로 표현이 가능하다.

```java
// Runnable
Runnable runnable = () -> {
  ... 구현
}

// Thread
Thread thread = new Thread(() -> {
  ... 구현
})
```

자바 8부터는 빈번하게 사용되는 함수적 인터페이스는 표준 API 패키지로 제공된다.

| 종류      | 추상 메서드 특징                                                                |
| --------- | ------------------------------------------------------------------------------- |
| Consumer  | - 매개값은 있고, 리턴값은 없음                                                  |
| Supplier  | - 매개값은 없고, 리턴값은 있음                                                  |
| Function  | - 매개값도 있고, 리턴값도 있음 <br> - 주로 매개값을 리턴값으로 매핑(타입 변환)  |
| Operator  | - 매개값도 있고, 리턴값도 있음 <br> - 주로 매개값을 연산하고 결과를 리턴        |
| Predicate | - 매개값은 있고, 리턴 타입은 boolean <br> - 매개값을 조사해서 true/false를 리턴 |

<br>

#### 1-1. Consumer 함수적 인터페이스

Consumer 함수적 인터페이스의 특징은 리턴값이 없는 accept() 메서드를 가지고 있으며, 단지 매개값을 소비하는 역할을 한다.

```java
Consumer<T> consumer = (t) -> { t를 소비하는 실행문; }
```

```java
public class ConsumerExample {
  public static void main(String... args) {
    // accept 메서드 구현부
    Consumer<String> consumer = t -> System.out.println(t + "8");
    consumer.accept("java");    // java8
  }
}
```

#### 1-2. Supplier 함수적 인터페이스

Supplier 함수적 인터페이스의 특징은 매개변수가 없고 리턴값이 있는 getXXX() 메서드를 가지고 있다. 이 메서드들은 실행 후 호출한 곳으로 데이터를 리턴(공급)하는 역할을 한다.

```java
Supplier<T> supplier = () -> { return T; }

/**
 *  Supplier<T> T get() T 객체를 리턴
 *  BooleanSupplier
 *  DoubleSupplier
 *  IntSupplier
 *  LongSupplier
```

```java
public class SupplierExample {
  public static void main(String... args) {
    // accept 메서드 구현부
    IntSupplier intSuppier = () -> {
      int num = (int) (Math.random() * 6) + 1;
      return num;
    }

    System.out.println(intSupplier.getAsInt());   // 3
  }
}
```

#### 1-3. Function 함수적 인터페이스

매개값과 리턴값이 있는 applyXXX() 메서드를 가지고 있다. 이 메서드들은 매개값을 리턴값으로 매핑(타입 변환)하는 역할을 한다.

| 인터페이스명         | 추상 메서드           | 설명                      |
| -------------------- | --------------------- | ------------------------- |
| Functionn<T, R>      | R apply(T t)          | 객체 T를 객체 R로 매핑    |
| BiFunctionn<T, U, R> | R apply(T t, U u)     | 객체 T, U를 객체 R로 매핑 |
| DoubleFunction<R>    | R apply(double value) | double을 객체 R로 매핑    |
| 등등                 |                       |                           |

```java
// 람다식 작성
Function<Student, String> function = t -> { return t.getName(); }
Function<Student, String> function = t -> t.getName();
```

```java
public class FunctionExample {
  private static List<Student> list = List.of{
    new Student("홍길동", 20, 12);
    new Student("김길동", 30, 40);
  }

  public static void printString(Function<Student, String> function) {
    for(Student student : list) {
      System.out.println(function.apply(student) + " ");        // 람다식 구현
    }
  }

  public static void printInt(ToIntFunction<Student> function) {
    for(Student student : list) {
      System.out.println(function.applyAsInt(student) + " ");   // 람다식 구현
    }
  }

  public static void main(String... args) {
    System.out.println("학생 이름")
    printString(t -> t.getName());        // 람다식 실행
    printInt(t -> t.getEnglishScore());
  }
}
```
