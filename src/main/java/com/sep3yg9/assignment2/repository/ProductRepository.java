package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    @Autowired
    private TrayRespository trayRespository;

    private final Map<Long, ProductEntity> products = new HashMap<>();

    public ProductRepository() {
        initDataSource();
    }

    private void initDataSource() {
        //put file context for serialization
    }

    public ProductEntity createProduct(String type) {
        if (!(type.equalsIgnoreCase("same parts") || type.equalsIgnoreCase("half an animal"))) {
            System.out.println("Product can be only \"Same parts\" or \"Half an animal\"");
            //rak, avoid
            return null;
        }
        long id = 1L;
        if (!products.isEmpty()) {
            id = (long) products.keySet().toArray()[products.keySet().size() - 1] + 1;
        }
        products.put(id, new ProductEntity(id, type));
        return products.get(id);
    }

    public List<Tray> getFinishedTrays() {
        return trayRespository.getAllTrays(true);
    }

}
