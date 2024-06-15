package symetricum.multimod.mod.core.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ModCoreConfig {
    @SneakyThrows
    public ModCoreConfig() {
        Thread.sleep(300);
        log.info("### ModCore initialized ###");
    }
}
