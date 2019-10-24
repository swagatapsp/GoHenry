package com.gohenry.parentapp.dao;

import com.gohenry.parentapp.entity.Person;
import com.gohenry.parentapp.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Person parent = null;
    private Person child = null;

    @Before
    public void setUp() throws Exception {
        parent = new Person(null, "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk",
                "1990-06-03", "female", "", null,
                null);
        child = new Person(null, null, "Janet", "Doe", "janet.doe@gohenry.co.uk",
                "2010-05-22", "female", "", parent, null);

    }

    @Test
    public void saveOrUpdateParentAndChild() throws Exception {
        Person savedPerson = testEntityManager.persistFlushFind(parent);
        assertThat(savedPerson.getFirstName()).isEqualTo(parent.getFirstName());
        assertThat(savedPerson.getLastName()).isEqualTo(parent.getLastName());
        Person savedChild = testEntityManager.persistFlushFind(child);
        assertThat(savedChild.getFirstName()).isEqualTo(child.getFirstName());
        assertThat(savedChild.getLastName()).isEqualTo(child.getLastName());
        assertThat(savedChild.getParent().getFirstName()).isEqualTo(child.getParent().getFirstName());

    }

    @Test
    public void findById_ReturnsPerson() throws Exception {
        Person savedPerson = testEntityManager.persistFlushFind(parent);
        Person person = this.personRepository.findById(savedPerson.getId());
        assertThat(person.getFirstName()).isEqualTo(savedPerson.getFirstName());
        assertThat(person.getLastName()).isEqualTo(savedPerson.getLastName());
    }

}
