package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository {


    public boolean collectiviteExists(int id) {
        String sql = "SELECT COUNT(*) FROM collectivite WHERE id_collectivite = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public Member save(CreateMemberDTO dto) {
        String sql = """
            INSERT INTO member(
                telephone, email, date_adhesion,
                nom_membre, prenom_membre,
                date_naissance, genre, adresse,
                metier, id_collectivite
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dto.phoneNumber);
            ps.setString(2, dto.email);

            ps.setDate(3, dto.dateAdhesion!= null ? Date.valueOf(dto.dateAdhesion) : null);

            ps.setString(4, dto.lastName);
            ps.setString(5, dto.firstName);

            ps.setDate(6, dto.birthDate != null ? Date.valueOf(dto.birthDate) : null);

            ps.setString(7, dto.gender);
            ps.setString(8, dto.address);
            ps.setString(9, dto.profession);
            ps.setInt(10, dto.idCollectivity);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString(1));
                return m;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    }

