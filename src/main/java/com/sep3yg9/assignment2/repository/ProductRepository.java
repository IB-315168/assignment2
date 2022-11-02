package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    @Autowired
    private TrayRepository trayRepository;
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private HistoryRepository historyRepository;

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
        long id = historyRepository.getLastProductId() + 1;
        products.put(id, new ProductEntity(id, type));
        return products.get(id);
    }

    public List<Tray> getFinishedTrays() {
        return trayRepository.getAllTrays(true);
    }

    public List<Product> getAllProducts() {
        List<Product> products1 = new ArrayList<>();
        for(long id : products.keySet()) {
            products1.add(products.get(id).convertToProduct());
        }

        return products1;
    }

    public ProductEntity putPartIntoProduct(long product, long part) {
        PartEntity part1 = new PartEntity(partRepository.getPart(part));
        long idTray = 0L;

        if(products.get(product).getParts().size() > 0) {
            if(products.get(product).getType().equalsIgnoreCase("Same parts")) {
                if(products.get(product).getParts().get(0).getType().equalsIgnoreCase(part1.getType())) {
                    return addHelper(product, part, part1, idTray);
                } else {
                    System.out.println("This product requires same type of parts.");
                    return products.get(product);
                }
            } else {
                for(PartEntity part2 : products.get(product).getParts()) {
                    if(part2.getType().equalsIgnoreCase(part1.getType())) {
                        System.out.println("This type of part is already in the product (this product requires single part of every type).");
                        return products.get(product);
                    }
                }
                return addHelper(product, part, part1, idTray);
            }
        } else {
            return addHelper(product, part, part1, idTray);
        }
    }

    public ProductEntity markProductAsFinished(long id) {
        if(!products.get(id).isFinished()) {
            products.get(id).setFinished(true);
            historyRepository.addToProductHistory(products.get(id));
        } else {
            System.out.println("Product is already finished");
        }

        return products.get(id);
    }

    private ProductEntity addHelper(long product, long part, PartEntity part1, long idTray) {
        if(trayRepository.getAllTrays(true).get((int) idTray).getPartsList().contains(part1.convertToPart())) {
            System.out.println("Part is unavailable, make sure that tray with that part is finished.");
        }

        products.get(product).getParts().add(part1);
        idTray = trayRepository.removeFromTray(part1);
        products.get(product).getTableOfContents().add(new TOCEntryEntity(idTray, part));

        if(trayRepository.getAllTrays(true).get((int) idTray).getPartsList().size() == 0) {
            trayRepository.trayUnfinished(idTray);
        }

        return products.get(product);
    }
}
