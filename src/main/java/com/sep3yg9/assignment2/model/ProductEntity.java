package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductEntity {
    private long id;
    private ArrayList<TOCEntryEntity> tableOfContents;

    private String type;

    private boolean finished;
    private ArrayList<PartEntity> parts;

    public ProductEntity(long id, String type) {
        this.id = id;
        this.type = type;
        this.finished = false;
        this.tableOfContents = new ArrayList<>();
        this.parts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<TOCEntryEntity> getTableOfContents() {
        return tableOfContents;
    }

    public void addContent(TOCEntryEntity entry){
        tableOfContents.add(entry);
    }

    public boolean isFinished(long productId){
        return finished;
    }

    public Product convertToProduct() {
        List<PartTray> toc = new ArrayList<>();
        for(TOCEntryEntity entry : tableOfContents) {
            toc.add(entry.convertToPartTray());
        }
        List<Part> parts1 = new ArrayList<>();
        for(PartEntity part : parts) {
            parts1.add(part.convertToPart());
        }

        Product product = Product.newBuilder()
                .setId(id)
                .setProductType(type)
                .setFinished(finished)
                .addAllToc(toc)
                .addAllParts(parts1)
                .build();

        return product;
    }
}
