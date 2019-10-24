package com.gohenry.parentapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gohenry.parentapp.utility.Utils;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", unique = false, nullable = true, length = 64)
    private String title;

    @Column(name = "first_name", unique = false, nullable = false, length = 128)
    private String firstName;

    @Column(name = "last_name", unique = false, nullable = false, length = 128)
    private String lastName;

    @Column(name = "email_address", unique = false, nullable = true, length = 128)
    private String emailAddress;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date", length = 19, nullable = false)
    private Date birthDate;

    @Column(name = "gender", unique = false, nullable = false, length = 6)
    private String gender;

    @Column(name = "second_name", unique = false, nullable = true, length = 128)
    private String secondName;

    @JsonIgnoreProperties("parent")
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Person parent;

    @OneToMany(mappedBy = "parent")
    private List<Person> children;

    public Person() {

    }

    public Person(final Long id, final String title, final String firstName, final String lastName, final String emailAddress, final Date birthDate,
                  final String gender, final String secondName, final Person parent, final List<Person> children) throws ParseException {
        super();
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.gender = gender;
        this.secondName = secondName;
        this.parent = parent;
        this.children = children;
    }

    public Person(final Long id, final String title, final String firstName, final String lastName, final String emailAddress, final String birthDate,
                  final String gender, final String secondName, final Person parent, final List<Person> children) throws ParseException {
        super();
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = Utils.convertStringToDate(birthDate);
        this.gender = gender;
        this.secondName = secondName;
        this.parent = parent;
        this.children = children;
    }


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


    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }


}
