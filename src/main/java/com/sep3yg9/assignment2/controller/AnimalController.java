package com.sep3yg9.assignment2.controller;

import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.repository.AnimalRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
  @RequestMapping(value="/all",method = RequestMethod.GET)
  public Map<Long,Animal>getAllAnimals(){
    return AnimalRepository.getAllAnimals();
  }
  @RequestMapping(value="/get/{regNumber}", method = RequestMethod.GET)
  public Animal getAnimal(@PathVariable long regNumber){
    return AnimalRepository.getAnimal(regNumber);
  }

  @RequestMapping("/getByDate/{date}")
  public ArrayList<Animal> getAnimalsByDate(@PathVariable ("date")@DateTimeFormat(pattern="yyyy-MM-dd")Date date){
    return AnimalRepository.getAnimalsDate(date);
  }
  @RequestMapping(value = "/getByOrigin/{origin}", method = RequestMethod.GET)
  public ArrayList<Animal> getAnimalsByOrigin(@PathVariable String origin){
    return AnimalRepository.getAnimalsByOrigin(origin);
  }
}
