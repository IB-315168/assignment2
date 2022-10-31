package com.sep3yg9.assignment2.controller;

import com.sep3yg9.assignment2.misc.CreateParts;
import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.model.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parts")
public class PartController
{
    public PartController() {
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Part createParts(@RequestBody Animal animal){
        return CreateParts.cutIntoParts(animal);
    }
}
