package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.Part;
import com.sep3yg9.assignment2.model.Tray;

import java.util.HashMap;
import java.util.Map;

public class TrayRespository
{
    //put into tray
    //maximum capacity of tray
    //full tray as a 'history'

    private static final Map<Long, Tray> trays = new HashMap<>();

    static {
        initDataSource();
    }

    private static void initDataSource() {

    }

    public static Tray createTray(double maxWeight, boolean isFinished, String type){
        long id = 1L;
        if(!trays.isEmpty()){
            id = (long) trays.keySet().toArray()[trays.keySet().size() -1]+1;
        }
        trays.put(id, new Tray(id, maxWeight, isFinished, type));
        return trays.get(id);
    }

    public static Tray putPartIntoTray(Tray idTray, Part idPart){

        if(!trays.isEmpty()){
            trays.get(id).setType(idPart.getType());
        }
    }
}
