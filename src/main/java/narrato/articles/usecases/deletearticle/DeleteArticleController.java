package narrato.articles.usecases.deletearticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Objects;
import narrato.articles.usecases.shared.repo.FindArticleBySlugRepository;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.LoginUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeleteArticleController {
    private final AuthService authService;
    private final DeleteArticleRepository deleteArticle;
    private final FindArticleBySlugRepository findArticleBySlug;

    DeleteArticleController(
            AuthService authService,
            DeleteArticleRepository deleteArticle,
            FindArticleBySlugRepository findArticleBySlug) {
        this.authService = authService;
        this.deleteArticle = deleteArticle;
        this.findArticleBySlug = findArticleBySlug;
    }

    @DeleteMapping("/api/articles/{slug}")
    @Operation(summary = "Delete Article", tags = "Articles")
    @SecurityRequirement(name = "Token")
    void delete(@PathVariable String slug) {
        LoginUser loginUser = authService.getCurrentUserOrThrow();
        var articleAuthor = findArticleBySlug.getArticleMetadataBySlugOrThrow(slug);
        Long articleId = articleAuthor.articleId();
        if (!Objects.equals(articleAuthor.authorId(), loginUser.id())) {
            throw new AccessDeniedException("Access Denied");
        }
        deleteArticle.deleteArticle(loginUser, articleId);
    }
}
