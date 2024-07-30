package narrato.articles.usecases.unfavoritearticle;

import static narrato.jooq.models.tables.ArticleFavorite.ARTICLE_FAVORITE;

import narrato.users.usecases.shared.models.LoginUser;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
class UnfavoriteArticleRepository {
    private final DSLContext dsl;

    UnfavoriteArticleRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void unfavoriteArticle(LoginUser loginUser, Long articleId) {
        dsl.deleteFrom(ARTICLE_FAVORITE)
                .where(ARTICLE_FAVORITE.ARTICLE_ID.eq(articleId).and(ARTICLE_FAVORITE.USER_ID.eq(loginUser.id())))
                .execute();
    }
}
