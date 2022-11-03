package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Int64Value;
import com.sep3yg9.assignment2.grpc.protobuf.history.HistoryServiceGrpc;
import com.sep3yg9.assignment2.grpc.protobuf.products.AnimalList;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.grpc.protobuf.products.ProductList;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.ProductEntity;
import com.sep3yg9.assignment2.repository.HistoryRepository;
import com.sep3yg9.assignment2.repository.PartRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class HistoryServiceImpl extends HistoryServiceGrpc.HistoryServiceImplBase
{
  @Autowired
  private HistoryRepository historyRepository;

  @Override
  public void getProductsAnimals(Int64Value id, StreamObserver<AnimalList> responseObserver) {
    List<Long> animalIds = historyRepository.getProductsAnimals(id.getValue());
    AnimalList animals = AnimalList.newBuilder()
        .addAllAnimalIds(animalIds)
        .build();

    responseObserver.onNext(animals);
    responseObserver.onCompleted();
  }

  @Override
  public void getAnimalsProducts(Int64Value id, StreamObserver<ProductList> responseObserver) {
    List<Product> products = new ArrayList<>();

    for(ProductEntity product :  historyRepository.getAnimalsProducts(id.getValue())) {
      products.add(product.convertToProduct());
    }

    ProductList productList = ProductList.newBuilder()
        .addAllProducts(products)
        .build();

    responseObserver.onNext(productList);
    responseObserver.onCompleted();
  }
}
