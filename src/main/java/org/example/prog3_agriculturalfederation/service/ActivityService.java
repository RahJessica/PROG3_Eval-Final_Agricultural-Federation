package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateCollectivityActivityDTO;
import org.example.prog3_agriculturalfederation.entity.CollectivityActivity;
import org.example.prog3_agriculturalfederation.entity.enums.ActivityType;
import org.example.prog3_agriculturalfederation.repository.ActivityRepository;
import org.example.prog3_agriculturalfederation.repository.CollectivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    private final ActivityRepository activityRepo;
    private final CollectivityRepository collectivityRepo;

    public ActivityService(ActivityRepository activityRepo,
                           CollectivityRepository collectivityRepo) {
        this.activityRepo = activityRepo;
        this.collectivityRepo = collectivityRepo;
    }

    public List<CollectivityActivity> createActivities(Integer collectivityId,
                                                       List<CreateCollectivityActivityDTO> dtos) {

        if (collectivityRepo.findById(collectivityId) == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<CollectivityActivity> activities = new ArrayList<>();

        for (CreateCollectivityActivityDTO dto : dtos) {

            if (dto.getExecutiveDate() != null && dto.getWeekOrdinal() != null) {
                throw new RuntimeException("Cannot use both executiveDate and recurrence rule");
            }

            CollectivityActivity activity = new CollectivityActivity();

            activity.setId(UUID.randomUUID().toString());
            activity.setLabel(dto.getLabel());
            activity.setActivityType(dto.getActivityType());
            activity.setCollectivityId(collectivityId);
            activity.setExecutiveDate(dto.getExecutiveDate());
            activity.setWeekOrdinal(dto.getWeekOrdinal());
            activity.setDayOfWeek(dto.getDayOfWeek());

            activity.setMemberOccupationConcerned(dto.getMemberOccupationConcerned());

            activities.add(activity);
        }

        activityRepo.saveAll(activities);

        return activities;
    }
}