package services.impl;

import domains.*;
import helper.SingleTonScanner;
import repositories.AdminRepository;
import services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminServiceImpl extends WriterServiceImpl implements AdminService {

    public AdminServiceImpl(AdminRepository repository){
        super(repository);

    }

    @Override
    public boolean createNewCategory(CategoryService categoryService) {
        try {
            Category newCategory = categoryService.createNewCategory();
            if (newCategory != null)
                return true;
            else
                return false;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean createNewTag(TagService tagService) {
        Tag newTag = tagService.createNewTag();
        if(newTag != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean createNewAdminUser(RoleService roleService) {
        Role adminRole = roleService.findByTitle("Admin");
        Role writerRole = roleService.findByTitle("Writer");
        if(writerRole == null || adminRole == null)
            return false;
        Set<Role> newUserRole = new HashSet<>();
        newUserRole.add(adminRole);
        newUserRole.add(writerRole);
        System.out.print("Enter a username: ");
        String username = SingleTonScanner.getScanner().nextLine();
        User repetitiveUser = this.findByUsername(username);
        while (repetitiveUser != null) {
            System.out.println("This username was been chosen");
            System.out.print("Enter another username: ");
            username = SingleTonScanner.getScanner().nextLine();
            repetitiveUser = this.findByUsername(username);
        }
        System.out.print("Enter your national-code: ");
        String nationalCode = SingleTonScanner.getScanner().nextLine();
        //get birthday
        User newUser = new User();
        newUser.setUserRoles(newUserRole);
        newUser.setUsername(username);
        newUser.setNationalCode(nationalCode);
        newUser.setBirthday(null);
        newUser.setPassword(nationalCode);
        this.save(newUser);
        return true;
    }

    @Override
    public boolean changeUserRole() {
        return false;
    }

    @Override
    public boolean removeArticle(ArticleService articleService) {
        if(this.printWritersArticles(articleService))
            return false;
        else{
            System.out.print("Enter id of article: ");
            Integer id;
            try {
                id = SingleTonScanner.getScanner().nextInt();
                return articleService.deleteById(id);
            }catch (Exception ex){
                return false;
            }
        }
    }

    @Override
    public boolean changeArticlePublishSate(ArticleService articleService) {
        if(this.printWritersArticles(articleService))
            return false;
        else{
            System.out.print("Enter id of article: ");
            Integer id;
            try {
                id = SingleTonScanner.getScanner().nextInt();
                Article customArticle = articleService.findById(id);
                return articleService.changeArticlePublishState(customArticle);
            }catch (Exception ex){
                return false;
            }
        }
    }

    @Override
    public boolean printWritersArticles(ArticleService articleService) {
        Set<User> WriteUsers = this.findAll();
        boolean isEmpty = true;
        for(User user:WriteUsers)
            isEmpty = isEmpty && articleService.printArticle(user.getUsername());
        return isEmpty;
    }
}
