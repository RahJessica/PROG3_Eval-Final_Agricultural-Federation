package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.Frequency;
import org.example.prog3_agriculturalfederation.entity.enums.Status;

import java.time.LocalDate;

public class MembershipFee {
    private String id;
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private double amount;
    private String label;
    private Integer collectivityId;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getEligibleFrom() {
        return eligibleFrom;
    }

    public void setEligibleFrom(LocalDate eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(Integer collectivityId) {
        this.collectivityId = collectivityId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
