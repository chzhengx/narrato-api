package narrato.articles.usecases.unfavoritearticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import narrato.articles.usecases.shared.models.SingleArticleResponse;
import narrato.users.AuthService;
import narrato.users.usecases.shared.models.LoginUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UnfavoriteArticleController {
    private final UnfavoriteArticle unfavoriteArticle;
    private final AuthService authService;

    UnfavoriteArticleController(UnfavoriteArticle unfavoriteArticle, AuthService authService) {
        this.unfavoriteArticle = unfavoriteArticle;
        this.authService = authService;
    }

    @DeleteMapping("/api/articles/{slug}/favorite")
    @Operation(summary = "Unfavourite Article", tags = "Favorites")
    @SecurityRequirement(name = "Token")
    SingleArticleResponse unfavoriteArticle(@PathVariable String slug) {
        LoginUser loginUser = authService.getCurrentUserOrThrow();
        var article = unfavoriteArticle.execute(loginUser, slug).orElseThrow();
        return new SingleArticleResponse(article);
    }
}
