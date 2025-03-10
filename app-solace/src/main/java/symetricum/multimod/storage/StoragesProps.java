package symetricum.multimod.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@ConfigurationProperties(StoragesProps.prefix)
@Configuration
public class StoragesProps {
    final static String prefix = "app.storages";
    Map<StoragesConfig.Types, StorageConfig> configuration;
    StoragesConfig.Types active;
}