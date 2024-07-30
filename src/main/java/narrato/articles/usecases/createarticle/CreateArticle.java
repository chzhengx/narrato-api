package narrato.articles.usecases.createarticle;

import narrato.articles.usecases.shared.models.Article;
import narrato.articles.usecases.shared.repo.FindArticleBySlugRepository;
import narrato.shared.StringUtils;
import narrato.users.usecases.shared.models.LoginUser;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class CreateArticle {
    private final CreateArticleRepository createArticleRepository;
    private final FindArticleBySlugRepository findArticleBySlugRepository;

    CreateArticle(
            CreateArticleRepository createArticleRepository, FindArticleBySlugRepository findArticleBySlugRepository) {
        this.createArticleRepository = createArticleRepository;
        this.findArticleBySlugRepository = findArticleBySlugRepository;
    }

    public Article execute(LoginUser loginUser, CreateArticleCmd cmd) {
        String slug = StringUtils.toSlug(cmd.title());
        Article article = new Article(
                slug,
                cmd.title(),
                cmd.description(),
                cmd.body(),
                cmd.tagList(),
                LocalDateTime.now(),
                null,
                false,
                0,
                null);
        createArticleRepository.createArticle(loginUser, article);
        return findArticleBySlugRepository.findArticleBySlug(loginUser, slug).orElseThrow();
    }
}
