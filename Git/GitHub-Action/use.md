```yml
name: Packaging Work

on:
  push:
    branches:
      - project-package # push시 `project-package` branch로 action을 실행한다.
  pull_request:
    types:
      - opened # pull request가 opened 시 action을 실행한다.
    branches:
      - 'project-package/**'

jobs:
  # typescript & scss build for js, css
  build-client:
    name: Build client
    runs-on: ubuntu-latest
    # package.json이 있는 경로를 명시해준다.
    env:
      working-directory: ./client/ts
    # steps 는 name을 기준으로 step을 나눠 실행한다.
    steps:
      # GitHub Actions는 해당 프로젝트를 리눅스 환경에 checkout하고 나서 실행을 합니다.
      # 마치 우리가 브랜치를 만들 때 checkout하는 것처럼요. 꼭 필요합니다.
      - uses: actions/checkout@v3

      - name: Build ts and sass
        # users는 action의 플러그인을 사용을 명시한다.
        # users에는 파일명이 들어가 파일을
        uses: actions/setup-node@v3
        with:
          node-version: 16.13.1

      - name: npm install
        run: npm ci
        working-directory: ${{env.working-directory}} # working directory 설정

      - name: Build sass
        run: | # 파이프라인 명령어는 이렇게 작성한다.
          npm install -g sass node-sass
          npm run sass-build
        working-directory: ${{env.working-directory}}

      - name: Build tsc
        run: npm run tsc-build
        working-directory: ${{env.working-directory}}

  # maven build for jar
  build-server:
    name: Build server
    runs-on: ubuntu-latest
    needs: build-client
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven
      - name: Build with Maven
        run: mvn -B package --file pom.xml

  # assembly run
#  assemble-project:
#    build:
#      runs-on: ubuntu-latest
#      needs: [build-client, build-server]
#      steps:
#        - uses: actions/checkout@v3
#        - name: Assemble project
```
