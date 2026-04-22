package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.entity.MembershipFee;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {
    public MembershipFee findById(String id) {

        String sql = "SELECT * FROM membership_fee WHERE id_fee = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MembershipFee fee = new MembershipFee();
                fee.setId(rs.getString("id_fee"));
                fee.setAmount(rs.getDouble("amount"));
                fee.setLabel(rs.getString("label"));
                fee.setFrequency(
                        Enum.valueOf(org.example.prog3_agriculturalfederation.entity.enums.Frequency.class,
                                rs.getString("frequency"))
                );
                fee.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());

                return fee;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error finding membership fee", e);
        }
    }

    public List<MembershipFee> findAllByCollectivity(String collectivityId) {

        List<MembershipFee> fees = new ArrayList<>();

        String sql = "SELECT * FROM membership_fee WHERE collectivity_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, collectivityId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MembershipFee fee = new MembershipFee();
                fee.setId(rs.getString("id_fee"));
                fee.setAmount(rs.getDouble("amount"));
                fee.setLabel(rs.getString("label"));
                fees.add(fee);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching fees", e);
        }

        return fees;
    }
}
