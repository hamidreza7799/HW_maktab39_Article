package services;

import base.services.BaseService;
import domains.Article;
import domains.User;

import java.io.IOException;
import java.util.List;

public interface ArticleService extends BaseService<Article,Integer> {

    boolean printArticle();

    boolean printArticle(int articleId);

    boolean printArticle(String username);

    boolean printArticle( String username , int articleId);

    void createNewArticle(UserService userService , User creator , CategoryService categoryService , TagService tagService);

    Article loadUserArticle(User user);

    void editArticle(Article article);

    boolean changeArticlePublishState(Article article);

    List<Article> findByCreatorUsername(String username);

    Article findByCreatorUsernameArticleId(String username , int articleId);
}
