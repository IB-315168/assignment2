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
public class PartRepository
{
    @Autowired
    private HistoryRepository historyRepository;
    private final Map<Long, PartEntity> parts = new HashMap<>();

    public PartRepository() {
        initDataSource();
    }

    private void initDataSource() {
        //put file context for serialization
    }

    public PartEntity createPart(long animal_id, String type, double weight){
        long id = historyRepository.getLastPartId() + 1;
        parts.put(id, new PartEntity(id, animal_id, type, weight));
        historyRepository.addToPartHistory(parts.get(id));
        return parts.get(id);
    }

    public void removePart(long partId) {
        parts.remove(partId);
    }

    public Part getPart(long id) {
        return parts.get(id).convertToPart();
    }

    public List<Part> getAllParts() {
        List<Part> parts1 = new ArrayList<>();
        for(long id : parts.keySet()) {
            parts1.add(parts.get(id).convertToPart());
        }
        return parts1;
    }
}
