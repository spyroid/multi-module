package symetricum.multimod.starter.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(StarterProps.class)
public class StarterAutoConfiguration {

    private final StarterProps starterProps;

    public StarterAutoConfiguration(StarterProps starterProps) {
        this.starterProps = starterProps;
        log.info(">>> StarterAutoConfiguration");
    }

    @Bean
    @ConditionalOnMissingBean
    public StarterConfig starterConfig() {
        val bean = new StarterConfig();
        bean.name = starterProps.name == null ? "default-0" : starterProps.name + "-" + starterProps.price;
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean
    public StarterService starterService(StarterConfig starterConfig) {
        return new StarterServiceImpl(starterConfig);
    }
}
