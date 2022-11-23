package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayList;
import com.sep3yg9.assignment2.model.dbentities.TrayEntity;
import com.sep3yg9.assignment2.services.TrayServiceImpl;
import com.sep3yg9.assignment2.services.interfaces.TrayService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.ArrayList;
import java.util.List;

@GRpcService public class TrayServiceGrpc extends
    com.sep3yg9.assignment2.grpc.protobuf.trays.TrayServiceGrpc.TrayServiceImplBase
{
  private final TrayService trayService;

  public TrayServiceGrpc(TrayService trayService)
  {
    this.trayService = trayService;
  }

  @Override public void createTray(Tray tray,
      StreamObserver<Tray> responseObserver)
  {
    try
    {
      TrayEntity tray1 = new TrayEntity();

      tray1.setType(tray.getType());
      tray1.setMaxweight(tray.getMaxWeight());
      tray1.setFinished(false);

      TrayEntity trayCreated = trayService.create(tray1);

      responseObserver.onNext(trayCreated.convertToTray());
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      responseObserver.onError(
          Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
    }
  }

  @Override public void trayFinished(Int32Value request,
      StreamObserver<Tray> responseObserver)
  {
    try
    {
      TrayEntity tray = trayService.trayFinished(request.getValue());

      responseObserver.onNext(tray.convertToTray());
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      responseObserver.onError(
          Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
    }
  }

  @Override public void putOnTray(PartTray request,
      StreamObserver<Tray> responseObserver)
  {
    try
    {
      Tray tray = trayService.putPartIntoTray(request.getTrayId(),
          request.getPartId()).convertToTray();

      responseObserver.onNext(tray);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      responseObserver.onError(
          Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
    }
  }

  @Override public void getAllTrays(Empty request,
      StreamObserver<TrayList> responseObserver)
  {
    try
    {
      List<Tray> trays = new ArrayList<>();

      for (TrayEntity trayEntity : trayService.getAllRemainingTrays())
      {
        trays.add(trayEntity.convertToTray());
      }

      TrayList trayList = TrayList.newBuilder().addAllTrays(trays).build();
      responseObserver.onNext(trayList);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      responseObserver.onError(
          Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
    }
  }

}
