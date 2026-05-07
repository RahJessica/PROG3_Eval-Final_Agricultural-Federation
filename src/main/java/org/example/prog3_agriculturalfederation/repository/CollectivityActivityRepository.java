package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.entity.CollectivityActivity;
import org.example.prog3_agriculturalfederation.entity.enums.ActivityType;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityActivityRepository {

    public List<CollectivityActivity> findByCollectivityId(String collectivityId) {

        List<CollectivityActivity> list = new ArrayList<>();

        String sql = "SELECT * FROM collectivity_activity WHERE collectivity_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(collectivityId));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CollectivityActivity activity = new CollectivityActivity();

                activity.setId(rs.getString("collectivity_id"));
                activity.setLabel(rs.getString("label"));
                activity.setActivityType(
                        ActivityType.valueOf(rs.getString("activity_type"))
                );

                list.add(activity);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching activities", e);
        }

        return list;
    }
}