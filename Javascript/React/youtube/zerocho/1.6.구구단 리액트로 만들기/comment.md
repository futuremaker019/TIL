초기 stata를 constructor에서 정의할 수 있다.

```javascript
constructor(props) {
  super(props);
  this.state = {
    first: Math.ceil(Math.random() * 9),
    second: Math.ceil(Math.random() * 9),
    value: '',
    result: '',
  }
}
```

<br>

메서드는 class에 정의하여 사용한다.

```javascript
onSubmit = (e) => {
  e.preventDefault();
  if (parseInt(this.state.value) === this.state.first * this.state.second) {
    // this.setState({})`를 이용하여 한번에 state를 변화 시켜줄수도 있다.
    this.setState({
      result: '정답',
      first: Math.ceil(Math.random() * 9),
      second: Math.ceil(Math.random() * 9),
      value: '',
    });
  } else {
    this.setState({
      result: '땡',
      value: '',
    });
  }
};

onChange = (e) => {
  this.setState({ value: e.target.value });
};

<form onSubmit={this.onSubmit}>
  <input type="number" value={this.state.value} onChange={this.onChange} />
  <button>입력</button>
</form>;
```

<br>

`value` 값은 `state`를 사용하여 변경한다.

```javascript
onChange = (e) => {
  this.setState({ value: e.target.value });
};

<input type="number" value={this.state.value} onChange={this.onChange} />;
```

<br>

이벤트의 로직을 form의 onSubmit에 적었다...
javascript에서는 이벤트 메서드의 이름을 `camel case`로 적는다.
