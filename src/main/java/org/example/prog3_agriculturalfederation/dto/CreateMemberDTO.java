package org.example.prog3_agriculturalfederation.dto;

import java.time.LocalDate;
import java.util.List;

public class CreateMemberDTO {
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public String gender;
    public String address;
    public String profession;
    public String phoneNumber;
    public String email;
    public String occupation;

    public String collectivityIdentifier;
    public List<String> referees;

    public boolean registrationFeePaid;
    public boolean membershipDuesPaid;

}
