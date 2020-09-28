package repositories.metadata.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.metadata.TableMetadata;
import repositories.metadata.TableMetadataRepository;

import javax.persistence.EntityManager;

public class TableMetadataRepositoryImpl extends BaseRepositoryImpl<TableMetadata,Integer> implements TableMetadataRepository {

    public TableMetadataRepositoryImpl(EntityManager entityManager) {
        super(TableMetadata.class, entityManager);
    }
}
