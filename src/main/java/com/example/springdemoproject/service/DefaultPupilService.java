package com.example.springdemoproject.service;

import com.example.springdemoproject.data.Pupil;
import com.example.springdemoproject.dto.PupilData;
import com.example.springdemoproject.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("pupilService")
public class DefaultPupilService implements PupilService {

    @Autowired
    private PupilRepository pupilRepository;


    @Override
    public PupilData createPupil(PupilData pupil) {
        Pupil pupilModel = populatePupilEntity(pupil);

        return populatePupilData(pupilRepository.save(pupilModel));
    }

    @Override
    public PupilData updatePupil(PupilData pupil, long id) throws Exception {
        Pupil pupilModel = populatePupilEntity(pupil);
        pupilModel.setId(id);
        pupilRepository.findById(id).orElseThrow(()-> new Exception("No pupils with this id"));

        return populatePupilData(pupilRepository.save(pupilModel));
    }

    @Override
    public boolean deletePupil(long pupilId) {
        pupilRepository.deleteById(pupilId);

        return true;
    }

    @Override
    public PupilData getPupilById(long pupilId) {
        return populatePupilData(pupilRepository.findById(pupilId).orElseThrow(() -> new EntityNotFoundException("Pupil not found")));
    }

    private PupilData populatePupilData(Pupil pupil) {
        PupilData pupilData = new PupilData();
        pupilData.setId(pupil.getId());
        pupilData.setName(pupil.getName());
        pupilData.setSchoolClass(pupil.getSchoolClass());

        return pupilData;
    }

    private Pupil populatePupilEntity(PupilData pupilData) {
        Pupil pupil = new Pupil();
        pupil.setName(pupilData.getName());
        pupil.setSchoolClass(pupilData.getSchoolClass());

        return pupil;
    }
}
