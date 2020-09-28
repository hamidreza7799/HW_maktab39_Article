package repositories.app;

import base.repositories.BaseRepository;
import domains.app.User;

public interface UserRepository extends BaseRepository<User , Integer> {

    User findByUsername(String username);
}
