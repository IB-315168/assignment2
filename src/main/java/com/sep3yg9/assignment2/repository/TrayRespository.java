package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.grpc.protobuf.parts.Part;
import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PartRepository partRepository;

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

    public TrayEntity putPartIntoTray(long tray, long part){
        PartEntity part1 = new PartEntity(partRepository.getPart(part));

        if(trays.get(tray).getParts().size() == 0){
            if(!trayChecks(tray, part1)) {
                System.out.println("One of the checks failed, verify tray weight and part type");
                return trays.get(tray);
            }
            trays.get(tray).setType(part1.getType());
            trays.get(tray).addPart(part1);
            partRepository.removePart(part);
        } else {
            if(!trayChecks(tray, part1)) {
//                throw new Exception("One of the checks failed, verify tray weight and part type");
                System.out.println("One of the checks failed, verify tray weight and part type");
            } else {
                trays.get(tray).addPart(part1);
                partRepository.removePart(part);
            }
        }

        return trays.get(tray);
    }

    public List<Tray> getAllTrays() {
        List<Tray> trays1 = new ArrayList<>();
        for(long id : trays.keySet()) {
            trays1.add(trays.get(id).convertToTray());
        }
        return trays1;
    }

    private boolean trayChecks(long id, PartEntity part) {
        TrayEntity tray = trays.get(id);

        if(!tray.getType().equals(part.getType())) {
            return false;
        }

        if(tray.getCarriedWeight() + part.getWeight() > tray.getMax_weight()) {
            return false;
        }

        return true;
    }


}
