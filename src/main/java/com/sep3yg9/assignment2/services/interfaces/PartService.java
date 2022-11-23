package com.sep3yg9.assignment2.services.interfaces;

import com.sep3yg9.assignment2.model.dbentities.PartEntity;

import java.util.List;

public interface PartService
{
  PartEntity create(PartEntity partEntity);
  List<PartEntity> getAllRemainingParts();
}
