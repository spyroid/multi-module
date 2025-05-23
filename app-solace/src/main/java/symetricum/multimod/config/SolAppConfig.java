package symetricum.multimod.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;
import symetricum.grpc.hello.proto.SimpleGrpc;
import symetricum.multimod.mod.solace.config.SolaceConnection;

@Slf4j
@Configuration
public class SolAppConfig {
    @SneakyThrows
    public SolAppConfig(@Qualifier("aaa") SolaceConnection solaceConnection) {
        Thread.sleep(300);
        log.info("### SolAppConfig initialized with {} ###", solaceConnection);
    }

    @Bean
    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
        return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:9090"));
    }
}
