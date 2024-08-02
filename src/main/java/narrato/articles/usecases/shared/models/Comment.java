package narrato.articles.usecases.shared.models;

import java.time.LocalDateTime;
import narrato.users.usecases.shared.models.Profile;

public record Comment(Long id, String body, LocalDateTime createdAt, LocalDateTime updatedAt, Profile author) {
    public Comment withProfile(Profile profile) {
        return new Comment(id, body, createdAt, updatedAt, profile);
    }
}
