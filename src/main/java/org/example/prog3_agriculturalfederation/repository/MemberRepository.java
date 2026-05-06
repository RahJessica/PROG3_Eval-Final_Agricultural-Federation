package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {

    public boolean collectiviteExists(int id) {
        String sql = "SELECT COUNT(id_collectivite) FROM collectivite WHERE id_collectivite = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1) > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public List<Member> saveAll(List<CreateMemberDTO> dtos) {

        String sql = """
            INSERT INTO membre(
                telephone, email, date_adhesion,
                nom_membre, prenom_membre,
                date_naissance, genre, adresse,
                metier, id_collectivite
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        List<Member> members = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                for (CreateMemberDTO dto : dtos) {

                    ps.setString(1, dto.phoneNumber);
                    ps.setString(2, dto.email);
                    ps.setDate(3, dto.dateAdhesion != null ? Date.valueOf(dto.dateAdhesion) : null);
                    ps.setString(4, dto.lastName);
                    ps.setString(5, dto.firstName);
                    ps.setDate(6, dto.birthDate != null ? Date.valueOf(dto.birthDate) : null);
                    ps.setString(7, dto.gender);
                    ps.setString(8, dto.address);
                    ps.setString(9, dto.profession);
                    ps.setInt(10, dto.idCollectivity);

                    ps.addBatch();
                }

                ps.executeBatch();

                ResultSet rs = ps.getGeneratedKeys();
                int i = 0;

                while (rs.next()) {
                    Member m = new Member();
                    m.setId(rs.getInt(1));
                    m.setFirstName(dtos.get(i).firstName);
                    m.setLastName(dtos.get(i).lastName);
                    m.setEmail(dtos.get(i).email);
                    members.add(m);
                    i++;
                }

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return members;
    }

    public List<Member> findByIds(List<Integer> ids) {

        List<Member> members = new ArrayList<>();

        if (ids == null || ids.isEmpty()) {
            return members;
        }

        String sql = "SELECT id_membre, prenom_membre, nom_membre, email, telephone FROM membre WHERE id_membre = ANY (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            Array array = conn.createArrayOf("int4", ids.toArray());
            ps.setArray(1, array);

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
            throw new RuntimeException("Error finding members by ids", e);
        }

        return members;
    }

    public Member findById(Integer id) {

        String sql = "SELECT id_membre, prenom_membre, nom_membre, email, telephone FROM membre WHERE id_member = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id_membre"));
                m.setFirstName(rs.getString("prenom_membre"));
                m.setLastName(rs.getString("nom_membre"));
                m.setEmail(rs.getString("email"));
                m.setPhoneNumber(rs.getString("telephone"));
                return m;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error finding member by id", e);
        }
    }

    public List<Member> findByCollectivityId(Integer collectivityId) {

        List<Member> members = new ArrayList<>();

        String sql = "SELECT * FROM membre WHERE id_collectivite = ?";

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
}