package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.entity.Collectivity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CollectivityRepository {
    private final Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveAll(List<Collectivity> collectivities) {
        String sql = "INSERT INTO collectivity (id, location) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            for (Collectivity c : collectivities) {
                ps.setString(1, c.getIdCollectivity());
                ps.setString(2, c.getTown());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving collectivities", e);
        }
    }
}
