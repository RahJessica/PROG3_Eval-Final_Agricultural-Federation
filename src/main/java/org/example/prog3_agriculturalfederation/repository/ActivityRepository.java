package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.entity.CollectivityActivity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository
public class ActivityRepository {

    public void saveAll(List<CollectivityActivity> activities) {

        String sql = """
            INSERT INTO collectivity_activity
            (id_activity, label, activity_type, collectivity_id,
             executive_date, week_ordinal, day_of_week)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (CollectivityActivity a : activities) {

                ps.setString(1, a.getId());
                ps.setString(2, a.getLabel());
                ps.setObject(3, a.getActivityType().name(), java.sql.Types.OTHER);
                ps.setInt(4, a.getCollectivityId());

                if (a.getExecutiveDate() != null) {
                    ps.setDate(5, Date.valueOf(a.getExecutiveDate()));
                } else {
                    ps.setNull(5, java.sql.Types.DATE);
                }

                if (a.getWeekOrdinal() != null) {
                    ps.setInt(6, a.getWeekOrdinal());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }

                ps.setString(7, a.getDayOfWeek());

                ps.addBatch();
            }

            ps.executeBatch();

        } catch (Exception e) {
            throw new RuntimeException("Error saving activities", e);
        }
    }
}