package org.example.prog3_agriculturalfederation.dto;

import java.util.List;

public class CollectivityDTO {
    private Integer id;
    private String location;
    private List<MemberDTO> members;
    private CollectivityStructureDTO structure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDTO> members) {
        this.members = members;
    }

    public CollectivityStructureDTO getStructure() {
        return structure;
    }

    public void setStructure(CollectivityStructureDTO structure) {
        this.structure = structure;
    }
}
