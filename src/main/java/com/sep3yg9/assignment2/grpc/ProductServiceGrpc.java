package com.sep3yg9.assignment2.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import com.sep3yg9.assignment2.grpc.protobuf.products.PartProduct;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.grpc.protobuf.products.ProductList;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.grpc.protobuf.trays.TrayList;
import com.sep3yg9.assignment2.model.ProductEntity1;
import com.sep3yg9.assignment2.model.dbentities.ProductEntity;
import com.sep3yg9.assignment2.model.dbentities.TrayEntity;
import com.sep3yg9.assignment2.repository.ProductRepo;
import com.sep3yg9.assignment2.services.interfaces.ProductService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class ProductServiceGrpc extends com.sep3yg9.assignment2.grpc.protobuf.products.ProductServiceGrpc.ProductServiceImplBase
{
    private final ProductService productService;

    public ProductServiceGrpc(ProductService productService)
    {
        this.productService = productService;
    }

    @Override
    public void createProduct(StringValue type, StreamObserver<Product> responseObserver) {
        try {
            ProductEntity productToCreate = new ProductEntity();
            productToCreate.setType(type.getValue());
            productToCreate.setFinished(false);

            ProductEntity product = productService.create(productToCreate);

            responseObserver.onNext(product.convertToProduct());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void putIntoProduct(PartProduct request, StreamObserver<Product> responseObserver) {
        try
        {
            Product product = productService.putPartIntoProduct(request.getProductId(), request.getPartId()).convertToProduct();

            responseObserver.onNext(product);
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(
                Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void markProductAsFinished(Int32Value request, StreamObserver<Product> responseObserver) {
        Product product = productService.productFinished(request.getValue()).convertToProduct();

        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllFinishedTrays(Empty request, StreamObserver<TrayList> responseObserver) {
        List<Tray> trayEntityList = new ArrayList<>();

        for(TrayEntity tray : productService.getAllFinishedTrays()) {
            trayEntityList.add(tray.convertToTray());
        }

        TrayList trayList = TrayList.newBuilder()
                .addAllTrays(trayEntityList)
                .build();

        responseObserver.onNext(trayList);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllProducts(Empty request, StreamObserver<ProductList> responseObserver) {
        List<Product> products = new ArrayList<>();

        for(ProductEntity productEntity : productService.getAllUnfinishedProducts()) {
            products.add(productEntity.convertToProduct());
        }

        ProductList list = ProductList.newBuilder()
                .addAllProducts(products)
                .build();

        responseObserver.onNext(list);
        responseObserver.onCompleted();
    }
}
