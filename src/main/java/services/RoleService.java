package services;

import base.services.BaseService;
import domains.app.Role;

public interface RoleService extends BaseService<Role,Integer> {

    Role findByTitle(String title);
}
