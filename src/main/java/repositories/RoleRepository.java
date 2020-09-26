package repositories;

import base.repositories.BaseRepository;
import domains.Role;

public interface RoleRepository extends BaseRepository<Role,Integer> {

    Role findByTitle(String title);
}
