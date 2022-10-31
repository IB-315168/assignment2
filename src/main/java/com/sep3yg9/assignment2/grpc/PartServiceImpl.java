package com.sep3yg9.assignment2.grpc;

import com.sep3yg9.assignment2.grpc.protobuf.parts.PartServiceGrpc;
import com.sep3yg9.assignment2.repository.TrayRespository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GRpcService
public class PartServiceImpl extends PartServiceGrpc.PartServiceImplBase
{
@Override
    public void createTray(com.sep3yg9.assignment2.grpc.protobuf.parts.Tray tray, StreamObserver<com.sep3yg9.assignment2.grpc.protobuf.parts.Tray>responseObserver){
    TrayRespository.createTray(tray.getMaxWeight(), tray.getFinished(), tray.getType());
    responseObserver.onNext(tray);
    responseObserver.onCompleted();
}

}
