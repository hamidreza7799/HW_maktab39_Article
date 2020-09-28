package services.metadata.impl;

import base.services.impl.BaseServiceImpl;
import domains.metadata.TableMetadata;
import repositories.metadata.TableMetadataRepository;
import services.metadata.TableMetadataService;

public class TableMetadataServiceImpl extends BaseServiceImpl<TableMetadata,Integer, TableMetadataRepository>
        implements TableMetadataService {

    public TableMetadataServiceImpl(TableMetadataRepository repository) {
        super(repository);
    }
}
