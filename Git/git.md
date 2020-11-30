## 사용해보고 정리한 깃 명령어
### 브랜치

- 전체 브랜치를 클론하지 않고 특정 브랜치만 클론
```vim
git clone -b {branch_name} --single-branch {저장소 URL}
```

- 브랜치 이름 변경
```vim
git branch -m 변경 전 branch명 변경하고 싶은 branch명
```

<br>

### 커밋
- 커밋 로그 변경
    - 마지막 커밋한 로그를 변경
```vim
git commit --amend "변경을 원하는 로그 작성"
```
