package com.sep3yg9.assignment2.model;

public class TOCEntry {
    private TrayEntity tray_Id;
    private PartEntity part_Id;

    public TOCEntry(TrayEntity tray_Id, PartEntity part_Id) {
        this.tray_Id = tray_Id;
        this.part_Id = part_Id;
    }

    public TrayEntity getTray_Id() {
        return tray_Id;
    }

    public void setTray_Id(TrayEntity tray_Id) {
        this.tray_Id = tray_Id;
    }

    public PartEntity getPart_Id() {
        return part_Id;
    }

    public void setPart_Id(PartEntity part_Id) {
        this.part_Id = part_Id;
    }
}
