/*
 * This file is generated by jOOQ.
 */
package narrato.jooq.models.tables.records;


import java.time.LocalDateTime;

import narrato.jooq.models.tables.ArticleFavorite;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ArticleFavoriteRecord extends UpdatableRecordImpl<ArticleFavoriteRecord> implements Record3<Long, Long, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.article_favorite.article_id</code>.
     */
    public void setArticleId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.article_favorite.article_id</code>.
     */
    public Long getArticleId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.article_favorite.user_id</code>.
     */
    public void setUserId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.article_favorite.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.article_favorite.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.article_favorite.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, Long, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ArticleFavorite.ARTICLE_FAVORITE.ARTICLE_ID;
    }

    @Override
    public Field<Long> field2() {
        return ArticleFavorite.ARTICLE_FAVORITE.USER_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return ArticleFavorite.ARTICLE_FAVORITE.CREATED_AT;
    }

    @Override
    public Long component1() {
        return getArticleId();
    }

    @Override
    public Long component2() {
        return getUserId();
    }

    @Override
    public LocalDateTime component3() {
        return getCreatedAt();
    }

    @Override
    public Long value1() {
        return getArticleId();
    }

    @Override
    public Long value2() {
        return getUserId();
    }

    @Override
    public LocalDateTime value3() {
        return getCreatedAt();
    }

    @Override
    public ArticleFavoriteRecord value1(Long value) {
        setArticleId(value);
        return this;
    }

    @Override
    public ArticleFavoriteRecord value2(Long value) {
        setUserId(value);
        return this;
    }

    @Override
    public ArticleFavoriteRecord value3(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public ArticleFavoriteRecord values(Long value1, Long value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ArticleFavoriteRecord
     */
    public ArticleFavoriteRecord() {
        super(ArticleFavorite.ARTICLE_FAVORITE);
    }

    /**
     * Create a detached, initialised ArticleFavoriteRecord
     */
    public ArticleFavoriteRecord(Long articleId, Long userId, LocalDateTime createdAt) {
        super(ArticleFavorite.ARTICLE_FAVORITE);

        setArticleId(articleId);
        setUserId(userId);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }
}
