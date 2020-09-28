package services.app.impl;

import domains.app.Article;
import domains.app.User;
import repositories.app.WriterRepository;
import services.app.ArticleService;
import services.app.CategoryService;
import services.app.TagService;
import services.app.WriterService;

public class WriterServiceImpl extends UserServiceImpl implements WriterService {

    public WriterServiceImpl(WriterRepository repository) {
        super(repository);
    }

    @Override
    public void createNewArticle(ArticleService articleService , User user , CategoryService categoryService ,TagService tagService) {
        articleService.createNewArticle(this,user,categoryService,tagService);
    }

    @Override
    public void editArticle(ArticleService articleService , User user) {
        Article customArticle = articleService.loadUserArticle(user);
        if(customArticle != null){
            articleService.editArticle(customArticle);
        }
    }

    @Override
    public boolean printUserArticles(ArticleService articleService , User user) {
        return articleService.printArticle(user.getUsername());
    }
}
