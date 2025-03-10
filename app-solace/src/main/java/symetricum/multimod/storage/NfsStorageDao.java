package symetricum.multimod.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class NfsStorageDao implements StorageDao {
    public NfsStorageDao(StorageConfig config, ApplicationContext applicationContext) {
        log.info("Creating NfsStorage: {}", config);
        log.warn("Application context: {}", applicationContext);
    }
}
