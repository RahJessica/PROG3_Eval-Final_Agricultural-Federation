package org.example.prog3_agriculturalfederation.service;

import jdk.dynalink.linker.LinkerServices;
import org.example.prog3_agriculturalfederation.dto.ActivityAttendanceResponseDTO;
import org.example.prog3_agriculturalfederation.dto.CreateAttendanceDTO;
import org.example.prog3_agriculturalfederation.entity.ActivityAttendance;
import org.example.prog3_agriculturalfederation.repository.ActivityAttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityAttendanceService {
    private final ActivityAttendanceRepository activityAttendanceRepository;

    public ActivityAttendanceService(ActivityAttendanceRepository activityAttendanceRepository) {
        this.activityAttendanceRepository = activityAttendanceRepository;
    }

    public ActivityAttendance markAttendance(String activityId, CreateAttendanceDTO dto) {
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
        return a;
    }

    public List<ActivityAttendanceResponseDTO> getAttendanceByActivity (String collectivityId, String activityId) {
        List<ActivityAttendance> list = activityAttendanceRepository.findByActivityId(activityId);

        return list.stream().map(a -> {
            ActivityAttendanceResponseDTO dto = new ActivityAttendanceResponseDTO();
            dto.memberId= a.getMemberId();
            dto.status = a.getStatus();
            dto.markedAt= a.getCreatAt();
            dto.collectivityId = collectivityId;
            return dto;
        }).toList();
    }

}
