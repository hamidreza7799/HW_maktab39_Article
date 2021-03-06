package services.app;

import base.services.BaseService;
import domains.app.Category;

import java.io.IOException;

public interface CategoryService extends BaseService<Category,Integer> {

    boolean printCategories();

    Category createNewCategory() throws IOException;

    Category chooseCategory();

    Category findByTitle(String title);
}
