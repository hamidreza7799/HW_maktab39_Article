package services;

import domains.app.User;

public interface WriterService extends UserService{

    void createNewArticle(ArticleService articleService , User user , CategoryService categoryService , TagService tagService);

    void editArticle(ArticleService articleService , User user);

    boolean printUserArticles(ArticleService articleService , User user);
}
