package repositories.app;

import base.repositories.BaseRepository;
import domains.app.Role;

public interface RoleRepository extends BaseRepository<Role,Integer> {

    Role findByTitle(String title);
}
