package com.sep3yg9.assignment2.controller;

import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
}
