package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.Gender;
import org.example.prog3_agriculturalfederation.entity.enums.MemberOccupation;

import java.time.LocalDate;
import java.util.Date;

public class CreateMember extends Member {
    public CreateMember(String address, Date birthDate, LocalDate dateAdhesion, String email, String firstName, Gender gender, String lastName, MemberOccupation occupation, String phoneNumber, String profession) {
        super(address, birthDate, dateAdhesion, email, firstName, gender, lastName, occupation, phoneNumber, profession);
    }
}
