package repositories.metadata.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.metadata.TableColumnMetadata;
import repositories.metadata.TableColumnMetadataRepository;

import javax.persistence.EntityManager;

public class TableColumnRepositoryImpl extends BaseRepositoryImpl<TableColumnMetadata,Integer>
        implements TableColumnMetadataRepository {

    public TableColumnRepositoryImpl(EntityManager entityManager){
        super(TableColumnMetadata.class,entityManager);
    }
}
