package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.ActivityAttendanceResponseDTO;
import org.example.prog3_agriculturalfederation.dto.CreateAttendanceDTO;
import org.example.prog3_agriculturalfederation.entity.ActivityAttendance;
import org.example.prog3_agriculturalfederation.service.ActivityAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities/{id}/activities/{activityId}/attendance")
public class ActivityAttendanceController {

    private final ActivityAttendanceService service;

    public ActivityAttendanceController(ActivityAttendanceService service) {
        this.service = service;
    }

    @PostMapping
    public ActivityAttendance createAttendance(
            @PathVariable String activityId,
            @RequestBody CreateAttendanceDTO dto
            ) {
        return service.markAttendance(activityId, dto);
    }

    @GetMapping
    public ResponseEntity<List<ActivityAttendanceResponseDTO>> getAttendance (
            @PathVariable("id") String collectivityId,
            @PathVariable String activityId
    ){
        List<ActivityAttendanceResponseDTO> result = service.getAttendanceByActivity(collectivityId, activityId);
        return ResponseEntity.ok(result);
    }


}
