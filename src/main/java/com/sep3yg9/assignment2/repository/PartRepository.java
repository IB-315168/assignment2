package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository<PartEntity, Integer>
{
  List<PartEntity> findByFinishedFalse();

}