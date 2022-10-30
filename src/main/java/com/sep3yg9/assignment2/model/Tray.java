package com.sep3yg9.assignment2.model;

import java.util.ArrayList;

public class Tray {
    private long id;
    private double max_weight;
    private boolean finished;
    private String type;
    private ArrayList<Part> parts;

    public Tray(long id, double max_weight, boolean finished, String type) {
        this.id = id;
        this.max_weight = max_weight;
        this.finished = finished;
        this.type = type;
        parts = new ArrayList<Part>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(double max_weight) {
        this.max_weight = max_weight;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Part> getParts() {
        return parts;
    }

    public void addPart(Part p){
        this.parts.add(p);
    }

    public void removePart(Part p){
        if(parts.contains(p)){
            if(parts.isEmpty())
                type = p.getType();
            this.parts.remove(p);
        }else
            System.out.println("Part does not exist in the tray");
    }

}
