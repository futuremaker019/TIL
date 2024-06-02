  ```mermaid
  sequenceDiagram
    autonumber
    client ->> server: 회원가입 요청
    alt 성공한 경우
    server -->> client: 성공 반환
    else 아이디가 중복된 경우 
    server -->> client: reason code와 함께 실패 반환
    end
```

git 에서는 `mermaid`를 바로 적용하여 flowchart를 만들수 있다. 

[Mermaid Github 사이트 바로가기](https://github.com/mermaid-js/mermaid#sequence-diagram-docs---live-editor)
