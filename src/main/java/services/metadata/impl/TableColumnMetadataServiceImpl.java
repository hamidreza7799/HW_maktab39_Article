package services.metadata.impl;

import base.services.BaseService;
import base.services.impl.BaseServiceImpl;
import domains.metadata.TableColumnMetadata;
import repositories.metadata.TableColumnMetadataRepository;
import services.metadata.TableColumnMetadataService;
import services.metadata.TableMetadataService;

public class TableColumnMetadataServiceImpl extends BaseServiceImpl<TableColumnMetadata,Integer,TableColumnMetadataRepository>
        implements TableColumnMetadataService {

    public TableColumnMetadataServiceImpl(TableColumnMetadataRepository repository) {
        super(repository);
    }
}
