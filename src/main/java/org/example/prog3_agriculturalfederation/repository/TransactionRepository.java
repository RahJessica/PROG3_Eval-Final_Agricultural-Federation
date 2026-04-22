package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;
import org.example.prog3_agriculturalfederation.entity.CollectivityTransaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    public void save(CollectivityTransaction tx) {

        String sql = """
            INSERT INTO collectivity_transaction
            (id_transaction, amount, payment_mode, creation_date,
             account_id, member_id, collectivity_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tx.getId());
            ps.setDouble(2, tx.getAmount());
            ps.setString(3, tx.getPaymentMode().name());
            ps.setDate(4, Date.valueOf(tx.getCreationDate()));
            ps.setString(5, tx.getAccountId());
            ps.setString(6, tx.getMemberId());
            ps.setString(7, tx.getCollectivityId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving transaction", e);
        }
    }

    public List<CollectivityTransaction> findBetweenDates(String collectivityId,
                                                          LocalDate from,
                                                          LocalDate to) {

        List<CollectivityTransaction> list = new ArrayList<>();

        String sql = """
            SELECT * FROM collectivity_transaction
            WHERE collectivity_id = ?
            AND creation_date BETWEEN ? AND ?
            ORDER BY creation_date
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, collectivityId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CollectivityTransaction tx = new CollectivityTransaction();
                tx.setId(rs.getString("id_transaction"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setPaymentMode(
                        Enum.valueOf(org.example.prog3_agriculturalfederation.entity.enums.PaymentMode.class,
                                rs.getString("payment_mode"))
                );
                tx.setCreationDate(rs.getDate("creation_date").toLocalDate());
                tx.setCollectivityId(rs.getString("collectivity_id"));

                list.add(tx);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching transactions", e);
        }

        return list;
    }
}
