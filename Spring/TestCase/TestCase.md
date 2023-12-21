### 상황별 테스트케이스

목적 : 각기 다른 테스트 케이스 상황을 작성하여 상황에 관련된 테스트 케이스를 작성하는 요령을 습득하자

#### Service Layer

```java
@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)                         // MokitoExtension
class ArticleServiceTest {

    @InjectMocks                                            // test 할 service를 injectMock으로 선언한다.
    private ArticleService sut;                             // 테스트될 필드를 명시할떄 sut를 사용한다.

    @Mock                                                   // Mocking할 Repository를 잡는다.
    private ArticleRepository articleRepository;                
    @Mock
    private UserAccountRepository userAccountRepository;

    /**
     * 
     * 조회시 
     * 
    */


    /**
     * 
     * 등록시
     * 
    */

    @DisplayName("게시글을 id로 조회하면 댓글이 달린 게시글을 반환한다.") // 첨부파일 추가해야 한다.
    @Test
    public void givenArticleId_whenCallingGetArticle_thenReturnArticle() {
        // given
        long articleId = 1L;
        Article article = createArticle();
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        // when
        ArticleWithCommentsDto dto = sut.getArticle(articleId);

        // then
        // 필드 내부의 속성을 확인
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent());

        // given에서 주어진 상황에서 then의 수행이 잘 됬는지 확인
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("게시글이 없으면, 예외를 던진다.")
    @Test
    public void givenArticleId_whenCallingGetArticle_thenThrowException() {
        // given
        long articleId = 0L;
        given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> sut.getArticle(articleId));

        // then
        assertThat(throwable)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("게시글을 찾지 못했습니다. articleId : " + articleId);

        then(articleRepository).should().findById(articleId);
    }


}
```
