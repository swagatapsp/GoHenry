package com.gohenry.parentapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gohenry.parentapp.utility.Utils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class PersonDTO {

    private Long id;

    @JsonInclude(NON_NULL)
    private String title;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String dateOfBirth;
    private String gender;
    private String secondName;

    @JsonIgnore
    private Date birthDate;

    @JsonInclude(NON_NULL)
    private List<PersonDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() throws ParseException {
        if (this.birthDate != null) {
            return this.dateOfBirth = Utils.convertDateToString(this.getBirthDate());
        }
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<PersonDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PersonDTO> children) {
        this.children = children;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


}
