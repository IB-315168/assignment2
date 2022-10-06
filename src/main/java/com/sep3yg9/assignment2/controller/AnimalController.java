package com.sep3yg9.assignment2.controller;

import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/animals")
public class AnimalController
{
  public AnimalController() {}


  @RequestMapping(method = RequestMethod.POST,
          produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Animal createOrder(@RequestBody Animal animal) {
    return AnimalRepository.create(animal.getWeight(), animal.getOrigin());
  }

  @RequestMapping(value="/get/{regNumber}", method = RequestMethod.GET)
  public Animal getAnimal(@PathVariable long regNumber){
    return AnimalRepository.getAnimal(regNumber);
  }

  @RequestMapping(value = "/get/{year}/{month}/{day}", method = RequestMethod.GET)
  public ArrayList<Animal> getAnimalsByDate(@PathVariable int year, int month, int day){
    Date date = new Date(year,month,day);
    return AnimalRepository.getAnimalsDate(date);
  }
}
