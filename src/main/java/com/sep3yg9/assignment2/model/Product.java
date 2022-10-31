package com.sep3yg9.assignment2.model;

import java.util.ArrayList;

public class Product {
    private long id;
    private ArrayList<TOCEntry> tableOfContents;

    private String type;

    private boolean finished=true;
    public Product(long id,String type) {
        this.id = id;
        this.type = type;
        this.tableOfContents = new ArrayList<TOCEntry>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<TOCEntry> getTableOfContents() {
        return tableOfContents;
    }

    public void addContent(TOCEntry entry){
        tableOfContents.add(entry);
    }

    public boolean isFinished(long productId){
        if (finished==true){
            return true;
        }
        else return false;
    }
}
