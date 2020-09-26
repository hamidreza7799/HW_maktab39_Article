package repositories.impl;

import repositories.AdminRepository;

import javax.persistence.EntityManager;

public class AdminRepositoryImpl extends WriterRepositoryImpl implements AdminRepository {

    public AdminRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
