package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.grpc.protobuf.trays.Tray;
import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class TrayRepository
{

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private HistoryRepository historyRepository;

    private Map<Long, TrayEntity> trays;

    public TrayRepository() {
    }

    //We assume that trays properties won't change over time, i.e. maxWeight
    @PostConstruct
    private void initDataSource() {
        Map<Long, TrayEntity> trayList = new HashMap<>();
        if(historyRepository.getTrays() != null && !historyRepository.getTrays().isEmpty()) {
            for(long id : historyRepository.getTrays().keySet()) {
                if(historyRepository.getTrays().get(id).size() != 0) {
                    TrayEntity tray = historyRepository.getTrays().get(id).get(0);
                    trayList.put(id, new TrayEntity(id, tray.getMax_weight(), false, tray.getType()));
                }
            }
        }
        trays = trayList;
    }

    public TrayEntity createTray(double maxWeight, boolean isFinished, String type){
        long id = historyRepository.getLastTrayId() + 1;
        if(trays.keySet().size() != 0 && Collections.max(trays.keySet()) > id) {
            id = Collections.max(trays.keySet()) + 1;
        }
        trays.put(id, new TrayEntity(id, maxWeight, isFinished, type));
        historyRepository.addNewTray(id);
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
                System.out.println("One of the checks failed, verify tray weight and part type");
            } else {
                trays.get(tray).addPart(part1);
                partRepository.removePart(part);
            }
        }

        return trays.get(tray);
    }

    public List<Tray> getAllTrays(boolean finished) {
        List<Tray> trays1 = new ArrayList<>();
        for(long id : trays.keySet()) {
            if(trays.get(id).isFinished() == finished) {
                trays1.add(trays.get(id).convertToTray());
            }
        }
        return trays1;
    }

    public Tray trayFinished(long idTray) {
        if(!trays.get(idTray).isFinished() && trays.get(idTray).getParts().size() != 0) {
            trays.get(idTray).setFinished(true);
            historyRepository.addToTrayHistory(trays.get(idTray));
        }
        else {
            System.out.println("Tray is already finished.");
        }

        return trays.get(idTray).convertToTray();
    }

    public void trayUnfinished(long idTray) {
        if(trays.get(idTray).isFinished()) {
            trays.get(idTray).setFinished(false);
        }
        else {
            System.out.println("Tray is not finished.");
        }
    }

    public TrayEntity removeFromTray(PartEntity part) {
        for(long id : trays.keySet()) {
            if(trays.get(id).isFinished()) {
                if(trays.get(id).getParts().contains(part)) {
                    trays.get(id).getParts().remove(part);
                    return trays.get(id);
                }
            }
        }
        TrayEntity tray = new TrayEntity();
        tray.setId(-1);
        return tray;
    }

    private boolean trayChecks(long id, PartEntity part) {
        TrayEntity tray = trays.get(id);

        if(tray.isFinished()) {
            return false;
        }

        if(!tray.getType().equals(part.getType())) {
            return false;
        }

        if(tray.totalPartsWeight() + part.getWeight() > tray.getMax_weight()) {
            return false;
        }

        return true;
    }


}
