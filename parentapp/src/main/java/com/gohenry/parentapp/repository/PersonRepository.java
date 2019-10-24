package com.gohenry.parentapp.repository;

import com.gohenry.parentapp.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findById(Long id);
}


