package com.sep3yg9.assignment2.testclient;

import com.google.protobuf.Empty;
import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartServiceGrpc;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.parts.Tray;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class TestClient
{
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        PartServiceGrpc.PartServiceBlockingStub stub = PartServiceGrpc.newBlockingStub(channel);
        Scanner scanner = new Scanner(System.in);
        System.out.println("type in weight");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println("type in type");
        String type = scanner.nextLine();
        Tray tray=stub.createTray(Tray.newBuilder().setMaxWeight(weight).setFinished(false).setType(type).build());
        System.out.println("tray "+tray );

    }
}