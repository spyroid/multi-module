package symetricum.multimod.storage;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
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
@EnableConfigurationProperties(StoragesProps.class)
public class StoragesConfig {

    @Bean
    static StorageFactory storagesFactory(Environment environment) {
        return new StorageFactory(environment);
    }

    public enum Types {
        S3, NFS
    }

    private static class StorageFactory implements BeanDefinitionRegistryPostProcessor {
        private final Map<Types, StorageConfig> allStorages;

        StorageFactory(Environment environment) {
            allStorages = Binder.get(environment)
                    .bind(StoragesProps.prefix + ".configuration", Bindable.mapOf(Types.class, StorageConfig.class))
                    .orElse(Map.of());
        }

        @Override
        public void postProcessBeanDefinitionRegistry(@NotNull BeanDefinitionRegistry registry) throws BeansException {
            allStorages.forEach((name, cfg) -> {
                log.info("Registering storage {}: {}", name, cfg);
                var beanDefinition = new GenericBeanDefinition();
                val args = new ConstructorArgumentValues();
                args.addGenericArgumentValue(cfg);
                beanDefinition.setConstructorArgumentValues(args);

                switch (name) {
                    case S3 -> beanDefinition.setBeanClass(S3StorageDao.class);
                    case NFS -> beanDefinition.setBeanClass(NfsStorageDao.class);
                }
                registry.registerBeanDefinition(name.name(), beanDefinition);
            });
        }
    }
}
