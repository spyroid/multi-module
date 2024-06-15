package symetricum.multimod.mod.solace.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties("mod.solace")
public class ModSolaceProps {
    Map<String, SolaceConfig> connection;
}
