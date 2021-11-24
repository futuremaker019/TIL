input에 focus를 주고 싶을때 사용할 수 있다.

input tag에 ref 속성을 이용하여 DOM에 직접 접근한다.

- class 형태

```javascript
// class 형태의 React
onSubmit = (e) => {
  this.input.focus();
}

input;

render() (
  <input ref={(c) => {this.input = c;}}>
);
```

- hooks 형태

```javascript
// 초기값을 null로 잡아줄수 있음
const inputRef = React.useRef(null);

const onSubmit = () => {
  InputRef.current.focus();
};

render(
  <input ref={inputRef} onChange={...} value={value}>
)
```
