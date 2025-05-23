package symetricum.multimod;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import symetricum.grpc.hello.proto.HelloRequest;
import symetricum.grpc.hello.proto.SimpleGrpc;

@Slf4j
@Service
@RequiredArgsConstructor
public class GRPCClient {

    final SimpleGrpc.SimpleBlockingStub simpleStub;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Application ready");
        var response = simpleStub.sayHello(HelloRequest.newBuilder().setName("Solace App").build());
        log.info("Hello -> {}", response.getMessage());
        var response2 = simpleStub.sayHello(HelloRequest.newBuilder().setName("Solace App").build());
        log.info("...");
        log.info("Hello -> {}", response2.getMessage());
    }
}
