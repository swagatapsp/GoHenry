package com.gohenry.parentapp.domain;

import com.gohenry.parentapp.utility.Utils;

import java.text.ParseException;
import java.util.Date;

public class CreatePersonDTO {

    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String dateOfBirth;
    private String gender;
    private String secondName;
    private Long parentId;
    private Date birthDate;

    public CreatePersonDTO() {
    }

    public CreatePersonDTO(String title, String firstName, String lastName, String emailAddress, String dateOfBirth,
                           String gender, String secondName, Long parentId) throws ParseException {
        super();
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.birthDate = Utils.convertStringToDate((dateOfBirth));
        this.gender = gender;
        this.secondName = secondName;
        this.parentId = parentId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        if (birthDate != null) {
            this.dateOfBirth = Utils.convertDateToString(this.getBirthDate());

        }
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
