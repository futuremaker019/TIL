특정 단어가 포함된 문장을 찾을때

```
grep -rni [찾는 단어] [파일명]

ex) grep -rni "mail = " ./nohup.out
```

실행중인 nohup.out 초기화

```
cat /dev/null > nohup.out
```

파일 용량 표시

```
ls -lh
```

vi에서 라인에 숫자 표시

```
:set nu
```
