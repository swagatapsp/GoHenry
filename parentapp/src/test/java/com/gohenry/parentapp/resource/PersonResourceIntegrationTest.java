package com.gohenry.parentapp.resource;


import com.gohenry.parentapp.domain.CreatePersonDTO;
import com.gohenry.parentapp.domain.PersonDTO;
import com.gohenry.parentapp.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonResourceIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private CreatePersonDTO parentDTO = null;
    private CreatePersonDTO childDTO = null;

    private Person parent = null;
    private Person child = null;

    @Before
    public void setUp() throws Exception {
        parent = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk",
                "1990-06-03", "female", "", null,
                Arrays.asList(child));
        child = new Person(new Long(2), null, "Janet", "Doe", "janet.doe@gohenry.co.uk",
                "2010-05-22", "female", "", parent, null);

        parentDTO = new CreatePersonDTO("Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk",
                "1990-06-03", "female", "", null);
        childDTO = new CreatePersonDTO(null, "Janet", "Doe", "janet.doe@gohenry.co.uk",
                "2010-05-22", "female", "", new Long(1));

    }

    @Test
    public void shouldReturnPersonAfterPersonCreated() throws ParseException {
        HttpEntity<CreatePersonDTO> request = new HttpEntity<>(parentDTO);
        Integer resultPersonId = testRestTemplate.postForObject("/parents", request, Integer.class);

        //GET
        ResponseEntity<PersonDTO> responseEntity = this.testRestTemplate.getForEntity("/parents/{id}", PersonDTO.class, resultPersonId);
        PersonDTO personResult = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personResult.getFirstName()).isEqualTo("Jane");
        assertThat(personResult.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void shouldReturnParentAfterPersonAndChildCreated() throws ParseException {

        HttpEntity<CreatePersonDTO> request = new HttpEntity<>(parentDTO);
        Long resultPersonId = testRestTemplate.postForObject("/parents", request, Long.class);

        childDTO.setParentId(resultPersonId);
        HttpEntity<CreatePersonDTO> requestChild = new HttpEntity<>(childDTO);
        testRestTemplate.postForObject("/parents", requestChild, Long.class);

        //GET
        ResponseEntity<PersonDTO> responseEntity = this.testRestTemplate.getForEntity("/parents/{id}", PersonDTO.class, resultPersonId);
        PersonDTO personResult = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personResult.getFirstName()).isEqualTo("Jane");
        assertThat(personResult.getLastName()).isEqualTo("Doe");
        assertThat(personResult.getChildren().size()).isEqualTo(1);
        assertThat(personResult.getChildren().get(0).getFirstName()).isEqualTo("Janet");
    }

    @Test
    public void shouldReturnNotFoundWhenParentIsSearchedWithInvalidParentId() throws Exception {
        Random random = new Random();
        int id = random.nextInt(100000);

        ResponseEntity<PersonDTO> responseEntity = this.testRestTemplate.getForEntity("/parents/{id}", PersonDTO.class, id);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
