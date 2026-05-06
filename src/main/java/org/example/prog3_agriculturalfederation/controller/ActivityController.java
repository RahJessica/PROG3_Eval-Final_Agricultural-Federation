package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateCollectivityActivityDTO;
import org.example.prog3_agriculturalfederation.entity.CollectivityActivity;
import org.example.prog3_agriculturalfederation.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/{id}/activities")
    public List<CollectivityActivity> createActivities(
            @PathVariable Integer id,
            @RequestBody List<CreateCollectivityActivityDTO> dtos
    ) {
        return activityService.createActivities(id, dtos);
    }
}