package services;

import base.services.BaseService;
import domains.app.User;

public interface UserService extends BaseService<User,Integer> {

    User signUp(RoleService roleService);

    User logIn(String role);

    User createWriterUser(RoleService roleService);

    User findByUsername(String username);
}
