package narrato.users.usecases.registration;

import static narrato.jooq.models.tables.Users.USERS;

import narrato.users.usecases.shared.models.User;
import java.time.LocalDateTime;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
class UserRegistrationRepository {
    private final DSLContext dsl;

    UserRegistrationRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public User createUser(User user) {
        Long id = dsl.insertInto(USERS)
                .set(USERS.EMAIL, user.email())
                .set(USERS.PASSWORD, user.password())
                .set(USERS.USERNAME, user.username())
                .set(USERS.CREATED_AT, LocalDateTime.now())
                .returning(USERS.ID)
                .fetchOne(USERS.ID);
        return user.withId(id);
    }
}
