package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.entity.Collectivity;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityRepository {
    private final Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveAll(List<Collectivity> collectivities) {
        String sql = "INSERT INTO collectivite (nom_collectivite, ville, specialite, creation_date, autorisation, numero)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            for (Collectivity c : collectivities) {
                ps.setString(1, c.getNameCollectivity());
                ps.setString(2, c.getTown());
                ps.setString(3, c.getSpeciality());
                ps.setDate(4, Date.valueOf(c.getCreationDate()));
                ps.setBoolean(5, c.getAutorisation());
                ps.setString(6, c.getNumero());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving collectivities", e);
        }
    }

    public Collectivity findById(Integer id) {

        String sql = "SELECT id_collectivite, ville FROM collectivite WHERE id_collectivite = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
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
                SET nom_collectivite = ?,
                    numero = ?,
                    ville = ?
                WHERE id_collectivite = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, c.getNameCollectivity());
            ps.setString(2, c.getNumero());
            ps.setString(3, c.getTown());
            ps.setInt(4, c.getIdCollectivity());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating collectivity", e);
        }
    }

    public boolean existsByName(String name) {

        String sql = "SELECT COUNT(id_collectivite) FROM collectivite WHERE nom_collectivite = ?";

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

        String sql = "SELECT COUNT(id_collectivite) FROM collectivite WHERE numero = ?";

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

    public List<Member> findByCollectivityId(int collectivityId) {

        List<Member> members = new ArrayList<>();

        String sql = "SELECT * FROM member WHERE id_collectivite = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, collectivityId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id_membre"));
                m.setFirstName(rs.getString("prenom_membre"));
                m.setLastName(rs.getString("nom_membre"));
                m.setEmail(rs.getString("email"));
                m.setPhoneNumber(rs.getString("telephone"));

                members.add(m);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching members by collectivity", e);
        }

        return members;
    }

    public List<Collectivity> findAll() {

        List<Collectivity> list = new ArrayList<>();

        String sql = "SELECT * FROM collectivite";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Collectivity c = new Collectivity();
                c.setIdCollectivity(rs.getInt("id_collectivite"));
                c.setNameCollectivity(rs.getString("nom_collectivite"));
                c.setTown(rs.getString("ville"));
                c.setSpeciality(rs.getString("specialite"));
                c.setCreationDate(rs.getDate("creation_date").toLocalDate());
                c.setAutorisation(rs.getBoolean("autorisation"));
                c.setNumero(rs.getString("numero"));

                list.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching collectivities", e);
        }

        return list;
    }
}
