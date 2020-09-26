package services.impl;

import base.services.impl.BaseServiceImpl;
import domains.Role;
import repositories.RoleRepository;
import services.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role,Integer, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository repository){
        super(repository);
    }

    @Override
    public Role findByTitle(String title) {
        return this.repository.findByTitle(title);
    }
}
