package ma.enset.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ma.enset.stubs.Bank;
import ma.enset.stubs.BankServiceGrpc;
public class BankGrpcClient1 {
    public static void main(String[] args)
    {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",555)
                .usePlaintext()
                .build();

        BankServiceGrpc.BankServiceBlockingStub blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setCurrencyFrom("DH")
                .setCurrencyTo("EURO")
                .setAmount(1200.0)
                .build();
        Bank.ConvertCurrencyResponse response = blockingStub.convert(request);
        System.out.println(response);
    }



}
