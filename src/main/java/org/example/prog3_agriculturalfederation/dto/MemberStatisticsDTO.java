package org.example.prog3_agriculturalfederation.dto;

public class MemberStatisticsDTO {private Integer memberId;
    private String name;
    private Double totalPaid;
    private Double totalUnpaid;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Double getTotalUnpaid() {
        return totalUnpaid;
    }

    public void setTotalUnpaid(Double totalUnpaid) {
        this.totalUnpaid = totalUnpaid;
    }
}
