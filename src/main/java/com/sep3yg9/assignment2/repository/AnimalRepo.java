package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class AnimalRepo
{
  @Autowired
  private HistoryRepo historyRepository;
//  private final Map<Long, Animal> animals = new HashMap<>();
private Map<Long, Animal> animals = new HashMap<>();
  public AnimalRepo() {
//    initDataSource();
  }

  @PostConstruct
  private void initDataSource() {
    animals = historyRepository.getAnimals();
  }

  public Animal create(double weight, String origin) {
    long id = historyRepository.getLastAnimalId() + 1;
    animals.put(id, new Animal(id, weight, origin));
//    CreateParts.cutIntoParts(animals.get(id));
    historyRepository.addToAnimalHistory(animals.get(id));
    return animals.get(id);
  }

  public Animal getAnimal(long regNumber){
    return animals.get(regNumber);
  }

  public ArrayList<Animal> getAnimalsDate(Date date){
    var listOfAnimal = new ArrayList<Animal>();
    animals.forEach((key, value) -> {
      if(value.getArrivedOn().equals(date))
        listOfAnimal.add(value);
    });
    return listOfAnimal;
  }

  public ArrayList<Animal> getAnimalsByOrigin(String origin){
    var listOfAnimal = new ArrayList<Animal>();
    animals.forEach((key, value) -> {
      if(value.getOrigin().equals(origin))
        listOfAnimal.add(value);
    });
    return listOfAnimal;
  }
}