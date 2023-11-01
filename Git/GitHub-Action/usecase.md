```yml
name: Packaging Work

on:
  push:
    branches:
      - project-package
  pull_request:
    types:
      - opened
    branches:
      - 'project-package/**'

jobs:
  build:
    runs-on: ubuntu-latest
    env:
      working-directory: ./client/ts # package.json의 위치
    steps:
      - uses: actions/checkout@v3

      - name: Build ts and sass
        uses: actions/setup-node@v3
        with:
          node-version: 16.13.1

      - name: npm install
        run: npm ci
        working-directory: ${{env.working-directory}}

      - name: Build tsc
        run: npm run tsc-build
        working-directory: ${{env.working-directory}}

      - name: Build sass
        run: |
          npm install -g sass node-sass
          npm run sass-build
        working-directory: ${{env.working-directory}}

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven

      - name: Assembly applied
        run: |
          mvn assembly:assembly

      - name: Clone prev project
        uses: GuillaumeFalourd/clone-github-repo-action@main
        with:
          owner: 'owener의  id'
          repository: 'repository 명'
          access-token: ${{ secrets.API_TOKEN_GITHUB }}       
          branch: master                                      # 브랜치 명을 

      - name: set zip files
        run: |
          chmod +x ./package.sh
          sh package.sh

      - name: Pushes test file
        uses: cpina/github-action-push-to-another-repository@main
        env:
          API_TOKEN_GITHUB: ${{ secrets.API_TOKEN_GITHUB }}
        with:
          source-directory: './ROOT'
          destination-github-username: 'username'
          destination-repository-name: 'repository name'
          user-email: user email
          target-branch: master
          commit-message: commit message
```

```sh
#!/bin/sh

DIR_CLONE="ROOT"                       # repository명
DIR_ZIP="server/ROOT/WEB-INF"          # zip파일의 위치
DIR_MONTH=`date +%Y-%m`                # 현재 년-월 생성  ex) 2023-09
ROOT=`pwd`

echo "> cd $ROOT/$DIR_CLONE"
cd $ROOT/$DIR_CLONE

echo "> mkdir $DIR_MONTH"
if [ ! -d "$DIR_MONTH" ]; then
 mkdir "$DIR_MONTH"
else
 echo "$DIR_MONTH dir already exist"
fi

cp -R $ROOT/$DIR_ZIP/*.zip $ROOT/$DIR_CLONE/$DIR_MONTH/
```
