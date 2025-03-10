package symetricum.multimod.storage;

import lombok.Data;

@Data
public class StorageConfig {
    String root;
    Integer buffer = 1024;
}
