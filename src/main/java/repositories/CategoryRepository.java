package repositories;

import base.repositories.BaseRepository;
import domains.Category;

public interface CategoryRepository extends BaseRepository<Category,Integer> {

    Category findByTitle(String title);
}
