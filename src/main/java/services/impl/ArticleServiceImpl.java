package services.impl;

import base.services.impl.BaseServiceImpl;
import domains.Article;
import domains.Category;
import domains.Tag;
import domains.User;
import helper.Editor;
import helper.SingleTonScanner;
import repositories.ArticleRepository;
import services.ArticleService;
import services.CategoryService;
import services.TagService;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArticleServiceImpl extends BaseServiceImpl<Article, Integer, ArticleRepository> implements ArticleService {

    public ArticleServiceImpl(ArticleRepository repository) {
        super(repository);
    }

    @Override
    public boolean printArticle() {
        System.out.println("\t\r**********\t\rArticles\t\r**********");
        boolean isEmpty = true;
        List<Article> articles = this.findAll();
        for (Article article : articles) {
            if (article.getIsPublished()) {
                isEmpty = false;
                System.out.println("The id of this article is: " + article.getId());
                System.out.println("Title of article is: " + article.getTitle());
                System.out.println("brief of article is: " + article.getBrief());
                System.out.println("\t\r-------------\t\r\t\r+++++++++++");
            }
        }
        if (isEmpty) {
            System.out.println("Sorry we dont have any article at now......");
        }
        System.out.println("\t\r**********\t\rArticles\t\r**********");
        return isEmpty;
    }

    @Override
    public boolean printArticle(int articleId) {
        Article customArticle = this.findById(articleId);
        if (customArticle != null && customArticle.getIsPublished()) {
            System.out.println("\t\r**********\t\r" + customArticle.getTitle() + "\t\r**********");
            System.out.println(customArticle.getContent());
            System.out.println("The user that created this article is: " + customArticle.getCreator().getUsername());
            System.out.println("The category of this article is: " + customArticle.getCategory().getTitle());
            System.out.println("The data of created: " + customArticle.getCreateDate());
            System.out.println("\t\r**********\t\r\t\r**********");
            return true;
        } else {
            System.out.println("This article not exists in data base!!!!");
            return false;
        }
    }

    @Override
    public boolean printArticle(String username){
        if(username == null)
            return false;
        System.out.println("\t\r**********\t\rYour-Articles\t\r**********");
        boolean isEmpty = true;
        List<Article> articles = this.findByCreatorUsername(username);
        for (Article article : articles) {
            isEmpty = false;
            System.out.println("The id of this article is: " + article.getId());
            System.out.println("Title of article is: " + article.getTitle());
            System.out.println("brief of article is: " + article.getBrief());
            System.out.println("\t\r-------------\t\r\t\r+++++++++++");

        }
        if (isEmpty) {
            System.out.println("Sorry you dont have any article at now......");
        }
        System.out.println("\t\r**********\t\rYour-Articles\t\r**********");
        return isEmpty;
    }

    @Override
    public boolean printArticle(String username ,int articleId) {
        if(username == null)
            return false;
        Article customArticle = this.findByCreatorUsernameArticleId(username , articleId);
        if (customArticle != null && customArticle.getIsPublished()) {
            System.out.println("\t\r**********\t\r" + customArticle.getTitle() + "\t\r**********");
            System.out.println(customArticle.getContent());
            System.out.println("The user that created this article is: " + customArticle.getCreator().getUsername());
            System.out.println("The category of this article is: " + customArticle.getCategory().getTitle());
            System.out.println("The data of created: " + customArticle.getCreateDate());
            System.out.println("\t\r**********\t\r\t\r**********");
            return true;
        } else {
            System.out.println("This article not exists in data base!!!!");
            return false;
        }
    }

    @Override
    public void createNewArticle(UserService userService , User creator , CategoryService categoryService , TagService tagService){
        try {
            User user = userService.findByUsername(creator.getUsername());
            if (user == null) {
                System.out.println("This username not exists!!!");
                return;
            }
            Category articleCategory = categoryService.chooseCategory();
            if(articleCategory == null){
                System.out.println("Because not exists any category,you can not create any article.....");
                return;
            }
            System.out.print("Enter the title of article: ");
            String articleTitle = SingleTonScanner.getScanner().nextLine();
            Editor editor = new Editor();
            System.out.print("Enter the brief of article.....");
            String brief = editor.openEditor("", "Brief_Of_Article");
            System.out.print("Enter the content of the article......");
            String content = editor.openEditor("", "Content_Of_Article");
            Set<Tag> articleTags = tagService.chooseTags();
            Article newArticle = new Article();
            newArticle.setTitle(articleTitle);
            newArticle.setBrief(brief);
            newArticle.setContent(content);
            newArticle.setCreator(creator);
            newArticle.setCategory(articleCategory);
            newArticle.setArticleTags(articleTags);
            this.save(newArticle);
        }catch (IOException ex){
            System.out.println("Exception in open editor");
        }
    }

    @Override
    public Article loadUserArticle(User user){
        if(user == null)
            return null;
        boolean isEmpty = this.printArticle(user.getUsername());
        if (!isEmpty) {
            try{
                System.out.print("Enter considered article id: ");
                Integer articleId = SingleTonScanner.getScanner().nextInt();
                return this.findById(articleId);

            }
            catch (Exception ex){
                System.out.println("Something was happened please try again...");
                return null;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public void editArticle(Article article){
        if(article == null)
            return;
        try {
            Editor editor = new Editor();
            System.out.print("Do you want change the title of article?(y/n): ");
            if ("y".equals(SingleTonScanner.getScanner().nextLine().toLowerCase())) {
                article.setTitle(editor.openEditor(article.getTitle(), "Title_Of_Article"));
            }
            System.out.print("Do you want change the brief of article?(y/n): ");
            if ("y".equals(SingleTonScanner.getScanner().nextLine().toLowerCase())) {
                article.setBrief(editor.openEditor(article.getBrief(), "Brief_Of_Article"));
            }
            System.out.print("Do you want change the content of article?(y/n): ");
            if ("y".equals(SingleTonScanner.getScanner().nextLine().toLowerCase())) {
                article.setContent(editor.openEditor(article.getContent(), "Content_Of_Article"));
            }
            this.save(article);
        }catch (IOException ex){
            System.out.println("Exception in open editor");
        }
    }

    @Override
    public boolean changeArticlePublishState(Article article) {
        if(article == null)
            return false;
        System.out.println("Article published: " + article.getIsPublished());
        System.out.print("Do you want change publish value of article?(y/n): ");
        if ("y".equals(SingleTonScanner.getScanner().nextLine().toLowerCase()))
            article.setPublished(!article.getIsPublished());
        else
            return false;
        this.save(article);
        return true;
    }

    @Override
    public List<Article> findByCreatorUsername(String username) {
        if(username == null)
            return new ArrayList<Article>();
        return this.repository.findByCreatorUsername(username);
    }

    @Override
    public Article findByCreatorUsernameArticleId(String username, int articleId) {
        if(username == null)
            return null;
        return this.repository.findByCreatorUsernameArticleId(username, articleId);
    }
}
