package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.AttendanceStatus;

import java.time.LocalDateTime;

public class ActivityAttendance {
    private String id;
    private String activityId;
    private Integer memberId;
    private AttendanceStatus status;
    private LocalDateTime creatAt;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
