package narrato.articles.usecases.shared.models;

import narrato.users.usecases.shared.models.Profile;
import java.time.LocalDateTime;

public record Comment(Long id, String body, LocalDateTime createdAt, LocalDateTime updatedAt, Profile author) {
    public Comment withProfile(Profile profile) {
        return new Comment(id, body, createdAt, updatedAt, profile);
    }
}
