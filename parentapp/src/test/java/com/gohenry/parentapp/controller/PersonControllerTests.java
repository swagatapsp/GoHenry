package com.gohenry.parentapp.controller;

import com.gohenry.parentapp.domain.CreatePersonDTO;
import com.gohenry.parentapp.entity.Person;
import com.gohenry.parentapp.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;
	
	@MockBean
	private ModelMapper modelMapper;
	
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private CreatePersonDTO parentDto = null;
	private CreatePersonDTO childDto = null;
	
	private Person parent = null;
	private Person child = null;
	
	@Before
	public void setUp() throws Exception {	
		parent = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,
				Arrays.asList(child));
		child = new Person(new Long(2), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", parent, null);
		
		parentDto = new CreatePersonDTO("Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk",
				"1990-06-03", "female", "", null);
		childDto = new CreatePersonDTO(null, "Janet", "Doe", "janet.doe@gohenry.co.uk",
				"2010-05-22", "female", "", new Long(1));
		
	}
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
    @Test
	public void saveParent() throws Exception {
		Person child = new Person(new Long(1), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", null, null);
		
		Person person = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,
				Arrays.asList(child));
		
		when(this.personService.savePerson(any())).thenReturn(person);
		 this.mockMvc.perform(post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(parentDto)))
                .andExpect(status().isOk());
	}
    
	@Test
	public void saveChild() throws Exception {
		Person child = new Person(new Long(1), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", null, null);
		
		Person person = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,null);
		
		when(this.personService.savePerson(any())).thenReturn(person);
		 this.mockMvc.perform(post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(childDto)))
                .andExpect(status().isOk());
	}
	
	@Test
	public void getPerson_WithId_ReturnsPerson() throws Exception {
		when(this.personService.getPersonById(new Long(1))).thenReturn(parent);
		this.mockMvc.perform(get("/parents/{id}", new Long(1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(new Long(1)))
				.andExpect(jsonPath("firstName").value("Jane"))
				.andExpect(jsonPath("lastName").value("Doe"))
				.andExpect(jsonPath("emailAddress").value("jane.doe@gohenry.co.uk"))
				.andExpect(jsonPath("dateOfBirth").value("1990-06-03"))
				.andExpect(jsonPath("gender").value("female"))
				.andExpect(jsonPath("secondName").value(""));
	}

	@Test
	public void getPerson_NotFound_Returns404() throws Exception {
		when(this.personService.getPersonById(any())).thenReturn(null);
		this.mockMvc.perform(get("/parents/{id}", new Long(4)))
				.andExpect(status().isNotFound());
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
               o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
