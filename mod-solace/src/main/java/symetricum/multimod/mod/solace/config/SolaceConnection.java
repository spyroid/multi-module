package symetricum.multimod.mod.solace.config;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SolaceConnection {
    final SolaceConfig solaceConfig;

    @SneakyThrows
    public SolaceConnection(SolaceConfig solaceConfig) {
        this.solaceConfig = solaceConfig;
        log.info("~~~> Connecting to {} ...", solaceConfig);
//        Thread.sleep(678);
        log.info("~~~> Connected to {}", solaceConfig);
    }
}
