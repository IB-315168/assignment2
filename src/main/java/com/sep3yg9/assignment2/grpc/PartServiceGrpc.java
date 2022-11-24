package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartList;
import com.sep3yg9.assignment2.model.AnimalEntity;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.services.interfaces.AnimalService;
import com.sep3yg9.assignment2.services.interfaces.PartService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@GRpcService public class PartServiceGrpc extends
    com.sep3yg9.assignment2.grpc.protobuf.parts.PartServiceGrpc.PartServiceImplBase
{
  private final PartService partService;
  private final AnimalService animalService;

  public PartServiceGrpc(PartService partService, AnimalService animalService)
  {
    this.partService = partService;
    this.animalService = animalService;
  }

  @Override public void createPart(Part part,
      StreamObserver<Part> responseObserver)
  {
    try
    {
      PartEntity part1 = new PartEntity();

      Optional<AnimalEntity> animalEntity = animalService.getByRegNumber(part.getAnimalId());
      if(animalEntity.isEmpty()) {
        throw new IllegalArgumentException("Animal does not exist");
      }

      part1.setAnimalid(animalEntity.get());
      part1.setType(part.getType());
      part1.setWeight(part.getWeight());
      part1.setFinished(false);

      PartEntity partCreated = partService.create(part1);

      responseObserver.onNext(partCreated.convertToPart());
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      responseObserver.onError(
          Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
    }
  }

  @Override public void getAllRemainingParts(Empty request,
      StreamObserver<PartList> responseObserver)
  {
    try
    {
      List<PartEntity> parts = partService.getAllRemainingParts();
      List<Part> convertedParts = new ArrayList<>();

      for (PartEntity part : parts)
      {
        convertedParts.add(part.convertToPart());
      }

      PartList partList = PartList.newBuilder().addAllParts(convertedParts)
          .build();

      responseObserver.onNext(partList);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      responseObserver.onError(
          Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
    }
  }
}
