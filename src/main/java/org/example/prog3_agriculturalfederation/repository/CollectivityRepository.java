package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.entity.Collectivity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CollectivityRepository {
    private final Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveAll(List<Collectivity> collectivities) {
        String sql = "INSERT INTO collectivite (id_collectivite, ville) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            for (Collectivity c : collectivities) {
                ps.setInt(1, c.getIdCollectivity());
                ps.setString(2, c.getTown());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving collectivities", e);
        }
    }

    public Collectivity findById(String id) {

        String sql = "SELECT * FROM collectivite WHERE id_collectivite = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Collectivity c = new Collectivity();
                c.setIdCollectivity(rs.getInt("id_collectivite"));
                c.setTown(rs.getString("ville"));
                return c;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void update(Collectivity c) {

        String sql = """
        UPDATE collectivite
        SET ville = ?
        WHERE id_collectivite = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, c.getTown());
            ps.setInt(2, c.getIdCollectivity());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating collectivity", e);
        }
    }

    public boolean existsByName(String name) {

        String sql = "SELECT COUNT(*) FROM collectivite WHERE nom_collectivite = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean existsByNumber(int number) {

        String sql = "SELECT COUNT(*) FROM collectivite WHERE numero = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, number);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
