package com.example.springdemoproject.service;

import com.example.springdemoproject.data.Pupil;
import com.example.springdemoproject.dto.PupilData;
import com.example.springdemoproject.repository.PupilRepository;
import com.example.springdemoproject.specification.PupilSpecificationFindByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
        pupilRepository.findById(id).orElseThrow(() -> new Exception("No pupils with this id"));

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

    @Override
    public List<Pupil> findByNameQuery(String name) {
        return pupilRepository.findByNameQuery(name);
    }

    @Override
    public List<Pupil> findByNameSpecification(String name) {
        Specification<Pupil> spec = Specification.where(new PupilSpecificationFindByName(name));
        return pupilRepository.findAll(spec);
    }


    private PupilData populatePupilData(Pupil pupil) {
        PupilData pupilData = new PupilData();
        pupilData.setId(pupil.getId());
        pupilData.setName(pupil.getName());

        return pupilData;
    }

    private Pupil populatePupilEntity(PupilData pupilData) {
        Pupil pupil = new Pupil();
        pupil.setName(pupilData.getName());

        return pupil;
    }
}
