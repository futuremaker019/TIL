### Array.prototype.slice()

- slice() 메서드는 어떤 배열의 begin부터 end까지(end 미포함)에 대한 얕은 복사본을 새로운 배열 객체로 반환한다. 원본 배열은 바뀌지 않는다.

```javascript
const animals = ["ant", "bison", "camel", "duck", "elephant"];

console.log(animals.slice(2));
// expected output: Array ["camel", "duck", "elephant"]

console.log(animals.slice(2, 4));
// expected output: Array ["camel", "duck"]

console.log(animals.slice(1, 5));
// expected output: Array ["bison", "camel", "duck", "elephant"]
```

`arr.slice([begin[, end]])`

- 매개변수

  - begin
    - 0을 시작으로 하는 추출 시작점에 대한 인덱스를 의미함
    - 음수 인덱스는 배열의 끝에서부터의 길이를 나타냄
      - `slice(-2)`는 배열에서 마지막 두 개의 앨리먼트를 추출함
    - `begin`이 `undefined`인 경우에는, 0번 인덱스부터 slice 한다.
    - `begin`이 배열의 길이보다 큰 경우, 빈 배열을 반환한다.
  - end
    - 추출을 종료 할 0 기준 인덱스, `slice`는 `end` 인덱스를 제외하고 추출한다.
    - ex) `slice(1, 4)`, 두번째 요소부터 네번째 요소까지 (1~3을 인덱스로 하는 요소)를 추출

- 배열형 객체
  - `slice()` 메서드를 호출하여 배열형 객체와 콜렉션을 새로운 `Array`로 변환할 수 있다.

```javascript
function list() {
  return Arrary.prototype.slice.call(arguments);
}

let list1 = list(1, 2, 3); // [1, 2, 3]
```

- 참고 : [MDN-Array.prototype.slice()](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/slice)

<br><br>

### Array.prototype.splice()

- `splice()` 메서드는 배열의 기존 요소를 삭제 또는 교체하거나 새 요소를 추가하여 배열의 내용을 변경한다.

`array.splice(start[, deleteCount[, item1[, item2[, ...]]]])`
<br>

- 매개변수
  - start
    - 배열의 변경을 시작할 인덱스, 음수인 경우 배열의 끝에서부터 요소를 새어간다.
  - deleteCount (Optional)
    - 배열에서 제거할 요소의 수
  - item1, item2, ... (Optional)
    - 배열에 추가할 요소, 아무 요소도 지정하지 않으면 `splice()`는 요소를 제거만 한다.
- 예제
  - 하나도 제거하지 않고, 2번 인덱스에 "drum"을 추가

```javascript
var myFish = ["angel", "clown", "mandarin", "sturgeon"];
var removed = myFish.splice(2, 0, "drum");

// myFish is ["angel", "clown", "drum", "mandarin", "sturgeon"]
// removed is [], no elements removed
```

- - 3번 인뎃스에서 한개 요소 제거

```javascript
var myFish = ["angel", "clown", "drum", "mandarin", "sturgeon"];
var removed = myFish.splice(3, 1);

// removed is ["mandarin"]
// myFish is ["angel", "clown", "drum", "sturgeon"]
```
