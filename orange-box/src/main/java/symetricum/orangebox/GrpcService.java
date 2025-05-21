package symetricum.orangebox;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import symetricum.grpc.hello.proto.HelloReply;
import symetricum.grpc.hello.proto.HelloRequest;
import symetricum.grpc.hello.proto.SimpleGrpc;

@Slf4j
@Service
class GrpcServerService extends SimpleGrpc.SimpleImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Hello {}. I'm {}",req.getName(), Thread.currentThread().getName());
        if (req.getName().startsWith("error")) throw new IllegalArgumentException("Bad name: " + req.getName());
        if (req.getName().startsWith("internal")) throw new RuntimeException();
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello - " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void streamHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        int count = 0;
        while (count < 10) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello(" + count + ") ==> " + req.getName()).build();
            responseObserver.onNext(reply);
            count++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }
}