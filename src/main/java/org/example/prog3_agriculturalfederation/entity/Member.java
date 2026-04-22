package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.Gender;
import org.example.prog3_agriculturalfederation.entity.enums.MemberOccupation;

import java.time.LocalDate;
import java.util.Date;

public class Member {
    private Integer id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate ;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private LocalDate dateAdhesion;
    private String address;
    private String profession;
    private MemberOccupation occupation;


    public Member() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(LocalDate dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MemberOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(MemberOccupation occupation) {
        this.occupation = occupation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Member(Integer id, String address, LocalDate birthDate, LocalDate dateAdhesion, String email, String firstName, Gender gender, String lastName, MemberOccupation occupation, String phoneNumber, String profession) {
        this.id = id;
        this.address = address;
        this.birthDate = birthDate;
        this.dateAdhesion = dateAdhesion;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.occupation = occupation;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "MemberInformation{" +
                "address='" + address + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateAdhesion=" + dateAdhesion +
                ", profession='" + profession + '\'' +
                ", occupation=" + occupation +
                '}';
    }
}
