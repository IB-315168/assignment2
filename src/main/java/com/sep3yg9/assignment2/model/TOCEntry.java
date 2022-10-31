package com.sep3yg9.assignment2.model;

public class TOCEntry {
    private Tray tray_Id;
    private Part part_Id;

    public TOCEntry(Tray tray_Id, Part part_Id) {
        this.tray_Id = tray_Id;
        this.part_Id = part_Id;
    }

    public Tray getTray_Id() {
        return tray_Id;
    }

    public void setTray_Id(Tray tray_Id) {
        this.tray_Id = tray_Id;
    }

    public Part getPart_Id() {
        return part_Id;
    }

    public void setPart_Id(Part part_Id) {
        this.part_Id = part_Id;
    }
}
