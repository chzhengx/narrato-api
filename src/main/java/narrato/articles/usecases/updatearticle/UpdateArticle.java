package narrato.articles.usecases.updatearticle;

import narrato.articles.usecases.shared.models.Article;
import narrato.articles.usecases.shared.repo.FindArticleBySlugRepository;
import narrato.users.usecases.shared.models.LoginUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class UpdateArticle {
    private final FindArticleBySlugRepository findArticleBySlug;
    private final UpdateArticleRepository updateArticleRepository;

    UpdateArticle(FindArticleBySlugRepository findArticleBySlug, UpdateArticleRepository updateArticleRepository) {
        this.findArticleBySlug = findArticleBySlug;
        this.updateArticleRepository = updateArticleRepository;
    }

    public Article execute(LoginUser loginUser, UpdateArticleCmd cmd) {
        String currentSlug = updateArticleRepository.updateArticle(loginUser, cmd);
        return findArticleBySlug.findArticleBySlug(loginUser, currentSlug).orElseThrow();
    }
}
