package narrato.articles.usecases.favoritearticle;

import narrato.articles.usecases.shared.models.Article;
import narrato.articles.usecases.shared.repo.FindArticleBySlugRepository;
import narrato.users.usecases.shared.models.LoginUser;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class FavoriteArticle {
    private final FavoriteArticleRepository favoriteArticleRepository;
    private final FindArticleBySlugRepository findArticleBySlug;

    public FavoriteArticle(
            FavoriteArticleRepository favoriteArticleRepository, FindArticleBySlugRepository findArticleBySlug) {
        this.favoriteArticleRepository = favoriteArticleRepository;
        this.findArticleBySlug = findArticleBySlug;
    }

    public Optional<Article> execute(LoginUser loginUser, String slug) {
        Long articleId = findArticleBySlug.getArticleMetadataBySlugOrThrow(slug).articleId();
        favoriteArticleRepository.favoriteArticle(loginUser, articleId);
        return findArticleBySlug.findArticleBySlug(loginUser, slug);
    }
}
