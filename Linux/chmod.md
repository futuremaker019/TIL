## chmod
---
### 파일 권한 변경

- 명령어 형식
    - $ chmod [option] [권한설정] [파일명]
    - 예시
        - chmod ug+rw file.txt
            - file.txt 파일에 대한 user와 group에 읽기와 쓰기 권한 부여
        - chmod o-wx file.txt
            - file.txt 파일을 other에 쓰기와 실행 권한 박탈


```vim
-rw-r--r-- 1 kc7851 BII 8825 Jul 26 17:55 filename
drwxr-xr-x 1 kc7851 BII 8825 Jul 26 17:55 filename
현재권한/link수/user/grorp/file size/날짜/시간/filename
```
```
-rw-r--r--
파일이며 user일 경우 읽기 쓰기만 가능, group, other일 경우 읽기만 가능

drwxr-xr-x
디렉토리이며 user일 경우 읽기, 쓰기, 실행 가능, group, other일 경우 읽기, 실행만 가능

r = 읽기
w = 쓰기
x = 실행
```
- 총 10자리
- 1번 d = directory의 약자
- 2,3,4번 user 권한
- 5,6,7번 group 권한
- 8,9,10번 other 권한

<br>

숫자로 권한 변경
- rwx를 이진법으로 봤을때 111, 10진법으로 변경하면 7
- r-w를 이진법으로 봤을때 101. 10진법으로 변경하면 5
- r = 4, w = 2, x = 1
```
--x = 1
-w- = 2
-wx = 3
r-- = 4
r-x = 5
rw- = 6
rwx = 7
```

- 예시
    - $ chmod 777 file.txt (첫번째 숫자는 user, 두번째 group, 세번째 other)
        - file.txt 파일을 user, group, other 모두 읽기,쓰기, 실행 권한 부여
    - $ chmod 755
        - file.txt 파일에 user는 모든 권한 부여, group, other는 읽기, 실행 권한 부여


