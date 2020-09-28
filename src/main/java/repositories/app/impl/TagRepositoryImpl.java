package repositories.app.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.app.Tag;
import repositories.app.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class TagRepositoryImpl extends BaseRepositoryImpl<Tag,Integer> implements TagRepository {

    public TagRepositoryImpl(EntityManager entityManager) {
        super(Tag.class, entityManager);
    }

    @Override
    public Tag findByTitle(String title) {
        try {
            TypedQuery<Tag> findCategory = this.entityManager.createQuery("select t from Tag as t where t.title=:title", Tag.class);
            findCategory.setParameter("title", title);
            return findCategory.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
