package org.example.prog3_agriculturalfederation.dto;

import java.util.List;

public class CreateCollectivityDTO {
    private String location;
    private Boolean federationApproval;
    private CreateCollectivityStructureDTO structure;
    private List<String> members;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
