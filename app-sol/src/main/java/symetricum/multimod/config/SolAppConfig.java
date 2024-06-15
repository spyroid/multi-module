package symetricum.multimod.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SolAppConfig {
    public SolAppConfig() {
        log.info("### {}", this);
    }
}
