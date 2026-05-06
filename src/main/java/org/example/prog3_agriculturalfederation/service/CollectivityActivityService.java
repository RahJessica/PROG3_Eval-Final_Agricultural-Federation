package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CollectivityActivityDTO;
import org.example.prog3_agriculturalfederation.entity.CollectivityActivity;
import org.example.prog3_agriculturalfederation.repository.CollectivityActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectivityActivityService {

    private final CollectivityActivityRepository repo;

    public CollectivityActivityService(CollectivityActivityRepository repo) {
        this.repo = repo;
    }

    public List<CollectivityActivityDTO> getActivities(String collectivityId) {

        List<CollectivityActivity> activities = repo.findByCollectivityId(collectivityId);

        List<CollectivityActivityDTO> result = new ArrayList<>();

        for (CollectivityActivity a : activities) {

            CollectivityActivityDTO dto = new CollectivityActivityDTO();

            dto.setId(a.getId());
            dto.setLabel(a.getLabel());
            dto.setActivityType(a.getActivityType());
            dto.setMemberOccupationConcerned(a.getMemberOccupationConcerned());
            dto.setExecutiveDate(a.getExecutiveDate());

            result.add(dto);
        }

        return result;
    }
}