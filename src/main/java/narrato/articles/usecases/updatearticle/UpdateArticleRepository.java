package narrato.articles.usecases.updatearticle;

import static narrato.jooq.models.tables.Articles.ARTICLES;

import java.time.LocalDateTime;
import java.util.Objects;
import narrato.jooq.models.tables.records.ArticlesRecord;
import narrato.shared.BadRequestException;
import narrato.shared.ResourceNotFoundException;
import narrato.shared.StringUtils;
import narrato.users.usecases.shared.models.LoginUser;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Repository;

@Repository
class UpdateArticleRepository {
    private static final Logger log = LoggerFactory.getLogger(UpdateArticleRepository.class);
    private final DSLContext dsl;

    UpdateArticleRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public String updateArticle(LoginUser loginUser, UpdateArticleCmd cmd) {
        log.info("Updating article with slug {} by userId:{}", cmd.slug(), loginUser.id());
        ArticlesRecord record = dsl.selectFrom(ARTICLES)
                .where(ARTICLES.SLUG.equalIgnoreCase(cmd.slug()))
                .fetchOne();
        if (record == null) {
            throw new ResourceNotFoundException("Article with id '" + cmd.slug() + "' does not exist");
        }
        if (!Objects.equals(record.getAuthorId(), loginUser.id())) {
            throw new AccessDeniedException("Access Denied");
        }
        String slug = cmd.slug();
        if (cmd.title() != null && !cmd.title().isEmpty()) {
            record.set(ARTICLES.TITLE, cmd.title());
            slug = StringUtils.toSlug(cmd.title());
            record.set(ARTICLES.SLUG, slug);
        }
        if (cmd.description() != null && !cmd.description().isEmpty()) {
            record.set(ARTICLES.DESCRIPTION, cmd.description());
        }
        if (cmd.body() != null && !cmd.body().isEmpty()) {
            record.set(ARTICLES.CONTENT, cmd.body());
        }
        record.set(ARTICLES.UPDATED_AT, LocalDateTime.now());
        try {
            dsl.executeUpdate(record);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("uk_articles_title")) {
                throw new BadRequestException("Article with title '" + cmd.title() + "' already exists");
            }
            if (e.getMessage().contains("uk_articles_slug")) {
                throw new BadRequestException("Article with slug '" + cmd.slug() + "' already exists");
            }
            throw new BadRequestException("Cannot update article");
        }
        return slug;
    }
}
