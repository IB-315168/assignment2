package com.sep3yg9.assignment2.model;

import com.sep3yg9.assignment2.grpc.protobuf.parts.PartTray;

public class TOCEntryEntity {
    private TrayEntity tray;
    private PartEntity part;

    public TOCEntryEntity(TrayEntity tray_Id, PartEntity part_Id) {
        this.tray = tray_Id;
        this.part = part_Id;
    }

    public TrayEntity getTray() {
        return tray;
    }

    public void setTray(TrayEntity tray_Id) {
        this.tray = tray_Id;
    }

    public PartEntity getPart() {
        return part;
    }

    public void setPart(PartEntity part_Id) {
        this.part = part_Id;
    }

    public PartTray convertToPartTray() {
        PartTray tray1 = PartTray.newBuilder()
                .setTrayId(tray.getId())
                .setPartId(part.getId())
                .build();

        return tray1;
    }
}
