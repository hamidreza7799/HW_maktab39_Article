package repositories.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.User;
import repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public abstract class UserRepositoryImpl extends BaseRepositoryImpl<User,Integer> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    @Override
    public User findByUsername(String username) {
        try {
            TypedQuery<User> findUser = this.entityManager.createQuery("select u from User as u where u.username=:username", User.class);
            findUser.setParameter("username", username);
            return findUser.getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
