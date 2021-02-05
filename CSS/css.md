- 박스에서 글자수가 넘을 때, 한줄로 보여줌

```css
.notice li {
  /* 한줄로 표시해준다. */
  overflow: hidden;
  /* 한줄 넘을때 (...)을 생성해준다. */
  text-overflow: ellipsis;
  /* nomal과 같이 공백을 채우지만 가로로 긴 줄에서도 줄 바꿈을 하지 않고 표시 */
  white-space: nowrap;
}
```
