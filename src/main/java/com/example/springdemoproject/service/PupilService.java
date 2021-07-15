package com.example.springdemoproject.service;

import com.example.springdemoproject.data.Pupil;
import com.example.springdemoproject.dto.PupilData;

import java.util.List;

public interface PupilService {

    PupilData createPupil (PupilData pupil);
    PupilData updatePupil(PupilData pupil, long id) throws Exception;
    boolean deletePupil(long pupilId);
    PupilData getPupilById(long pupilId);
    List<Pupil> findByNameQuery(String name);
    List<Pupil> findByNameSpecification(String name);
}
