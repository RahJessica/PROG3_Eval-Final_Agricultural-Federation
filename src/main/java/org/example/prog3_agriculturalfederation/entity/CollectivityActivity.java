package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.MemberOccupation;

import java.time.LocalDate;
import java.util.List;

public class CollectivityActivity {
    private String id;

    private String label;
    private String activityType;

    private Integer collectivityId;

    private LocalDate executiveDate;

    private Integer weekOrdinal;
    private String dayOfWeek;

    private List<MemberOccupation> memberOccupationConcerned;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(Integer collectivityId) {
        this.collectivityId = collectivityId;
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

    public List<MemberOccupation> getMemberOccupationConcerned() {
        return memberOccupationConcerned;
    }

    public void setMemberOccupationConcerned(List<MemberOccupation> memberOccupationConcerned) {
        this.memberOccupationConcerned = memberOccupationConcerned;
    }
}
