package com.example.springdemoproject.controllers;

import com.example.springdemoproject.data.Pupil;
import com.example.springdemoproject.dto.PupilData;
import com.example.springdemoproject.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pupils")
public class PupilController {


    @Autowired
    private PupilService pupilService;

    /**
     * Method to find pupil by query (HQL)
     */
    @GetMapping("/find")
    public List<Pupil> findPupilQuery(@RequestParam String name) {
        return pupilService.findByNameQuery(name);
    }


    /**
     * Method to find pupil by name
     */

    @GetMapping
    public List<Pupil> findPupil(@RequestParam("name") String name) {
        return pupilService.findByNameSpecification(name);
    }

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
    public void deletePupil(@RequestParam(value = "id") long id) {
        pupilService.deletePupil(id);
    }

    /**
     * Method to update the pupil.
     */

    @PutMapping
    public PupilData updatePupil(@RequestBody PupilData pupilData, @RequestParam(value = "id") long id) throws Exception {
        return pupilService.updatePupil(pupilData, id);
    }
}


