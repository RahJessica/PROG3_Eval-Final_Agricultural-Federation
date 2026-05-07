package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateAttendanceDTO;
import org.example.prog3_agriculturalfederation.entity.ActivityAttendance;
import org.example.prog3_agriculturalfederation.repository.ActivityAttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivityAttendanceService {
    private final ActivityAttendanceRepository activityAttendanceRepository;

    public ActivityAttendanceService(ActivityAttendanceRepository activityAttendanceRepository) {
        this.activityAttendanceRepository = activityAttendanceRepository;
    }

    public void markAttendance(String activityId, CreateAttendanceDTO dto) {
        if(activityAttendanceRepository.exists(activityId, dto.memberId)) {
            throw new RuntimeException("Attendance already recorded");
        }

        ActivityAttendance a = new ActivityAttendance();
        a.setId(UUID.randomUUID().toString());
        a.setActivityId(activityId);
        a.setMemberId(dto.memberId);
        a.setStatus(dto.status);
        a.setCreatAt(java.time.LocalDateTime.now());
        activityAttendanceRepository.save(a);
    }

}
