package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;
import com.sep3yg9.assignment2.grpc.protobuf.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductEntity1
{
    private long id;
    private ArrayList<TOCEntryEntity1> tableOfContents;

    private String type;

    private boolean finished;
    private ArrayList<PartEntity1> parts;

    public ProductEntity1()
    {
    }

    public ProductEntity1(long id, String type) {
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

    public ArrayList<TOCEntryEntity1> getTableOfContents() {
        return tableOfContents;
    }

    public void addContent(TOCEntryEntity1 entry){
        tableOfContents.add(entry);
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished(){
        return finished;
    }

    public String getType() {
        return type;
    }

    public ArrayList<PartEntity1> getParts() {
        return parts;
    }

    public void setParts(ArrayList<PartEntity1> parts) {
        this.parts = parts;
    }

    public Product convertToProduct() {
        List<PartTray> toc = new ArrayList<>();
        for(TOCEntryEntity1 entry : tableOfContents) {
            toc.add(entry.convertToPartTray());
        }
        List<Part> parts1 = new ArrayList<>();
        for(PartEntity1 part : parts) {
            parts1.add(part.convertToPart());
        }

        Product product = Product.newBuilder()
//                .setId(id)
                .setProductType(type)
                .setFinished(finished)
                .addAllToc(toc)
                .addAllParts(parts1)
                .build();

        return product;
    }
}
