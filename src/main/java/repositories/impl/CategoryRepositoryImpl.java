package repositories.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.Category;
import repositories.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category,Integer> implements CategoryRepository {

    public CategoryRepositoryImpl(EntityManager entityManager) {
        super(Category.class, entityManager);
    }

    @Override
    public Category findByTitle(String title) {
        try {
            TypedQuery<Category> findCategory = this.entityManager.createQuery("select c from Category as c where c.title=:title", Category.class);
            findCategory.setParameter("title", title);
            return findCategory.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
