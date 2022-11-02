package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayList;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayServiceGrpc;
import com.sep3yg9.assignment2.model.TrayEntity;
import com.sep3yg9.assignment2.repository.TrayRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class TrayServiceImpl extends TrayServiceGrpc.TrayServiceImplBase {
    @Autowired
    private TrayRepository trayRepository;

    @Override
    public void createTray(Tray tray, StreamObserver<Tray> responseObserver) {
        TrayEntity trayCreated = trayRepository.createTray(tray.getMaxWeight(), false, tray.getType());
        responseObserver.onNext(trayCreated.convertToTray());
        responseObserver.onCompleted();
    }

    @Override
    public void trayFinished(Int64Value request, StreamObserver<Tray> responseObserver) {
        Tray tray = trayRepository.trayFinished(request.getValue());
        responseObserver.onNext(tray);
        responseObserver.onCompleted();
    }

    @Override
    public void putOnTray(PartTray request, StreamObserver<Tray> responseObserver) {
        Tray tray = trayRepository.putPartIntoTray(request.getTrayId(), request.getPartId()).convertToTray();
        responseObserver.onNext(tray);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTrays(Empty request, StreamObserver<TrayList> responseObserver) {
        TrayList trayList = TrayList.newBuilder()
                .addAllTrays(trayRepository.getAllTrays(false))
                .build();
        responseObserver.onNext(trayList);
        responseObserver.onCompleted();
    }

}
