package symetricum.multimod.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import symetricum.multimod.mod.solace.config.SolaceConnection;

@Slf4j
@Configuration
public class SolAppConfig {
    @SneakyThrows
    public SolAppConfig(@Qualifier("aaa") SolaceConnection solaceConnection) {
        Thread.sleep(300);
        log.info("### SolAppConfig initialized with {} ###", solaceConnection);
    }
}
