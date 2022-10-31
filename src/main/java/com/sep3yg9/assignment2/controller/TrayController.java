package com.sep3yg9.assignment2.controller;

import com.sep3yg9.assignment2.misc.CreateParts;
import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.model.Part;
import com.sep3yg9.assignment2.model.TOCEntry;
import com.sep3yg9.assignment2.model.Tray;
import com.sep3yg9.assignment2.repository.TrayRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trays")
public class TrayController {
    public TrayController(){

    }
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tray createTray(@RequestBody Tray tray){
        return TrayRespository.createTray(tray.getMax_weight(), tray.isFinished(),tray.getType());
    }

    @RequestMapping(value="/put",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tray putPartIntoTray(@RequestBody TOCEntry tocEntry){
        return TrayRespository.putPartIntoTray(tocEntry.getTray_Id(),tocEntry.getPart_Id());
    }


}
