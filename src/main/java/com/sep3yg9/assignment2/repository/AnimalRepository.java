package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer>
{
  List<AnimalEntity> findByArrivedon(LocalDate arrivedon);
  List<AnimalEntity> findByOrigin(String origin);


}