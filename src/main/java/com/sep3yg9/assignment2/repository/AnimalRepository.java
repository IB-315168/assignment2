package com.sep3yg9.assignment2.repository;

import com.fasterxml.jackson.databind.DatabindException;
import com.sep3yg9.assignment2.misc.CreateParts;
import com.sep3yg9.assignment2.model.Animal;

import java.util.*;

public class AnimalRepository
{
  private static final Map<Long, Animal> animals = new HashMap<>();

  static {
    initDataSource();
  }

  private static void initDataSource() {

  }

  public static Animal create(double weight, String origin) {
    long id = 1L;
    if(!animals.isEmpty()) {
      id = (long) animals.keySet().toArray()[animals.keySet().size() -1]+1;
    }
    animals.put(id, new Animal(id, weight, origin));
    CreateParts.cutIntoParts(animals.get(id));
    return animals.get(id);
  }

  public static Animal getAnimal(long regNumber){
    return animals.get(regNumber);
  }

  public static ArrayList<Animal> getAnimalsDate(Date date){
    var listOfAnimal = new ArrayList<Animal>();
    animals.forEach((key, value) -> {
      if(value.getArrivedOn().equals(date))
        listOfAnimal.add(value);
    });
    return listOfAnimal;
  }

  public static ArrayList<Animal> getAnimalsByOrigin(String origin){
    var listOfAnimal = new ArrayList<Animal>();
    animals.forEach((key, value) -> {
      if(value.getOrigin().equals(origin))
        listOfAnimal.add(value);
    });
    return listOfAnimal;
  }
}
