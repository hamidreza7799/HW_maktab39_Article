package base.repositories.impl;

import base.domains.BaseEntity;
import base.repositories.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseRepositoryImpl<E extends BaseEntity<PK>, PK extends Serializable> implements BaseRepository<E , PK> {
    protected EntityManager entityManager;
    private final Class<E> classTypeParameter;

    public BaseRepositoryImpl(Class<E> classTypeParameter , EntityManager entityManager){
        this.classTypeParameter = classTypeParameter;
        this.entityManager = entityManager;
    }

    @Override
    public E save(E e) {
        try {
            this.entityManager.getTransaction().begin();
            if(e.getId() ==null)
                this.entityManager.persist(e);
            else
                this.entityManager.merge(e);
            this.entityManager.getTransaction().commit();
            return e;
        }
        catch (Exception ex){
            this.entityManager.getTransaction().rollback();
            System.out.println("This object not save");
            return null;
        }
    }

    @Override
    public E findById(PK id) {
        try {
            return this.classTypeParameter.cast(this.entityManager.find(this.classTypeParameter, id));
        }catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public boolean deleteById(PK id) {
        try {
            Object customObj = this.entityManager.find(this.classTypeParameter, id);
            this.entityManager.remove(customObj);
            return true;
        }catch (NoResultException ex){
            System.out.println("This object not in data base....");
            return false;
        }
    }

    @Override
    public Set<E> findAll() {
        try {
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteria = builder.createQuery(this.classTypeParameter);
            criteria.select(criteria.from(this.classTypeParameter));
            return new HashSet<>(this.entityManager.createQuery(criteria).getResultList());
        }catch (NoResultException ex){
            return new HashSet<>();
        }
    }

    @Override
    public Set<E> findAll(Predicate<E> filter) {
        return findAll().stream().filter(filter).collect(Collectors.toSet());
    }

    @Override
    public <T> Set<T> findAll(Function<E, T> mapper) {
        return findAll().stream().map(mapper).collect(Collectors.toSet());
    }

    @Override
    public Set<E> findByIdsIn(Collection<PK> ids) {
        try {
            CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<E> criteria = builder.createQuery(this.classTypeParameter);
            ParameterExpression<List> customIds = builder.parameter(List.class);
            criteria.where(criteria.from(this.classTypeParameter).get("id").in(customIds));
            return new HashSet<>(this.entityManager.createQuery(criteria).setParameter("customIds", ids).getResultList());
        }catch (NoResultException ex){
            return new HashSet<>();
        }
    }
}
