package com.example.springdemoproject.specification;

import com.example.springdemoproject.data.Pupil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PupilSpecificationFindByName implements Specification<Pupil> {

    private String name;

    public PupilSpecificationFindByName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Pupil> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (name == null) {
            return cb.isTrue(cb.literal(true));
        }
        return cb.equal(root.get("name"), this.name);
    }
}