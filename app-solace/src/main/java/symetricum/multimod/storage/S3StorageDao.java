package symetricum.multimod.storage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S3StorageDao implements StorageDao {

    public S3StorageDao(StorageConfig config) {
        log.info("Creating S3Storage: {}", config);
    }

}
