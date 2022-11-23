package com.sep3yg9.assignment2.controller;

import com.sep3yg9.assignment2.model.dbentities.AnimalEntity;
import com.sep3yg9.assignment2.services.interfaces.AnimalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/animals") public class AnimalController
{
  private final AnimalService animalService;

  public AnimalController(AnimalService animalService)
  {
    this.animalService = animalService;
  }

  @RequestMapping(method = RequestMethod.POST, produces = {
      MediaType.APPLICATION_JSON_VALUE}) @ResponseStatus(HttpStatus.CREATED) @ResponseBody public ResponseEntity<Object> createOrder(
      @RequestBody AnimalEntity animalEntity)
  {
    animalEntity.setArrivedon(LocalDate.now());
    AnimalEntity createdAnimalEntity = animalService.create(animalEntity);
    return new ResponseEntity<>(createdAnimalEntity, HttpStatus.OK);
  }

  @RequestMapping(value = "/get/{regNumber}", method = RequestMethod.GET) public ResponseEntity<Object> getAnimal(
      @PathVariable int regNumber)
  {
    try
    {
      Optional<AnimalEntity> animal = animalService.getByRegNumber(regNumber);
      if (animal.isPresent())
      {
        return new ResponseEntity<>(animal.get(), HttpStatus.OK);
      }
      else
      {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
    catch (Exception ex)
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping("/getByDate/{date}") public ResponseEntity<Object> getAnimalsByDate(
      @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
  {
    try
    {
      List<AnimalEntity> animalsByDate = animalService.getByDate(date);
      return new ResponseEntity<>(animalsByDate, HttpStatus.OK);
    }
    catch (Exception ex)
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/getByOrigin/{origin}", method = RequestMethod.GET) public ResponseEntity<Object> getAnimalsByOrigin(
      @PathVariable String origin)
  {
    try
    {
      List<AnimalEntity> animalsByOrigin = animalService.getAnimalsByOrigin(origin);
      return new ResponseEntity<>(animalsByOrigin, HttpStatus.OK);
    }
    catch (Exception ex)
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
