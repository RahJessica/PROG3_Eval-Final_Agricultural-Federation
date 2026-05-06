package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.dto.RefereeDTO;
import org.example.prog3_agriculturalfederation.entity.enums.Gender;
import org.example.prog3_agriculturalfederation.entity.enums.MemberOccupation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    public List<RefereeDTO> referees;
    private Integer collectivityId;

    public Member(Integer id, String lastName, String firstName, LocalDate birthDate, Gender gender, String phoneNumber, String email, LocalDate dateAdhesion, String address, String profession, MemberOccupation occupation, List<RefereeDTO> referees) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateAdhesion = dateAdhesion;
        this.address = address;
        this.profession = profession;
        this.occupation = occupation;
        this.referees = referees;
    }

    public Member() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(LocalDate dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public MemberOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(MemberOccupation occupation) {
        this.occupation = occupation;
    }

    public List<RefereeDTO> getReferees() {
        return referees;
    }

    public void setReferees(List<RefereeDTO> referees) {
        this.referees = referees;
    }

    public Integer getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(Integer collectivityId) {
        this.collectivityId = collectivityId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateAdhesion=" + dateAdhesion +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
                ", occupation=" + occupation +
                '}';
    }
}
