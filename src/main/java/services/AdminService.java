package services;

public interface AdminService extends WriterService{

    boolean createNewCategory(CategoryService categoryService);

    boolean createNewTag(TagService tagService);

    boolean createNewAdminUser(RoleService roleService);

    boolean changeUserRole();

    boolean removeArticle(ArticleService articleService);

    boolean changeArticlePublishSate(ArticleService articleService);

    boolean printWritersArticles(ArticleService articleService);
}
