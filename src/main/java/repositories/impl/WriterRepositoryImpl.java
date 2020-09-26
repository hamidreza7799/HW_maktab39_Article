package repositories.impl;

import repositories.WriterRepository;

import javax.persistence.EntityManager;

public class WriterRepositoryImpl extends UserRepositoryImpl implements WriterRepository {

    public WriterRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
