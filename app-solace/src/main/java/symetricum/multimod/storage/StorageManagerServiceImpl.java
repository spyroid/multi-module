package symetricum.multimod.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Slf4j
@Service
public class StorageManagerServiceImpl implements StorageManagerService {

    private final Map<String, StorageDao> storages;
    private StoragesConfig.Types activeStorage;
    private StorageDao activeStorageDao;

    public StorageManagerServiceImpl(StoragesProps storagesProps, Map<String, StorageDao> storages) {
        log.info("active: {}", storagesProps.active);
        log.info("storages: {}", storages);
        this.storages = storages;
        setActiveStorage(storagesProps.active);
    }

    @Override
    public void setActiveStorage(StoragesConfig.Types activeStorage) {
        if (storages.isEmpty()) return;
        if (activeStorage == null) throw new IllegalArgumentException("activeStorage cannot be null");
        if (!storages.containsKey(activeStorage.name())) throw new IllegalArgumentException("Storage " + activeStorage + " not found");
        this.activeStorage = activeStorage;
        this.activeStorageDao = storages.get(activeStorage.name());
    }

    public void save() {
        activeStorageDao.save();
    }

    public void delete() {
        activeStorageDao.delete();
    }
}
