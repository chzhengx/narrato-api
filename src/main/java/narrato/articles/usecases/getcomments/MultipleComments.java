package narrato.articles.usecases.getcomments;

import java.util.List;
import narrato.articles.usecases.shared.models.Comment;

record MultipleComments(List<Comment> comments) {}
