package narrato.articles.usecases.shared.models;

import narrato.users.usecases.shared.models.Profile;
import java.time.LocalDateTime;
import java.util.List;

public record Article(
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount,
        Profile author) {}
