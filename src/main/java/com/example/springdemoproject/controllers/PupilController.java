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

    @GetMapping({"/{id}"})
    public PupilData getPupilsById(@PathVariable Long id) {
        return pupilService.getPupilById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PupilData createPupil(@RequestBody PupilData pupilData) {
        return pupilService.createPupil(pupilData);
    }


    @DeleteMapping
   public void deletePupil(@RequestParam(value = "id") long id){
        pupilService.deletePupil(id);
    }

    @PutMapping
    public PupilData updatePupil(@RequestBody PupilData pupilData, @RequestParam(value = "id") long id) throws Exception {
        return pupilService.updatePupil(pupilData,id);
    }
}


