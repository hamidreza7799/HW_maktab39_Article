package repositories;

import base.repositories.BaseRepository;
import domains.User;

public interface UserRepository extends BaseRepository<User , Integer> {

    User findByUsername(String username);
}
