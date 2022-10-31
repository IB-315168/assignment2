package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.parts.Tray;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrayRespository
{
    //put into tray
    //maximum capacity of tray
    //full tray as a 'history'

    private final Map<Long, TrayEntity> trays = new HashMap<>();

    public TrayRespository() {
        initDataSource();
    }

    private void initDataSource() {
        //put file context for serialization
    }

    public TrayEntity createTray(double maxWeight, boolean isFinished, String type){
        long id = 1L;
        if(!trays.isEmpty()){
            id = (long) trays.keySet().toArray()[trays.keySet().size() -1]+1;
        }
        trays.put(id, new TrayEntity(id, maxWeight, isFinished, type));
        return trays.get(id);
    }

    public TrayEntity putPartIntoTray(TrayEntity tray, PartEntity part){
        if(trays.get(tray.getId()).getParts().size() == 0){
            trays.get(tray.getId()).setType(part.getType());
            trays.get(tray.getId()).addPart(part);
        } else {
            if(!trayChecks(tray.getId(), part)) {
//                throw new Exception("One of the checks failed, verify tray weight and part type");
            } else {
                trays.get(tray.getId()).addPart(part);
            }
        }

        return trays.get(tray.getId());
    }
}
