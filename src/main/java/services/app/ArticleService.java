package services.app;

import base.services.BaseService;
import domains.app.Article;
import domains.app.User;

import java.util.Set;

public interface ArticleService extends BaseService<Article,Integer> {

    boolean printArticle();

    boolean printArticle(int articleId);

    boolean printArticle(String username);

    boolean printArticle( String username , int articleId);

    void createNewArticle(UserService userService , User creator , CategoryService categoryService , TagService tagService);

    Article loadUserArticle(User user);

    void editArticle(Article article);

    boolean changeArticlePublishState(Article article);

    Set<Article> findByCreatorUsername(String username);

    Article findByCreatorUsernameArticleId(String username , int articleId);
}
