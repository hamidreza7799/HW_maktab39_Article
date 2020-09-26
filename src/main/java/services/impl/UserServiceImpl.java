package services.impl;

import base.services.impl.BaseServiceImpl;
import domains.Role;
import domains.User;
import helper.SingleTonScanner;
import repositories.UserRepository;
import services.ArticleService;
import services.RoleService;
import services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class UserServiceImpl extends BaseServiceImpl<User, Integer, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User signUp(RoleService roleService) {
        User newUser = createWriterUser(roleService);
        return newUser;
    }

    @Override
    public User logIn(String role) {
        System.out.print("Enter your username: ");
        String username = SingleTonScanner.getScanner().nextLine();
        System.out.print("Enter your password: ");
        String password = SingleTonScanner.getScanner().nextLine();
        User customUser = this.findByUsername(username);
        if (customUser == null) {
            System.out.println("username is not correct!!");
            return null;
        }
        if (!password.equals(customUser.getPassword())) {
            System.out.println("password is not correct!!");
            return null;
        }
        for(Role userRole : customUser.getUserRoles()){
            if(userRole.getTitle().toLowerCase().equals(role)){
                return customUser;
            }
        }
        System.out.println("This user have not " + role + " Role");
        return null;
    }

    @Override
    public User createWriterUser(RoleService roleService) {
        Role writerRole = roleService.findByTitle("Writer");
        if(writerRole == null)
            return null;
        List<Role> newUserRole = new ArrayList<>();
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
        return newUser;
    }

    @Override
    public User findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

}
