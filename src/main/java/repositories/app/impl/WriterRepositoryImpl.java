package repositories.app.impl;

import repositories.app.WriterRepository;

import javax.persistence.EntityManager;

public class WriterRepositoryImpl extends UserRepositoryImpl implements WriterRepository {

    public WriterRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
