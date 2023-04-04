package ma.enset.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ma.enset.stubs.Bank;
import ma.enset.stubs.BankServiceGrpc;

import java.io.IOException;
public class BankGrpcClient2 {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",555)
                .usePlaintext()
                .build();

        BankServiceGrpc.BankServiceStub asyncStyb = BankServiceGrpc.newStub(managedChannel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setCurrencyTo("DH")
                .setCurrencyTo("EURO")
                .setAmount(1200.0)
                .build();


        asyncStyb.convert(request, new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("=================");
                System.out.println("convertCurrencyResponse = " + convertCurrencyResponse);
                System.out.println("=============================");
            }
            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }
            @Override
            public void onCompleted() {
                System.out.println("==========FIN=========");
            }
        });
        System.out.println("==========?=========");
        System.in.read();
    }



}
