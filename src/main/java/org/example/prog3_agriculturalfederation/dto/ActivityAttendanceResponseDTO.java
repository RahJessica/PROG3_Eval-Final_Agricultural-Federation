package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.AttendanceStatus;

import java.time.LocalDateTime;

public class ActivityAttendanceResponseDTO {
    public Integer memberId;
    public Integer collectivityId;
    public AttendanceStatus status;
    public LocalDateTime markedAt;

}
