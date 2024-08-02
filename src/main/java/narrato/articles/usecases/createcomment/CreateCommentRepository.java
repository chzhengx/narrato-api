package narrato.articles.usecases.createcomment;

import static narrato.jooq.models.tables.Comments.COMMENTS;

import java.time.LocalDateTime;
import narrato.articles.usecases.shared.models.Comment;
import narrato.users.usecases.shared.models.LoginUser;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
class CreateCommentRepository {
    private final DSLContext dsl;

    CreateCommentRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Comment createComment(LoginUser loginUser, Long articleId, CreatedCommentCmd cmd) {

        return dsl.insertInto(COMMENTS)
                .set(COMMENTS.ARTICLE_ID, articleId)
                .set(COMMENTS.AUTHOR_ID, loginUser.id())
                .set(COMMENTS.CONTENT, cmd.body())
                .set(COMMENTS.CREATED_AT, LocalDateTime.now())
                .returning(COMMENTS.ID, COMMENTS.CONTENT, COMMENTS.CREATED_AT)
                .fetchOne(r -> new Comment(r.getId(), r.getContent(), r.getCreatedAt(), null, null));
    }
}
