package com.sep3yg9.assignment2.services;

import com.sep3yg9.assignment2.model.dbentities.AnimalEntity;
import com.sep3yg9.assignment2.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service public class AnimalServiceImpl implements com.sep3yg9.assignment2.services.interfaces.AnimalService
{
  private final AnimalRepository animalRepository;

  public AnimalServiceImpl(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @Override public AnimalEntity create(AnimalEntity animalEntity) {
    return animalRepository.save(animalEntity);
  }

  @Override public Optional<AnimalEntity> getByRegNumber(int id) {
    return animalRepository.findById(id);
  }

  @Override public List<AnimalEntity> getByDate(LocalDate date) {
    return animalRepository.findByArrivedon(date);
  }

  @Override public List<AnimalEntity> getAnimalsByOrigin(String origin) {
    return animalRepository.findByOrigin(origin);
  }
}
