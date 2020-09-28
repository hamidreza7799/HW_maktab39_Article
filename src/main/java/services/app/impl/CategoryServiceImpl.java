package services.app.impl;

import base.services.impl.BaseServiceImpl;
import domains.app.Category;
import helper.Editor;
import helper.SingleTonScanner;
import repositories.app.CategoryRepository;
import services.app.CategoryService;

import java.io.IOException;
import java.util.Set;

public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer, CategoryRepository> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public boolean printCategories() {
        System.out.println("\t**********\tCategories\t**********");
        Set<Category> categories = this.findAll();
        for (Category category : categories) {
            System.out.println("Title is: " + category.getTitle());
            System.out.println("Description is: " + category.getDescription());
            System.out.println("\t\r-------------\t\r\t\r+++++++++++");
        }
        boolean isEmpty = categories.size() == 0;
        if (isEmpty) {
            System.out.println("We dont have any category in data-base....");
        }
        System.out.println("\t\r**********\t\rCategories\t\r**********");
        return isEmpty;

    }

    @Override
    public Category createNewCategory() throws IOException {
        Editor editor = new Editor();
        System.out.print("Enter title of category....");
        String title = SingleTonScanner.getScanner().nextLine();
        Category oldCategory = this.findByTitle(title);
        while (oldCategory != null) {
            System.out.println("This title was been chosen");
            System.out.print("Enter another title: ");
            title = SingleTonScanner.getScanner().nextLine();
            oldCategory = this.findByTitle(title);
        }
        System.out.print("Enter category description.......");
        String description = editor.openEditor("" , "Description_Of_Category");
        Category newCategory = new Category();
        newCategory.setTitle(title);
        newCategory.setDescription(description);
        this.save(newCategory);
        return newCategory;
    }

    @Override
    public Category chooseCategory() {
        boolean isEmpty = this.printCategories();
        if(isEmpty){
            return null;
        }
        else {
            System.out.print("Enter your custom category title: ");
            String title = SingleTonScanner.getScanner().nextLine();
            Category customCategory = this.findByTitle(title);
            while (customCategory == null) {
                System.out.println("This title not exists in data base");
                System.out.print("Enter right title: ");
                title = SingleTonScanner.getScanner().nextLine();
                customCategory = this.findByTitle(title);
            }
            return customCategory;
        }
    }

    @Override
    public Category findByTitle(String title) {
        return this.repository.findByTitle(title);
    }
}
