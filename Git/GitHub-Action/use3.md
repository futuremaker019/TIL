### S3와 CodeDeploy를 이용한 CI/CD

전체 플로우

1. ts, sass 컴파일
2. 


acion.yml

```yml
name: Packaging Work

on:
  push:
    branches:
      - project-package
      - release
  pull_request:
    types:
      - opened
    branches:
      - 'project-package/**'

jobs:
  build:
    runs-on: ubuntu-latest
    env:
      working-directory: ./client/ts
    steps:
      - uses: actions/checkout@v3

        # ts 및 sass build를 위한 
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
          echo `pwd`
          echo `ls -al`

      - name: Clone simmssoft/SIMMS_V3_ROOT
        uses: GuillaumeFalourd/clone-github-repo-action@main
        with:
          owner: 'simmssoft'
          repository: 'SIMMS_V3_ROOT'
          access-token: ${{ secrets.API_TOKEN_GITHUB }}
          branch: master

      - name: Set zip file
        run: |
          chmod +x ./package.sh
          sh package.sh

      - name: Push zip file
        uses: cpina/github-action-push-to-another-repository@main
        env:
          API_TOKEN_GITHUB: ${{ secrets.API_TOKEN_GITHUB }}
        with:
          source-directory: './SIMMS_V3_ROOT'
          destination-github-username: 'simmssoft'
          destination-repository-name: 'SIMMS_V3_ROOT'
          user-email: tech@simmssoft.com
          target-branch: master
          commit-message: SIMMSV3_ROOT

      - name: Make zip file for deploy
        run: |
          mkdir -p ./deploy/scripts
          cp ./appspec.yml ./deploy/
          cp ./server/ROOT/WEB-INF/*.zip ./deploy/simms.zip
          cp ./scripts/*.sh ./deploy/scripts/
          zip -r ./$GITHUB_SHA.zip ./deploy

      - name: Access AWS S3
        uses: aws-actions/configure-aws-credentials@v1
        with:
          aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY }}
          aws-secret-access-key: ${{ secrets.AWS_SECRET_KEY }}
          aws-region: ap-northeast-2

      - name: Deliver to AWS S3
        run: aws s3 cp --region ap-northeast-2 --acl private ./$GITHUB_SHA.zip s3://${{ secrets.AWS_S3_BUCKET }}/deploy/$GITHUB_SHA.zip

      - name: Code Deploy
        run: aws deploy create-deployment --application-name ${{ secrets.AWS_CODEDEPLOY_NAME }} --deployment-config-name CodeDeployDefault.AllAtOnce --deployment-group-name ${{ secrets.AWS_CODEDEPLOY_GROUP }} --s3-location bucket=${{ secrets.AWS_S3_BUCKET }},bundleType=zip,key=deploy/$GITHUB_SHA.zip
```

appspec.yml

```yml
version: 0.0
os: linux
files:
  - source: /
    destination: /home/ec2-user/app/
    overwrite: yes

permissions:
  - object: /
    pattern: "**"
    owner: ec2-user
    group: ec2-user

hooks:
  AfterInstall:
    - location: scripts/change_permissions.sh       # codeDeploy에 의해 파일이 옮겨지면 수행될 작업
      timeout: 20
      runas: ec2-user
  ApplicationStart:
    - location: scripts/deploy.sh                   # sh 파일을 deploy 시킨다.
      timeout: 60
      runas: ec2-user
```

change_permission.sh

```shell
#!/bin/bash

echo "> Grant Permissions files in app"

sudo chmod -R +x /home/ec2-user/app/
```

deploy.sh

```shell
#!/bin/bash

echo "Implement deploy process"

echo "Grant permession"

sudo chmod 755 /home/ec2-user/app/simms.zip

echo "PID Check..."

CURRENT_PID=$(ps -ef | grep tomcat | grep -v grep | grep -v vi | awk '{print $2}')

echo "> find if tomcat is runing {$CURRENT_PID}"

if [ -z "$CURRENT_PID" ] ; then
    echo "Project is not running"
else
    kill -9 "$CURRENT_PID"
    sleep 10
fi

echo "Unzip deploy zip file"

unzip /home/ec2-user/app/simms.zip

echo "Proceed Application"

sh ./home/ec2-user/app/simms/bin/startup.sh
```

CodeDeploy 로그 확인

```cmd
tail -f /var/log/aws/codedeploy-agent/codedeploy-agent.log 
```

