package ma.enset.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ma.enset.stubs.Bank;
import ma.enset.stubs.BankServiceGrpc;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BankGrpcClient4 {
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

        StreamObserver<Bank.ConvertCurrencyRequest> perFormStream = asyncStyb.perFormStream(new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("====================");
                System.out.println(convertCurrencyResponse);
                System.out.println("====================");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {

                perFormStream.onNext(request);
                System.out.println("===================> counter : "+counter);
                ++counter;
                if(counter==5){
                    perFormStream.onCompleted();
                    timer.cancel();
                }
            }

        },1000,1000);

        System.in.read();

    }



}
