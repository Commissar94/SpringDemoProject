package com.example.springdemoproject.specification;

import com.example.springdemoproject.data.Teacher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TeacherSpecificationFindByName implements Specification<Teacher> {

    private String name;

    public TeacherSpecificationFindByName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (name == null) {
            return cb.isTrue(cb.literal(true));
        }
        return cb.like(root.get("name"), this.name);
    }
}