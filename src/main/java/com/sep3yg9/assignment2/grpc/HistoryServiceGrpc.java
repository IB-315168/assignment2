package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Int32Value;
import com.sep3yg9.assignment2.grpc.protobuf.products.AnimalList;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.grpc.protobuf.products.ProductList;
import com.sep3yg9.assignment2.model.ProductEntity;
import com.sep3yg9.assignment2.services.HistoryServiceImpl;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.ArrayList;
import java.util.List;

@GRpcService public class HistoryServiceGrpc extends
    com.sep3yg9.assignment2.grpc.protobuf.history.HistoryServiceGrpc.HistoryServiceImplBase
{
  private final HistoryServiceImpl historyService;

  public HistoryServiceGrpc(HistoryServiceImpl historyService)
  {
    this.historyService = historyService;
  }

  @Override public void getProductsAnimals(Int32Value id,
      StreamObserver<AnimalList> responseObserver)
  {
    List<Integer> animalIds = historyService.getProductsAnimals(id.getValue());

    AnimalList animals = AnimalList.newBuilder().addAllAnimalIds(animalIds)
        .build();

    responseObserver.onNext(animals);
    responseObserver.onCompleted();
  }

  @Override public void getAnimalsProducts(Int32Value id,
      StreamObserver<ProductList> responseObserver)
  {
    List<Product> products = new ArrayList<>();

    for (ProductEntity product : historyService.getAnimalsProducts(
        id.getValue()))
    {
      products.add(product.convertToProduct());
    }

    ProductList productList = ProductList.newBuilder().addAllProducts(products)
        .build();

    responseObserver.onNext(productList);
    responseObserver.onCompleted();
  }
}
