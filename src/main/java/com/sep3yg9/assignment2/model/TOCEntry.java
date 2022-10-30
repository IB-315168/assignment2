package com.sep3yg9.assignment2.model;

public class TOCEntry {
    private long tray_Id;
    private long part_Id;

    public TOCEntry(long tray_Id, long part_Id) {
        this.tray_Id = tray_Id;
        this.part_Id = part_Id;
    }

    public long getTray_Id() {
        return tray_Id;
    }

    public void setTray_Id(long tray_Id) {
        this.tray_Id = tray_Id;
    }

    public long getPart_Id() {
        return part_Id;
    }

    public void setPart_Id(long part_Id) {
        this.part_Id = part_Id;
    }
}
