package com.sep3yg9.assignment2.services.interfaces;

import com.sep3yg9.assignment2.model.AnimalEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnimalService
{
  AnimalEntity create(AnimalEntity animalEntity);
  Optional<AnimalEntity> getByRegNumber(int id);
  List<AnimalEntity> getByDate(LocalDate date);
  List<AnimalEntity> getAnimalsByOrigin(String origin);
}
