package narrato.articles.usecases.getcomments;

import narrato.articles.usecases.shared.models.Comment;
import java.util.List;

record MultipleComments(List<Comment> comments) {}
