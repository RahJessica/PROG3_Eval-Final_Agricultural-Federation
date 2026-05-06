package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.MemberOccupation;

import java.time.LocalDate;
import java.util.List;

public class CreateCollectivityActivityDTO {
    private String label;
    private String activityType;

    private List<MemberOccupation> memberOccupationConcerned;

    private LocalDate executiveDate;

    private Integer weekOrdinal;
    private String dayOfWeek;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public List<MemberOccupation> getMemberOccupationConcerned() {
        return memberOccupationConcerned;
    }

    public void setMemberOccupationConcerned(List<MemberOccupation> memberOccupationConcerned) {
        this.memberOccupationConcerned = memberOccupationConcerned;
    }

    public LocalDate getExecutiveDate() {
        return executiveDate;
    }

    public void setExecutiveDate(LocalDate executiveDate) {
        this.executiveDate = executiveDate;
    }

    public Integer getWeekOrdinal() {
        return weekOrdinal;
    }

    public void setWeekOrdinal(Integer weekOrdinal) {
        this.weekOrdinal = weekOrdinal;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
