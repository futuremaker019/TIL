기본 기능

| 명령어   | 기능                    |
| -------- | ----------------------- |
| i        | 입력모드 전환           |
| x, dd, j | 삭제                    |
| yy       | 현재 줄 복사하기        |
| u        | 명령 취소               |
| -p       | 한줄 아래 붙이기        |
| :w       | 파일 저장하기           |
| :q       | 종료하기                |
| :q!      | 저장하지 않고 강제 종료 |
| :wq!     | 저장하고 강제 종료      |

<br>

이동

| 명령어      | 기능             |
| ----------- | ---------------- |
| ctrl + b    | page up          |
| ctrl + f    | page down        |
| ctrl + u    | page up (1/2)    |
| ctrl + d    | page down (1/2)  |
| ^           | 행의 맨앞으로    |
| $           | 행의 맨끝으로    |
| gg          | 처음 줄로 이동   |
| G(대문자 g) | 마지막 줄로 이동 |

<br>

검색

| 명령어       | 기능                                     |
| ------------ | ---------------------------------------- |
| :숫자        | 지정한 줄 번호로 이동                    |
| /word        | word 검색 결과                           |
| n            | 다음 검색 결과로                         |
| N            | 반대 방향의 검색 결과로                  |
| shift + 3(#) | 해당하는 단어 검색 (해당하는 단어위에서) |

<br>

입력 모드 전환키

| 명령어 | 기능                                               |
| ------ | -------------------------------------------------- |
| a      | 현재 커서위치에서 한칸 뒤로 이동한 후 입력 모드    |
| A      | 현재 행의 끝으로 이동한 후 입력 모드로 전환        |
| i      | 입력 모드로 전환                                   |
| I      | 현재 행의 맨 앞으로 이동한 후 입력 모드로 전환     |
| o      | 현재 행 아래에 새로운 행을 만든 뒤 입력모드로 전환 |
| O      | 현쟈 행 위에 새로운 행을 만든뒤 입력모드로 전환    |
| R      | 모든 글자를 덮어씌움                               |
