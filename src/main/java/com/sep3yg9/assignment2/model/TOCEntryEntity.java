package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;

public class TOCEntryEntity {
    private long tray;
    private long part;

    public TOCEntryEntity(long tray_Id, long part_Id) {
        this.tray = tray_Id;
        this.part = part_Id;
    }

    public long getTray() {
        return tray;
    }

    public void setTray(long tray_Id) {
        this.tray = tray_Id;
    }

    public long getPart() {
        return part;
    }

    public void setPart(long part_Id) {
        this.part = part_Id;
    }

    public PartTray convertToPartTray() {
        PartTray tray1 = PartTray.newBuilder()
                .setTrayId(tray)
                .setPartId(part)
                .build();

        return tray1;
    }
}
