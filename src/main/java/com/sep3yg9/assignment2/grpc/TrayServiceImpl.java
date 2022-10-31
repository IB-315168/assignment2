package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartServiceGrpc;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayList;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayServiceGrpc;
import com.sep3yg9.assignment2.model.TrayEntity;
import com.sep3yg9.assignment2.repository.PartRepository;
import com.sep3yg9.assignment2.repository.TrayRespository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class TrayServiceImpl extends TrayServiceGrpc.TrayServiceImplBase {
    @Autowired
    private TrayRespository trayRespository;

    @Override
    public void createTray(Tray tray, StreamObserver<Tray> responseObserver) {
        TrayEntity trayCreated = trayRespository.createTray(tray.getMaxWeight(), tray.getFinished(), tray.getType());
        responseObserver.onNext(trayCreated.convertToTray());
        responseObserver.onCompleted();
    }

    @Override
    public void putOnTray(PartTray request, StreamObserver<Tray> responseObserver) {
        Tray tray = trayRespository.putPartIntoTray(request.getTrayId(), request.getPartId()).convertToTray();
        responseObserver.onNext(tray);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTrays(Empty request, StreamObserver<TrayList> responseObserver) {
        TrayList trayList = TrayList.newBuilder()
                .addAllTrays(trayRespository.getAllTrays())
                .build();
        responseObserver.onNext(trayList);
        responseObserver.onCompleted();
    }

}
