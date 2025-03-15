package symetricum.multimod.starter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "symetricum")
public class StarterProps {
    String name;
    String price;
}
