package services.metadata.impl;

import base.services.impl.BaseServiceImpl;
import domains.metadata.AppMetadata;
import repositories.metadata.AppMetadataRepository;
import services.metadata.AppMetadataService;

public class AppMetadataServiceImpl extends BaseServiceImpl<AppMetadata,Integer, AppMetadataRepository>
        implements AppMetadataService {

    public AppMetadataServiceImpl(AppMetadataRepository repository) {
        super(repository);
    }
}
