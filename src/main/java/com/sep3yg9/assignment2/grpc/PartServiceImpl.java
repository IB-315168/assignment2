package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartList;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartServiceGrpc;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.repository.PartRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class PartServiceImpl extends PartServiceGrpc.PartServiceImplBase {
    @Autowired
    private PartRepository partRepository;

    @Override
    public void createPart(Part part, StreamObserver<Part> responseObserver) {
        PartEntity part1 = partRepository.createPart(part.getAnimalId(), part.getType(), part.getWeight());

        responseObserver.onNext(part1.convertToPart());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllRemainingParts(Empty request, StreamObserver<PartList> responseObserver) {
        PartList partList = PartList.newBuilder()
                .addAllParts(partRepository.getAllParts())
                .build();

        responseObserver.onNext(partList);
        responseObserver.onCompleted();
    }
}
