package org.example.prog3_agriculturalfederation.dto;

import java.util.List;

public class CreateCollectivityDTO {
    private String town;
    private Boolean federationApproval;
    private CreateCollectivityStructureDTO structure;
    private List<Integer> members; //reference des members
    private List<RefereeDTO> referees;


    public String getTown() {
        return town;
    }

    public void setTown(String location) {
        this.town = location;
    }

    public Boolean getFederationApproval() {
        return federationApproval;
    }

    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }

    public CreateCollectivityStructureDTO getStructure() {
        return structure;
    }

    public void setStructure(CreateCollectivityStructureDTO structure) {
        this.structure = structure;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public List<RefereeDTO> getReferees() {
        return referees;
    }

    public void setReferees(List<RefereeDTO> referees) {
        this.referees = referees;
    }
}
