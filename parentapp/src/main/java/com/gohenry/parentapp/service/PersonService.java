package com.gohenry.parentapp.service;

import com.gohenry.parentapp.entity.Person;
import com.gohenry.parentapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    public PersonService(final PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id);
    }

}
