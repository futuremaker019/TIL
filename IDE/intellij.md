클래스 찾기

- ctrl + n

<br>

### 검색

클래스 포함 모든 파일 찾기

- shift shift

메서드 및 전체 검색

- ctrl + shift + f

<br>

### 클래스내의 변수, 메서드명 한꺼번에 바꾸기

<br>

클래스 내 변수명, 메서드명 검색

```
crt + f
```

클래스 내 변수명, 메서드명 검색 및 변경할 이름

```
ctr + r
```

한번에 변경

```
alt + a
```

<br>

탭 이동

- alt + < or >

제안 보기

- alt + enter

To project explorer

- alt + F1

project explorer에서 현재 페이지의 위치 확인

- F2

edit 창만 보기

- ctrl + shift + F12

Test class 생성
<br>Class의 edit 창에서

- ctrl + shift + t

변수 생성
<br>변수에 담을 코드 선택 후

- ctrl + alt + v

메서드 생성
<br>메서드를 생성하고자하는 코드 선택 후

- ctrl + alt + m

project explorer

- alt + 1

이전 클래스로 이동

- ctrl + e + enter

클래스 내의 같은 단어 찾기

- ctrl + alt + F7

변수로 만들기

- ctrl + alt + v

inline 메서드

- ctrl + alt + n

<br>

### SpringBoot 실행 시 에러

`SpringBoot` `run` 시, `Cammand line is too long` 에러

경로 : 해당 프로젝트의 `.idea` > `workspace.xml`

```xml
<component name="PropertiesComponent">
  ...
  ...
  <property name="dynamic.classpath" value="true" />
</component>
```
