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
        this.activeStorage = storagesProps.active;
        this.storages = storages;
        setActiveStorage(activeStorage);
    }

    @Override
    public void setActiveStorage(StoragesConfig.Types activeStorage) {
        if (storages.isEmpty()) throw new IllegalArgumentException("Storage list is empty");
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
