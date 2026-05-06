package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.entity.MemberPayment;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PaymentRepository {
    private final DatabaseConnection databaseConnection;

    public PaymentRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void save(MemberPayment payment) {

        String sql = """
        INSERT INTO member_payment(
            id_payment,
            amount,
            payment_mode,
            membership_fee_id,
            member_id,
            account_id,
            creation_date
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMode().name());
            ps.setString(4, payment.getMembershipFeeId());
            ps.setInt(5, payment.getMemberId());
            ps.setInt(6, payment.getAccountId());
            ps.setDate(7, Date.valueOf(payment.getCreationDate()));

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving payment", e);
        }
    }
}
