package narrato.articles.usecases.gettags;

import static narrato.jooq.models.tables.Tags.TAGS;

import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
class GetTagsRepository {
    private final DSLContext dsl;

    GetTagsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<String> findAllTags() {
        return dsl.select(TAGS.NAME).from(TAGS).fetch(TAGS.NAME);
    }
}
