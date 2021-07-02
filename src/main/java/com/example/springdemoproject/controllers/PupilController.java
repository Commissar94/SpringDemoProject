package com.example.springdemoproject.controllers;

import com.example.springdemoproject.dto.PupilData;
import com.example.springdemoproject.service.PupilService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/pupils")
public class PupilController {

    @Resource(name = "pupilService")
    private PupilService pupilService;

    /**
     * Method to get the pupil.
     */

    @GetMapping({"/{id}"})
    public PupilData getPupilsById(@PathVariable Long id) {
        return pupilService.getPupilById(id);
    }

    /**
     * Method to create new pupil.
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PupilData createPupil(@RequestBody PupilData pupilData) {
        return pupilService.createPupil(pupilData);
    }

    /**
     * Method to delete the pupil.
     */

    @DeleteMapping
   public void deletePupil(@RequestParam(value = "id") long id){
        pupilService.deletePupil(id);
    }

    /**
     * Method to update the pupil.
     */

    @PutMapping
    public PupilData updatePupil(@RequestBody PupilData pupilData, @RequestParam(value = "id") long id) throws Exception {
        return pupilService.updatePupil(pupilData,id);
    }
}


