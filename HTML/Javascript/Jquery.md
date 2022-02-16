### radio button

<br>

체크된 값 가져오기

```js
var temp = $('input:radio[name="radioName"]:checked').val();
```

<br>

체크된 값 가져오기 (change 메서드)

```js
$('input[name="radioName"]').change(function () {
  var value = this.value;
});
```

<br>

js에서 radio 체크하기

```js
$('input:radio[name="radioName"][value="radioValue"]').prop('checked', true);
```
