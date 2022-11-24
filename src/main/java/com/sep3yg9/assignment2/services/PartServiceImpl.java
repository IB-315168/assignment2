package com.sep3yg9.assignment2.services;

import com.sep3yg9.assignment2.model.PartEntity;
import com.sep3yg9.assignment2.repository.PartRepository;
import com.sep3yg9.assignment2.services.interfaces.PartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class PartServiceImpl implements PartService
{
  private final PartRepository partRepository;

  public PartServiceImpl(PartRepository partRepository)
  {
    this.partRepository = partRepository;
  }

  @Override public PartEntity create(PartEntity partEntity) {
    return partRepository.save(partEntity);
  }

  @Override public List<PartEntity> getAllRemainingParts() {
    return partRepository.findByFinishedFalse();
  }
}
