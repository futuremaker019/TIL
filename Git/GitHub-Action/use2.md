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
  # typescript & scss build for js, css
  build-client:
    name: Build client
    runs-on: ubuntu-latest
    env:
      working-directory: ./client/ts
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
  assemble-project:
    name: Assemble project
    runs-on: ubuntu-latest
    needs: [build-client, build-server]
    steps:
      - uses: actions/checkout@v3
      - name: Assembly applied
        run: mvn assembly:single
```
