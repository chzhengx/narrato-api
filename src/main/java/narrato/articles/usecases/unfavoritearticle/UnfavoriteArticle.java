package narrato.articles.usecases.unfavoritearticle;

import java.util.Optional;
import narrato.articles.usecases.shared.models.Article;
import narrato.articles.usecases.shared.repo.FindArticleBySlugRepository;
import narrato.users.usecases.shared.models.LoginUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class UnfavoriteArticle {
    private final UnfavoriteArticleRepository unfavoriteArticleRepository;
    private final FindArticleBySlugRepository findArticleBySlug;

    UnfavoriteArticle(
            UnfavoriteArticleRepository unfavoriteArticleRepository, FindArticleBySlugRepository findArticleBySlug) {
        this.unfavoriteArticleRepository = unfavoriteArticleRepository;
        this.findArticleBySlug = findArticleBySlug;
    }

    public Optional<Article> execute(LoginUser loginUser, String slug) {
        Long articleId = findArticleBySlug.getArticleMetadataBySlugOrThrow(slug).articleId();

        unfavoriteArticleRepository.unfavoriteArticle(loginUser, articleId);
        return findArticleBySlug.findArticleBySlug(loginUser, slug);
    }
}
