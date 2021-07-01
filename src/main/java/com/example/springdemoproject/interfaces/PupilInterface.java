package com.example.springdemoproject.interfaces;

import com.example.springdemoproject.entities.Pupil;

public interface PupilInterface {

    Pupil create(Pupil pupil);

    Pupil update(Pupil pupil);

    Pupil delete(long id);

    Pupil getById(long id);
}
