package com.sep3yg9.assignment2.repository;

import com.sep3yg9.assignment2.model.Animal;

import java.util.HashMap;
import java.util.Map;

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
      id = (long) animals.keySet().toArray()[animals.keySet().size() - 1];
    }

    return animals.put(id, new Animal(id, weight, origin));
  }
}
