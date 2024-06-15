package symetricum.multimod.mod.solace.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

@Slf4j
@Configuration
@EnableConfigurationProperties(ModSolaceProps.class)
public class ModSolaceConfig {
    @SneakyThrows
    public ModSolaceConfig() {
        Thread.sleep(300);
        log.info("### ModSolace initialized ###");
    }

    @Bean
    static SolaceConnectionFactory solaceConnectionFactory(Environment environment) {
        return new SolaceConnectionFactory(environment);
    }
}

@Slf4j
class SolaceConnectionFactory implements BeanDefinitionRegistryPostProcessor {
    private final Map<String, SolaceConfig> connections;

    SolaceConnectionFactory(Environment environment) {
        connections = Binder.get(environment)
                .bind("mod.solace.connection", Bindable.mapOf(String.class, SolaceConfig.class))
                .orElse(Map.of());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
        connections.forEach((name, cfg) -> {
            var beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(SolaceConnection.class);
            beanDefinition.setInstanceSupplier(() -> new SolaceConnection(cfg));
            registry.registerBeanDefinition(name, beanDefinition);
        });
    }
}
