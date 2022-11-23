package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.dbentities.TrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrayEntityRepository extends JpaRepository<TrayEntity, Integer>
{
  List<TrayEntity> findByFinishedFalse();
  List<TrayEntity> findByFinishedTrue();
  Optional<TrayEntity> findByParttrays_Id_PartId(int partId);


}