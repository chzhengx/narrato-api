/*
 * This file is generated by jOOQ.
 */
package narrato.jooq.models.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import narrato.jooq.models.Keys;
import narrato.jooq.models.Public;
import narrato.jooq.models.tables.records.ArticleFavoriteRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ArticleFavorite extends TableImpl<ArticleFavoriteRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.article_favorite</code>
     */
    public static final ArticleFavorite ARTICLE_FAVORITE = new ArticleFavorite();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ArticleFavoriteRecord> getRecordType() {
        return ArticleFavoriteRecord.class;
    }

    /**
     * The column <code>public.article_favorite.article_id</code>.
     */
    public final TableField<ArticleFavoriteRecord, Long> ARTICLE_ID = createField(DSL.name("article_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.article_favorite.user_id</code>.
     */
    public final TableField<ArticleFavoriteRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.article_favorite.created_at</code>.
     */
    public final TableField<ArticleFavoriteRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private ArticleFavorite(Name alias, Table<ArticleFavoriteRecord> aliased) {
        this(alias, aliased, null);
    }

    private ArticleFavorite(Name alias, Table<ArticleFavoriteRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.article_favorite</code> table reference
     */
    public ArticleFavorite(String alias) {
        this(DSL.name(alias), ARTICLE_FAVORITE);
    }

    /**
     * Create an aliased <code>public.article_favorite</code> table reference
     */
    public ArticleFavorite(Name alias) {
        this(alias, ARTICLE_FAVORITE);
    }

    /**
     * Create a <code>public.article_favorite</code> table reference
     */
    public ArticleFavorite() {
        this(DSL.name("article_favorite"), null);
    }

    public <O extends Record> ArticleFavorite(Table<O> child, ForeignKey<O, ArticleFavoriteRecord> key) {
        super(child, key, ARTICLE_FAVORITE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ArticleFavoriteRecord> getPrimaryKey() {
        return Keys.ARTICLE_FAVORITE_PKEY;
    }

    @Override
    public List<ForeignKey<ArticleFavoriteRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ARTICLE_FAVORITE__FK_ARTICLE_FAVORITE_ARTICLE_ID, Keys.ARTICLE_FAVORITE__FK_ARTICLE_FAVORITE_USER_ID);
    }

    private transient Articles _articles;
    private transient Users _users;

    /**
     * Get the implicit join path to the <code>public.articles</code> table.
     */
    public Articles articles() {
        if (_articles == null)
            _articles = new Articles(this, Keys.ARTICLE_FAVORITE__FK_ARTICLE_FAVORITE_ARTICLE_ID);

        return _articles;
    }

    /**
     * Get the implicit join path to the <code>public.users</code> table.
     */
    public Users users() {
        if (_users == null)
            _users = new Users(this, Keys.ARTICLE_FAVORITE__FK_ARTICLE_FAVORITE_USER_ID);

        return _users;
    }

    @Override
    public ArticleFavorite as(String alias) {
        return new ArticleFavorite(DSL.name(alias), this);
    }

    @Override
    public ArticleFavorite as(Name alias) {
        return new ArticleFavorite(alias, this);
    }

    @Override
    public ArticleFavorite as(Table<?> alias) {
        return new ArticleFavorite(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ArticleFavorite rename(String name) {
        return new ArticleFavorite(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ArticleFavorite rename(Name name) {
        return new ArticleFavorite(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ArticleFavorite rename(Table<?> name) {
        return new ArticleFavorite(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super Long, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super Long, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
