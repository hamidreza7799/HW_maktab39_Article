import domains.app.User;
import domains.metadata.AppMetadata;
import domains.metadata.TableColumnMetadata;
import domains.metadata.TableMetadata;
import helper.SingleTonScanner;
import repositories.app.*;
import repositories.app.impl.*;
import repositories.metadata.AppMetadataRepository;
import repositories.metadata.TableColumnMetadataRepository;
import repositories.metadata.TableMetadataRepository;
import repositories.metadata.impl.AppMetadataRepositoryImpl;
import repositories.metadata.impl.TableColumnRepositoryImpl;
import repositories.metadata.impl.TableMetadataRepositoryImpl;
import services.app.*;
import services.app.impl.*;
import services.metadata.AppMetadataService;
import services.metadata.TableColumnMetadataService;
import services.metadata.TableMetadataService;
import services.metadata.impl.AppMetadataServiceImpl;
import services.metadata.impl.TableColumnMetadataServiceImpl;
import services.metadata.impl.TableMetadataServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainApp {
    private static ArticleService articleService;
    private static CategoryService categoryService;
    private static WriterService writerService;
    private static AdminService adminService;
    private static RoleService roleService;
    private static TagService tagService;
    private static AppMetadataService appMetadataService;
    private static TableMetadataService tableMetadataService;
    private static TableColumnMetadataService tableColumnMetadataService;

    public static void main(String[] args) {
        EntityManagerFactory dataEntityManagerFactory = null;
        EntityManager dataEntityManager = null;
        EntityManagerFactory metadataEntityManagerFactory = null;
        EntityManager metadataEntityManager = null;
        try{
            dataEntityManagerFactory = Persistence.createEntityManagerFactory("data-persistence-unit");
            metadataEntityManagerFactory = Persistence.createEntityManagerFactory("metadata-persistence-unit");
            dataEntityManager = dataEntityManagerFactory.createEntityManager();
            metadataEntityManager = metadataEntityManagerFactory.createEntityManager();
            ArticleRepositoryImpl articleRepository = new ArticleRepositoryImpl(dataEntityManager);
            articleService = new ArticleServiceImpl(articleRepository);
            CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl(dataEntityManager);
            categoryService = new CategoryServiceImpl(categoryRepository);
            WriterRepositoryImpl writerRepository = new WriterRepositoryImpl(dataEntityManager);
            writerService = new WriterServiceImpl(writerRepository);
            AdminRepositoryImpl adminRepository = new AdminRepositoryImpl(dataEntityManager);
            adminService = new AdminServiceImpl(adminRepository);
            RoleRepositoryImpl roleRepository = new RoleRepositoryImpl(dataEntityManager);
            roleService = new RoleServiceImpl(roleRepository);
            TagRepositoryImpl tagRepository = new TagRepositoryImpl(dataEntityManager);
            tagService = new TagServiceImpl(tagRepository);
            AppMetadataRepository appMetadataRepository = new AppMetadataRepositoryImpl(metadataEntityManager);
            appMetadataService = new AppMetadataServiceImpl(appMetadataRepository);
            TableColumnMetadataRepository tableColumnMetadataRepository = new TableColumnRepositoryImpl(metadataEntityManager);
            tableColumnMetadataService= new TableColumnMetadataServiceImpl(tableColumnMetadataRepository);
            TableMetadataRepository tableMetadataRepository = new TableMetadataRepositoryImpl(metadataEntityManager);
            tableMetadataService = new TableMetadataServiceImpl(tableMetadataRepository);
            System.out.println("Connection is successful.......");
            System.out.println("App is running.....");
            while (startMenu()) ;
            System.out.println("Close App...");
            dataEntityManager.close();
            metadataEntityManager.close();
            dataEntityManagerFactory.close();
            metadataEntityManagerFactory.close();
        } catch (Exception ex) {
            System.out.println("Exception was occur......");
            if(dataEntityManager != null){
                dataEntityManager.close();
            }
            if (dataEntityManagerFactory != null){
                dataEntityManagerFactory.close();
            }
            if(metadataEntityManager != null){
                metadataEntityManager.close();
            }
            if (metadataEntityManagerFactory != null){
                metadataEntityManagerFactory.close();
            }
        }
    }

    public static boolean startMenu() {
        System.out.println("1 : Sign-Up user");
        System.out.println("2 : Log-In user");
        System.out.println("3 : See all articles");
        System.out.println("4 : Meta data menu");
        System.out.println("other character : close app");
        System.out.print("What do you want: ");
        switch (SingleTonScanner.getScanner().nextLine()) {
            case "1":
                writerApp(writerService.signUp(roleService));
                return true;
            case "2":
                String role;
                System.out.print("Log In as Writer or Admin: ");
                role = SingleTonScanner.getScanner().nextLine().toLowerCase();
                while (!role.equals("writer") && !role.equals("admin")){
                    System.out.println("Writer or Admin!!!");
                    role = SingleTonScanner.getScanner().nextLine().toLowerCase();
                }
                if(role.equals("writer"))
                    writerApp(writerService.logIn(role));
                else
                    adminApp(adminService.logIn(role));
                return true;
            case "3":
                showArticle();
                return true;

            case "4":
                User adminUser = adminService.logIn("Admin");
                metadataMenu(adminUser);
                return true;
            default:
                return false;

        }
    }

    private static void writerApp(User user) {
        if (user == null) {
            return;
        }
        while (writerMenu(user)) ;

    }

    private static boolean writerMenu(User user) {
        if (user == null) {
            System.out.println("Something was wrong.");
            return false;
        }
        System.out.println("1 : Print your articles");
        System.out.println("2 : Edit your articles");
        System.out.println("3 : Create new article");
        System.out.println("4 : Edit your profile");
        System.out.println("other character : back to start menu");
        System.out.print("What do you want: ");
        switch (SingleTonScanner.getScanner().nextLine()) {
            case "1":
                writerService.printUserArticles(articleService,user);
                return true;
            case "2":
                writerService.editArticle(articleService,user);
                return true;
            case "3":
                writerService.createNewArticle(articleService,user,categoryService,tagService);
                return true;
            case "4":
                return true;
            default:
                return false;

        }
    }

    private static void adminApp(User user){
        if (user == null) {
            return;
        }
        while (adminMenu(user)) ;
    }

    private static boolean adminMenu(User user) {
        if (user == null) {
            System.out.println("Something was wrong.");
            return false;
        }
        System.out.println("1 : Change publish state");
        System.out.println("2 : Remove article");
        System.out.println("3 : Create new category");
        System.out.println("4 : Create new tag");
        System.out.println("5 : Change users role");
        System.out.println("other character : back to start menu");
        System.out.print("What do you want: ");
        switch (SingleTonScanner.getScanner().nextLine()) {
            case "1":
                adminService.changeArticlePublishSate(articleService);
                return true;
            case "2":
                adminService.removeArticle(articleService);
                return true;
            case "3":
                adminService.createNewCategory(categoryService);
                return true;
            case "4":
                adminService.createNewTag(tagService);
                return true;
            case "5":
                adminService.changeUserRole();
                return true;
            default:
                return false;

        }
    }

    private static void metadataMenu(User adminUser){
        if (adminUser == null)
            return;

    }

    private static void showArticle() {
        boolean isEmpty = articleService.printArticle();
        if (!isEmpty) {
            while (true) {
                try {
                    System.out.println("Enter considered article id(write -1 for back to start menu): ");
                    Integer id = Integer.parseInt(SingleTonScanner.getScanner().nextLine());
                    if (id.equals(-1)) {
                        System.out.println("\t\r**********\t\rFinish show of articles\t\r**********");
                        return;
                    } else {
                        articleService.printArticle(id);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Please enter an integer");
                }
            }
        }


    }


}
