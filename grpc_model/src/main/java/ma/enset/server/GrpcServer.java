package ma.enset.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ma.enset.service.BankGrpcService;

public class GrpcServer {
    public static void main(String[] args) throws Exception{
        Server server = ServerBuilder.forPort(555)
                .addService(new BankGrpcService())
                .build();

        server.start();
        server.awaitTermination();
    }
}

