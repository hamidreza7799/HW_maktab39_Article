package services.impl;

import domains.Article;
import domains.User;
import repositories.UserRepository;
import repositories.WriterRepository;
import services.ArticleService;
import services.CategoryService;
import services.TagService;
import services.WriterService;

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
