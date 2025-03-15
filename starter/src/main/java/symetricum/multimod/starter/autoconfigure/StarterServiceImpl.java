package symetricum.multimod.starter.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StarterServiceImpl implements StarterService {

    public StarterServiceImpl(StarterConfig starterConfig) {
        log.info("Creating StarterServiceImpl: {}", starterConfig);
    }
}
