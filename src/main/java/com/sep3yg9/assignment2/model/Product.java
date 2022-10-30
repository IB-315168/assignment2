package com.sep3yg9.assignment2.model;

import java.util.ArrayList;

public class Product {
    private long id;
    private ArrayList<TOCEntry> tableOfContents;

    public Product(long id) {
        this.id = id;
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
}
