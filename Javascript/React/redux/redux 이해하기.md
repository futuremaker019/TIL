### 리덕스란

리덕스는 전역상태 관리에 사용되는 라이브러리이다. 컴포넌트의 상태관리의 로직을 분리시켜 컴포넌트 관리를 더 용이하도록 돕는다.

<br>

### 간락한 흐름

컴포넌트의 상태를 이벤트가 아닌 `액션(Action)`을 발생시켜 상태를 변경한다. 액션은 일종의 객체이며, 상태를 변경하는데 필요한 정보를 가지고 있다. 

생성된 액션을 `리듀서(Reducer)`에 전달한다. 리듀서는 이전상태와 액션을 전달받아 새로운 상태를 변환하는 함수이다. 이 과정을 통해 상태가 변경된다.

모든 상태는 하나의 `스토어(Store)`에 저장되며, 스토어의 상태는 불변을 유지한다. 직접 상태를 수정하는 것은 불가능하며, 새로운 상태를 반환하는 방식으로 상태를 변경한다.

리덕스는 단방향 데이터 흐름과 불변셩을 유지하는 상태 관리 패턴을 따른다.

<br>

### 리덕스에 필요한 개념

#### Action(액션)

상태를 변화시키기 위해 변화에 대한 정보가 필요하다. 액션은 상태 변화에 대해 알려주는 순수 자바 스크립트 객체이다.

액션 객체는 상태 변화에 대한 type을 필수로 가지고 있어야 한다. 액션의 type은 액션의 행위를 나타내는 문자열이다.

또한 액션은 Payload를 가지며 Payload에는 변경될 데이터를 가진다.


```javascript

// 액션 타입 정의
enum ActionTypes {
  INCREMENT = "INCREMENT",
  DECREMENT = "DECREMENT",
}

// 액션 생성자 함수 정의
interface IncrementAction {
  type: ActionTypes.INCREMENT;
  payload: {
    value: number;
  };
}

interface DecrementAction {
  type: ActionTypes.DECREMENT;
  payload: {
    value: number;
  };
}

type CounterAction = IncrementAction | DecrementAction;

// 액선 메서드 정의
function increment(value: number): IncrementAction {
  return {
    type: ActionTypes.INCREMENT,
    payload: {
      value,
    },
  };
}
function decrement(value: number): DecrementAction {
  return {
    type: ActionTypes.DECREMENT,
    payload: {
      value,
    },
  };
}
```

액션을 사용하기 위해서는 일반적으로 액션 메서드를 사용한다. 액션 생성자 함수는 액션 객체를 반환하는 순수 함수이다.


#### Reducer (리듀서)

리듀서는 상태와 액션을 가지고 함수를 실행하는 역할을 한다. 리듀서는 두가지 인자를 받게 되는데, 첫번째로 이전 상태의 정보를, 두번째로 액션 객체를 받는다.

리듀서는 액션에 대한 함수를 정의하고, 함수를 실행해서 상태를 업데이트 한다.

### Store

스토어는 애플리케이션의 상태를 저장하고, 액션을 디스패치해서 상태를 업데이트하고, 상태 변화를 구독할 수 있는 객체이다.

스토어 객체는 createStore 함수를 통해 생성된다. createStore는 첫번쨰 인자로 리듀서 함수를 받고, 두번쨰 인자로 초기 상태값을 받는다. 예제는 아래와 같다.

```javascript
import { createStore } from 'redux';

// reducer의 반환타입을 만들어준다.
interface CounterState {
  value: number;
}

// initailState 작성
const initialState: CounterState = {
  value: 0,
};


/**
 * reducer를 정의한다.
 **/
function counterReducer(
  state: CounterState = initialState,
  action: CounterAction
): CounterState {
  switch (action.type) {
    case ActionTypes.INCREMENT:
      return {
        value: state.value + action.payload.value,
      };
    case ActionTypes.DECREMENT:
      return {
        value: state.value - action.payload.value,
      };
    default:
      return state;
  }
}

// 정의된 reducer와 initailState를 파라미터로 사용하여 store를 생성해준다.
const store = createStore(counterReducer, initialState);
```

#### Dispatch (디스패치)

디스패치는 스토어의 내장함수 중 하나로 액션을 실행시키는 역할을 하며 액션을 인자로 받게 된다. 

디스패치는 다음과 같이 발생한다.

1. 디스패치로 액션을 실행한다.
2. 리듀서는 이전 상태와 액션객체를 받아 스토어 상태를 업데이트한다.

```javascript

import { useDispatch } from 'react-redux';
import { increment, decrement } from './actions';

function Counter() {

  // useDispatch 메서드를 이용해 store에 등록된 reducer에 전달할 action을 받아준다.
  const dispatch = useDispatch();

  const handleIncrement = () => {
    dispatch(increment(1));     // 생성된 action을 reducer에 전달
  };

  const handleDecrement = () => {
    dispatch(decrement(1));
  };

  return (
    <div>
      <h1>Counter</h1>
      <p>{/* 상태 값 출력 */}</p>
      <button onClick={handleIncrement}>+</button>
      <button onClick={handleDecrement}>-</button>
    </div>
  );
}
```

<br>

---

출처
- [https://woongtech.tistory.com/entry/리덕스-이해하기-reactreduxtypescript](https://woongtech.tistory.com/entry/%EB%A6%AC%EB%8D%95%EC%8A%A4-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-reactreduxtypescript)