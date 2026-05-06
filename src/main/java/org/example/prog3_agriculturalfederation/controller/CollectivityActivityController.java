package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CollectivityActivityDTO;
import org.example.prog3_agriculturalfederation.service.CollectivityActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityActivityController {

    private final CollectivityActivityService service;

    public CollectivityActivityController(CollectivityActivityService service) {
        this.service = service;
    }

    @GetMapping("/{id}/activities")
    public List<CollectivityActivityDTO> getActivities(@PathVariable String id) {
        return service.getActivities(id);
    }
}