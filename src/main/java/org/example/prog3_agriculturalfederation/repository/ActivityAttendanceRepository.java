package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.entity.ActivityAttendance;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ActivityAttendanceRepository {
    private final Connection connection;

    public ActivityAttendanceRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean exists(String activityId, Integer memberId) {
        String sql = """
                SELECT COUNT(*)
                FROM activity_attendance
                WHERE activity_id = ? AND member_id = ?
                """;

        try (PreparedStatement ps= connection.prepareStatement(sql)) {
            ps.setString(1, activityId);
            ps.setInt(2, memberId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1) > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(ActivityAttendance a) {
        String sql = """
            INSERT INTO activity_attendance
            (id, activity_id, member_id, status, created_at)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, a.getId());
            ps.setString(2, a.getActivityId());
            ps.setInt(3, a.getMemberId());
            ps.setObject(4,a.getStatus().name(), Types.OTHER );
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(a.getCreatAt()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
