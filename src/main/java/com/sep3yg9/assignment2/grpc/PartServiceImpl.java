package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartServiceGrpc;
import com.sep3yg9.assignment2.grpc.protobuf.parts.TrayList;
import com.sep3yg9.assignment2.model.TrayEntity;
import com.sep3yg9.assignment2.repository.PartRepository;
import com.sep3yg9.assignment2.repository.TrayRespository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class PartServiceImpl extends PartServiceGrpc.PartServiceImplBase {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private TrayRespository trayRespository;

    @Override
    public void createTray(com.sep3yg9.assignment2.grpc.protobuf.parts.Tray tray, StreamObserver<com.sep3yg9.assignment2.grpc.protobuf.parts.Tray> responseObserver) {
        TrayEntity trayCreated = trayRespository.createTray(tray.getMaxWeight(), tray.getFinished(), tray.getType());
        responseObserver.onNext(trayCreated.convertToTray());
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
