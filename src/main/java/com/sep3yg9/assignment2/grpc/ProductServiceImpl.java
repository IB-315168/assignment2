package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.grpc.protobuf.products.ProductList;
import com.sep3yg9.assignment2.grpc.protobuf.products.ProductServiceGrpc;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayList;
import com.sep3yg9.assignment2.model.ProductEntity;
import com.sep3yg9.assignment2.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(StringValue type, StreamObserver<Product> responseObserver) {
        ProductEntity product = productRepository.createProduct(type.getValue());

        responseObserver.onNext(product.convertToProduct());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllFinishedTrays(Empty request, StreamObserver<TrayList> responseObserver) {
        TrayList trayList = TrayList.newBuilder()
                .addAllTrays(productRepository.getFinishedTrays())
                .build();

        responseObserver.onNext(trayList);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllProducts(Empty request, StreamObserver<ProductList> reponseObserver) {
        List<Product> products = productRepository.getAllProducts();

        ProductList list = ProductList.newBuilder()
                .addAllProducts(products)
                .build();

        reponseObserver.onNext(list);
        reponseObserver.onCompleted();
    }
}
