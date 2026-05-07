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
                                        (montant, mode_paiement, date_creation,
                                         id_account, id_membre, id_collectivite)
                                        VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, tx.getAmount());
            ps.setString(2, tx.getPaymentMode().name());
            ps.setDate(3, Date.valueOf(tx.getCreationDate()));
            ps.setInt(4, tx.getAccountId());
            ps.setString(5, tx.getMemberId());
            ps.setInt(6, tx.getCollectivityId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving transaction", e);
        }
    }

    public List<CollectivityTransaction> findBetweenDates(Integer collectivityId,
                                                          LocalDate from,
                                                          LocalDate to) {

        List<CollectivityTransaction> list = new ArrayList<>();

        String sql = """
            SELECT * FROM collectivity_transaction
            WHERE id_collectivite = ?
            AND date_creation BETWEEN ? AND ?
            ORDER BY date_creation
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, collectivityId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CollectivityTransaction tx = new CollectivityTransaction();
                tx.setId(rs.getString("id_transaction"));
                tx.setAmount(rs.getDouble("montant"));
                tx.setPaymentMode(
                        Enum.valueOf(org.example.prog3_agriculturalfederation.entity.enums.PaymentMode.class,
                                rs.getString("mode_paiement"))
                );
                tx.setCreationDate(rs.getDate("date_creation").toLocalDate());
                tx.setCollectivityId(rs.getInt("id_collectivite"));
                tx.setMemberId(rs.getString("id_membre"));

                list.add(tx);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching transactions", e);
        }

        return list;
    }
}
