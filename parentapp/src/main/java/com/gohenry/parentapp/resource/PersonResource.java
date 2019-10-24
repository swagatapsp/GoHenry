package com.gohenry.parentapp.resource;

import com.gohenry.parentapp.domain.CreatePersonDTO;
import com.gohenry.parentapp.domain.PersonDTO;
import com.gohenry.parentapp.entity.Person;
import com.gohenry.parentapp.exception.PersonNotFoundException;
import com.gohenry.parentapp.service.PersonService;
import com.gohenry.parentapp.utility.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;


@RestController
public class PersonResource {

    @Autowired
    private PersonService personService;

    @Autowired
    public PersonResource(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
    }

    @RequestMapping(value = "/parents", method = RequestMethod.POST)
    public ResponseEntity<?> savePerson(HttpServletRequest request, @RequestBody final CreatePersonDTO createPersonDTO) throws ParseException {
        Long personId = personService.savePerson(convertToEntity(createPersonDTO)).getId();
        return ResponseEntity.ok(personId);

    }

    @RequestMapping(value = "/parents/{id}", method = RequestMethod.GET)
    public PersonDTO getPerson(HttpServletRequest request, @PathVariable Long id) throws ParseException {
        Person person = personService.getPersonById(id);
        if (person == null) {
            throw new PersonNotFoundException();
        }
        return convertToDTO(person);
    }

    private PersonDTO convertToDTO(Person person) {
        ModelMapper modelMapper = new ModelMapper();
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }

    private Person convertToEntity(CreatePersonDTO createPersonDTO) throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        Person person = modelMapper.map(createPersonDTO, Person.class);
        person.setBirthDate(Utils.convertStringToDate(createPersonDTO.getDateOfBirth()));
        if (createPersonDTO.getParentId() != null) {
            Person parent = personService.getPersonById(createPersonDTO.getParentId());
            person.setParent(parent);
        }

        return person;
    }
}
