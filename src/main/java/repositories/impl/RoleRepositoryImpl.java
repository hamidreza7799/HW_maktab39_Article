package repositories.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.Role;
import repositories.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RoleRepositoryImpl extends BaseRepositoryImpl<Role,Integer> implements RoleRepository {

    public RoleRepositoryImpl(EntityManager entityManager){
        super(Role.class , entityManager);
    }

    @Override
    public Role findByTitle(String title) {
        try {
            TypedQuery<Role> findRole = this.entityManager.createQuery("select r from Role as r where r.title=:title" , Role.class);
            findRole.setParameter("title" , title);
            return findRole.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
