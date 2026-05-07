package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.dto.CollectivityTransactionDTO;
import org.example.prog3_agriculturalfederation.entity.MembershipFee;
import org.example.prog3_agriculturalfederation.entity.enums.Frequency;
import org.example.prog3_agriculturalfederation.entity.enums.Status;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {
    public MembershipFee findById(Integer id) {

        String sql = "SELECT * FROM cotisation WHERE id_cotisation = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MembershipFee fee = new MembershipFee();
                fee.setId(rs.getString("id_cotisation"));
                fee.setAmount(rs.getDouble("montant"));
                fee.setFrequency(
                        Frequency.valueOf(rs.getString("frequency"))
                );
                fee.setEligibleFrom(
                        rs.getDate("eligible").toLocalDate()
                );
                fee.setStatus(
                        Status.valueOf(rs.getString("status"))
                );

                return fee;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error finding membership fee", e);
        }
    }

    public List<MembershipFee> findAllByCollectivity(Integer collectivityId) {

        List<MembershipFee> fees = new ArrayList<>();

        String sql = "SELECT * FROM cotisation WHERE id_collectivite = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, collectivityId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MembershipFee fee = new MembershipFee();
                fee.setId(rs.getString("id_cotisation"));
                fee.setAmount(rs.getDouble("montant"));
                fee.setStatus(Status.valueOf(rs.getString("status")));
                String freq = rs.getString("frequency");
                if (freq == null) {
                    throw new RuntimeException("Frequency is null for fee id " + rs.getString("id_cotisation"));
                }
                fee.setFrequency(Frequency.valueOf(freq));
                if (rs.getDate("eligible") != null) {
                    fee.setEligibleFrom(rs.getDate("eligible").toLocalDate());
                }
                fee.setStatus(Status.valueOf(rs.getString("status")));
                fees.add(fee);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching fees", e);
        }

        return fees;
    }

    public void saveAll(List<MembershipFee> fees) {

        String sql = """
        INSERT INTO cotisation (
            montant,
            frequency,
            eligible,
            status,
            id_collectivite
        ) VALUES (?, ?, ?, ?, ?)
    """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (MembershipFee fee : fees) {

                ps.setDouble(1, fee.getAmount());
                if (fee.getFrequency() == null) {
                    throw new RuntimeException("Frequency is null for fee before insert");
                }
                ps.setObject(2, fee.getFrequency().name(), Types.OTHER);
                ps.setDate(3, Date.valueOf(fee.getEligibleFrom()));
                ps.setObject(4, fee.getStatus().name(), Types.OTHER);
                ps.setInt(5, fee.getCollectivityId());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (Exception e) {
            throw new RuntimeException("Error saving membership fees", e);
        }
    }

    public List<MembershipFee> findActiveByCollectivity(Integer collectivityId) {

        List<MembershipFee> fees = new ArrayList<>();

        String sql = "SELECT * FROM cotisation WHERE id_collectivite = ? AND status = 'ACTIVE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, collectivityId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MembershipFee fee = new MembershipFee();

                fee.setId(rs.getString("id_cotisation"));
                fee.setAmount(rs.getDouble("montant"));

                String freq = rs.getString("frequency");
                if (freq != null) {
                    fee.setFrequency(Frequency.valueOf(freq));
                }

                if (rs.getDate("eligible") != null) {
                    fee.setEligibleFrom(rs.getDate("eligible").toLocalDate());
                }

                fee.setCollectivityId(rs.getInt("id_collectivite"));
                fee.setStatus(Status.valueOf(rs.getString("status")));

                fees.add(fee);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching active fees", e);
        }

        return fees;
    }
}
