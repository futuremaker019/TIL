## 사용해보고 정리한 git 명령어
<br>

### 브랜치
---

- 브랜치 생성
```vim
git branch branch명
```
<br>

- 브랜치 확인
```vim
git branch
```
<br>

- 브랜치로 이동
```vim
git checkout branch명
```
<br>

- 브랜치 병합
    - 병합하고자하는 브랜치를 합친다.
    - 일반 브랜치를 master 브랜치와 병합
    - conflicts를 해결하고 commit을 하여 통합을 완료한다.
```vim
 - git checkout master
 - git merge branch명
```
<br>

- 브랜치 삭제
```vim
git branch -d 
```
<br>


- 브랜치 이름 변경
    - 로컬에서 이름 수정 (원격 저장소에 올리지 않았을 때)
```vim
git branch -m {변경 전 branch명} {변경하고 싶은 branch명}
```
<br>

- - 원격 저장소에 올렸을 때 ([참고사이트](https://thdev.tech/git/2016/12/19/Git-Branch-Name-Change/))
```vim
 - git branch -m {old branch명} {new branch명}
 - git push origin :{old branch명}
 - git push --set-upstream origin {new branch명}
```

<br>

- bash shell에서 브랜치 정보 확인 ([참고사이트](https://opentutorials.org/course/2708/15261))
```vim
git log --branches -- graph -- decorate -- oneline
```
<br>

- 전체 브랜치를 클론하지 않고 특정 브랜치만 클론
```vim
git clone -b {branch_name} --single-branch {저장소 URL}
```
<br>

<br><br>

### 커밋
---
- 커밋 로그 변경
    - 마지막 커밋한 로그를 변경
```vim
git commit --amend "변경을 원하는 로그 작성"
```

<br>



### gitignore
---
- gitignore 파일 생성
```vim
touch .gitignore
```

<br>

- ignore 해야할 목록을 추가한다. ([gitignore 생성 사이트](https://www.toptal.com/developers/gitignore))
    - 스프링 부트에서는 프로젝트 생성 시, 자동적으로 gitignore가 생성됨
    - 


- gitignore가 적용되지 않는다면 아래의 명령어를 사용하여 cache를 삭제해준다.
```vim
git rm --cached -r .
- 전체 파일을 stage에서 내려준다.
```
- 위의 명령어를 사용해도 stage에서 내려오지 않는 파일은 직접 명시하여 내려준다.
```vim
git rm -r {파일명}
```
