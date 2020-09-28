package repositories.metadata.impl;

import base.repositories.impl.BaseRepositoryImpl;
import base.services.impl.BaseServiceImpl;
import domains.metadata.AppMetadata;
import repositories.metadata.AppMetadataRepository;

import javax.persistence.EntityManager;

public class AppMetadataRepositoryImpl extends BaseRepositoryImpl<AppMetadata,Integer> implements AppMetadataRepository {

    public AppMetadataRepositoryImpl(EntityManager entityManager) {
        super(AppMetadata.class, entityManager);
    }
}
