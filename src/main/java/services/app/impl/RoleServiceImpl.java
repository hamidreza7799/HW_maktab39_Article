package services.app.impl;

import base.services.impl.BaseServiceImpl;
import domains.app.Role;
import repositories.app.RoleRepository;
import services.app.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role,Integer, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository){
        super(repository);
    }

    @Override
    public Role findByTitle(String title) {
        return this.repository.findByTitle(title);
    }
}
