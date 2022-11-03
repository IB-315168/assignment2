package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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
        long id = historyRepository.getLastProductId();
        if(products.keySet().size() != 0 && Collections.max(products.keySet()) > id) {
            id = Collections.max(products.keySet()) + 1;
        }
        else {
            id = id + 1;
        }
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
        PartEntity part1 = new PartEntity();
        long idTray = -1;

        for(Tray tray : trayRepository.getAllTrays(true)) {
            for(Part parts : tray.getPartsList()) {
                if(parts.getId() == part) {
                    part1 = new PartEntity(parts);
                    idTray = tray.getId();
                    break;
                }
            }
        }

        if(idTray == -1) {
            System.out.println("Part is unavailable, make sure that tray with that part is finished.");
            return products.get(product);
        }

        if(products.get(product).getParts().size() > 0) {
            if(products.get(product).getType().equalsIgnoreCase("Same parts")) {
                if(products.get(product).getParts().get(0).getType().equalsIgnoreCase(part1.getType())) {
                    return addHelper(product, part1, idTray);
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
                return addHelper(product, part1, idTray);
            }
        } else {
            return addHelper(product, part1, idTray);
        }
    }

    public ProductEntity markProductAsFinished(long id) {
        if(!products.get(id).isFinished() && products.get(id).getParts().size() != 0) {
            products.get(id).setFinished(true);
            historyRepository.addToProductHistory(products.get(id));
            return products.remove(id);
        } else {
            System.out.println("Product is already finished");
        }

        return products.get(id);
    }

    private ProductEntity addHelper(long product, PartEntity part1, long idTray) {
        products.get(product).getParts().add(part1);
        TrayEntity tray = trayRepository.removeFromTray(part1);
        products.get(product).getTableOfContents().add(new TOCEntryEntity(tray.getId(), part1.getId()));

        //TODO make get all trays get long instead of int
        if(tray.getParts().size() == 0) {
            trayRepository.trayUnfinished(tray.getId());
        }

        return products.get(product);
    }
}
