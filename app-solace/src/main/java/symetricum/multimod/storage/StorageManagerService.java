package symetricum.multimod.storage;

public interface StorageManagerService {
    void setActiveStorage(StoragesConfig.Types activeStorage);

    StoragesConfig.Types getActiveStorage();
}
