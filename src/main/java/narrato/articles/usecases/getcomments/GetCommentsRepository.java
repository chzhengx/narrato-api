package narrato.articles.usecases.getcomments;

import static narrato.jooq.models.tables.Comments.COMMENTS;
import static narrato.jooq.models.tables.UserFollower.USER_FOLLOWER;
import static narrato.jooq.models.tables.Users.USERS;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.selectCount;

import narrato.articles.usecases.shared.models.Comment;
import narrato.users.usecases.shared.models.LoginUser;
import narrato.users.usecases.shared.models.Profile;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
class GetCommentsRepository {
    private final DSLContext dsl;

    GetCommentsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public MultipleComments getComments(LoginUser loginUser, Long articleId) {
        Long loginUserId = loginUser != null ? loginUser.id() : -1;
        var comments = dsl.select(
                        COMMENTS.ID,
                        COMMENTS.CONTENT,
                        COMMENTS.CREATED_AT,
                        COMMENTS.UPDATED_AT,
                        USERS.USERNAME,
                        USERS.BIO,
                        USERS.IMAGE,
                        field(selectCount()
                                        .from(USER_FOLLOWER.where(USER_FOLLOWER
                                                .FROM_ID
                                                .eq(loginUserId)
                                                .and(USER_FOLLOWER.TO_ID.eq(USERS.ID)))))
                                .as("FOLLOWING"))
                .from(COMMENTS.join(USERS).on(COMMENTS.AUTHOR_ID.eq(USERS.ID)))
                .where(COMMENTS.ARTICLE_ID.eq(articleId))
                .fetch(r -> new Comment(
                        r.get(COMMENTS.ID),
                        r.get(COMMENTS.CONTENT),
                        r.get(COMMENTS.CREATED_AT),
                        r.get(COMMENTS.UPDATED_AT),
                        new Profile(
                                r.get(USERS.USERNAME),
                                r.get(USERS.BIO),
                                r.get(USERS.IMAGE),
                                r.get("FOLLOWING", Integer.class) > 0)));
        return new MultipleComments(comments);
    }
}
