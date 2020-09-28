package repositories.metadata.impl;

import base.repositories.impl.BaseRepositoryImpl;
import domains.metadata.TableColumnMetadata;
import repositories.metadata.TableColumnMetadataRepository;

import javax.persistence.EntityManager;

public class TableColumnRepositoeyImpl extends BaseRepositoryImpl<TableColumnMetadata,Integer>
        implements TableColumnMetadataRepository {

    public TableColumnRepositoeyImpl(EntityManager entityManager){
        super(TableColumnMetadata.class,entityManager);
    }
}
