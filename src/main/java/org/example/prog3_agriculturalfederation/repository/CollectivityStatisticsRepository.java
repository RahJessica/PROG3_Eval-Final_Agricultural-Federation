package org.example.prog3_agriculturalfederation.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
public class CollectivityStatisticsRepository {
    private final Connection connection;

    public CollectivityStatisticsRepository(Connection connection) {
        this.connection = connection;
    }

    public int countMembers(int collectivityId) throws SQLException {

        String sql = """
            SELECT COUNT(*) 
            FROM membre 
            WHERE id_collectivite = ?
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, collectivityId);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int countNewMembers(int collectivityId, LocalDate from, LocalDate to) throws SQLException {

        String sql = """
            SELECT COUNT(*) 
            FROM membre 
            WHERE id_collectivite = ?
            AND date_adhesion BETWEEN ? AND ?
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, collectivityId);
        ps.setDate(2, java.sql.Date.valueOf(from));
        ps.setDate(3, java.sql.Date.valueOf(to));

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int getComplianceRate(int collectivityId, LocalDate from, LocalDate to) throws SQLException {

        String sql = """
             SELECT
                         CASE
                             WHEN COUNT(*) = 0 THEN 0
                             ELSE (COUNT(*) FILTER (WHERE paid >= expected) * 100.0 / COUNT(*))
                         END AS rate
                     FROM (
                         SELECT
                             m.id_membre,
                             COALESCE(SUM(t.montant), 0) AS paid,
                             COALESCE(SUM(c.montant), 0) AS expected
                         FROM membre m
                         LEFT JOIN collectivity_transaction t
                             ON t.id_membre = m.id_membre
                             AND t.date_creation BETWEEN ? AND ?
                         LEFT JOIN cotisation c
                             ON c.id_collectivite = m.id_collectivite
                             AND c.status = 'ACTIVE'
                         WHERE m.id_collectivite = ?
                         GROUP BY m.id_membre
                     ) stats
        """;

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setDate(1, java.sql.Date.valueOf(from));
        ps.setDate(2, java.sql.Date.valueOf(to));
        ps.setInt(3, collectivityId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return (int) rs.getDouble("rate");
        }

        return 0;
    }
}


