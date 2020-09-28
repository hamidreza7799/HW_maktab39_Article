package repositories.app;

import base.repositories.BaseRepository;
import domains.app.Category;

public interface CategoryRepository extends BaseRepository<Category,Integer> {

    Category findByTitle(String title);
}
