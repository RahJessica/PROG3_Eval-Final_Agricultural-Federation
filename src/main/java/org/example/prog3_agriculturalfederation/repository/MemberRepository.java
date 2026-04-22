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
        String sql = "SELECT COUNT(*) FROM collectivite WHERE id_collectivite = ?";

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
            INSERT INTO member(
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
                conn.rollback(); //
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return members;
    }
}