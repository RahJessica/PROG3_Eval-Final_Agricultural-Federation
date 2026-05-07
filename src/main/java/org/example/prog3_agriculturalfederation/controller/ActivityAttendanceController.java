package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateAttendanceDTO;
import org.example.prog3_agriculturalfederation.service.ActivityAttendanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collectivities/{id}/activities/{activityId}/attendance")
public class ActivityAttendanceController {

    private final ActivityAttendanceService service;

    public ActivityAttendanceController(ActivityAttendanceService service) {
        this.service = service;
    }

    @PostMapping
    public void createAttendance(
            @PathVariable String activityId,
            @RequestBody CreateAttendanceDTO dto
            ) {
        service.markAttendance(activityId, dto);
    }


}
