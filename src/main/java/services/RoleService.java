package services;

import base.services.BaseService;
import domains.Role;

public interface RoleService extends BaseService<Role,Integer> {

    Role findByTitle(String title);
}
