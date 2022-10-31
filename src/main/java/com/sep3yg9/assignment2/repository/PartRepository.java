package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.model.TrayEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PartRepository
{
    private final Map<Long, PartEntity> trays = new HashMap<>();

    public PartRepository() {
        initDataSource();
    }

    private void initDataSource() {
        //put file context for serialization
    }


}
