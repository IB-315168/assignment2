package com.sep3yg9.assignment2.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;

public class TrayEntity {
    private long id;
    private double max_weight;
    private boolean finished;
    private String type;
    private ArrayList<PartEntity> parts;

    public TrayEntity()
    {
    }

    public TrayEntity(long id, double max_weight, boolean finished, String type) {
        this.id = id;
        this.max_weight = max_weight;
        this.finished = finished;
        this.type = type;
        parts = new ArrayList<PartEntity>();
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

    public ArrayList<PartEntity> getParts() {
        return parts;
    }

    public void addPart(PartEntity p){
        this.parts.add(p);
    }

    public void removePart(PartEntity p){
        if(parts.contains(p)){
            if(parts.isEmpty())
                type = p.getType();
            this.parts.remove(p);
        }else
            System.out.println("Part does not exist in the tray");
    }

    public Tray convertToTray() {
        List<Part> trayParts = new ArrayList<>();
        for(PartEntity part : parts) {
            trayParts.add(part.convertToPart());
        }

        Tray tray = Tray.newBuilder()
                .setId(id)
                .setMaxWeight(max_weight)
                .setFinished(finished)
                .setType(type)
                .addAllParts(trayParts)
                .build();

        return tray;
    }

    public double getCarriedWeight() {
        double total = 0;
        for(PartEntity part : parts) {
            total += part.getWeight();
        }
        return total;
    }

}
