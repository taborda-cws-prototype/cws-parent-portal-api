package com.tabordasolutions.cws.parentportal.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "users")
@JsonSerialize(using=UserSerializer.class)
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "in_care_of")
    private String inCareOf;
    @Column(name = "address1")
    private String streetAddress1;
    @Column(name = "address2")
    private String streetAddress2;
    @Column(name = "state_abbreviation")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code")
    private String zip;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "email")
    private String email;

    // TODO: map this association
    @Transient
    private List<User> caseworkers;

    // TODO: implement password hashing
    @Transient
    private String password;

    public long getId() {return id; }

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

    public String getInCareOf() {
        return inCareOf;
    }

    public void setInCareOf(String inCareOf) {
        this.inCareOf = inCareOf;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<User> getCaseworkers() {
        return caseworkers;
    }

    public void setCaseworkers(List<User> caseworkers) {
        this.caseworkers = caseworkers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

}
