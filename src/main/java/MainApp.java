import domains.User;
import helper.SingleTonScanner;
import repositories.*;
import repositories.impl.*;
import services.*;
import services.impl.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    private static ArticleRepository articleRepository;
    private static ArticleService articleService;
    private static CategoryRepository categoryRepository;
    private static CategoryService categoryService;
    private static WriterRepository writerRepository;
    private static WriterService writerService;
    private static AdminRepository adminRepository;
    private static AdminService adminService;
    private static RoleRepository roleRepository;
    private static RoleService roleService;
    private static TagRepository tagRepository;
    private static TagService tagService;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            entityManager = entityManagerFactory.createEntityManager();
            articleRepository = new ArticleRepositoryImpl(entityManager);
            articleService = new ArticleServiceImpl(articleRepository);
            categoryRepository = new CategoryRepositoryImpl(entityManager);
            categoryService = new CategoryServiceImpl(categoryRepository);
            writerRepository = new WriterRepositoryImpl(entityManager);
            writerService = new WriterServiceImpl(writerRepository);
            adminRepository = new AdminRepositoryImpl(entityManager);
            adminService = new AdminServiceImpl(adminRepository);
            roleRepository = new RoleRepositoryImpl(entityManager);
            roleService = new RoleServiceImpl(roleRepository);
            tagRepository = new TagRepositoryImpl(entityManager);
            tagService = new TagServiceImpl(tagRepository);
            System.out.println("Connection is successful.......");
            System.out.println("App is running.....");
            while (startMenu()) ;
            System.out.println("Close App...");
            entityManager.close();
            entityManagerFactory.close();
        } catch (Exception ex) {
            System.out.println("Exception was occur......");
            if(entityManager != null){
                entityManager.close();
            }
            if (entityManagerFactory != null){
                entityManagerFactory.close();
            }
        }
    }

    public static boolean startMenu() {
        System.out.println("1 : Sign-Up user");
        System.out.println("2 : Log-In user");
        System.out.println("3 : See all articles");
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
