package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.AnimalEntity;
import com.sep3yg9.assignment2.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository
    extends JpaRepository<ProductEntity, Integer>
{
  List<ProductEntity> findByFinishedFalse();
  List<ProductEntity> findByTocentries_Idpart_Animalid(AnimalEntity animalid);


}