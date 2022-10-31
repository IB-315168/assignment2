package com.sep3yg9.assignment2.testclient;

import com.google.protobuf.Empty;
import com.sep3yg9.assignment2.grpc.protobuf.parts.*;
import com.sep3yg9.assignment2.grpc.protobuf.trays.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class TestClient
{
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        TrayServiceGrpc.TrayServiceBlockingStub trayStub = TrayServiceGrpc.newBlockingStub(channel);
        PartServiceGrpc.PartServiceBlockingStub partStub = PartServiceGrpc.newBlockingStub(channel);

        Part part1 = partStub.createPart(Part.newBuilder().setAnimalId(1).setWeight(1900).setType("TORSO").build());
        System.out.println("Part created: " + part1);
        part1 = partStub.createPart(Part.newBuilder().setAnimalId(1).setWeight(1000).setType("TORSO").build());
        System.out.println("Part created: " + part1);
        System.out.println("All parts: " + partStub.getAllRemainingParts(Empty.newBuilder().build()));

        Tray tray1 = trayStub.createTray(Tray.newBuilder().setMaxWeight(3000).setType("TORSO").build());
        System.out.println("Tray before: " + trayStub.getAllTrays(Empty.newBuilder().build()));

        trayStub.putOnTray(PartTray.newBuilder().setPartId(1).setTrayId(1).build());
        trayStub.putOnTray(PartTray.newBuilder().setPartId(2).setTrayId(1).build());

        System.out.println("Tray after: " + trayStub.getAllTrays(Empty.newBuilder().build()));
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("type in weight");
//        double weight = Double.parseDouble(scanner.nextLine());
//        System.out.println("type in type");
//        String type = scanner.nextLine();
//        Tray tray = trayStub.createTray(Tray.newBuilder().setMaxWeight(weight).setFinished(false).setType(type).build());
//        System.out.println("tray "+tray );
//        System.out.println("Overview of trays :");
//        TrayList trayList = trayStub.getAllTrays(Empty.newBuilder().build());
//        System.out.println(trayList);

    }
}