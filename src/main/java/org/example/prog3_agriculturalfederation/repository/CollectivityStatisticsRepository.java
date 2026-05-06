package org.example.prog3_agriculturalfederation.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CollectivityStatisticsRepository {
    private final Connection connection;

    public CollectivityStatisticsRepository(Connection connection) {
        this.connection = connection;
    }

    public int countMembers(int collectivityId) throws SQLException {

        String sql = """
            SELECT COUNT(*) 
            FROM member 
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
            FROM member 
            WHERE id_collectivite = ?
            AND creation_date BETWEEN ? AND ?
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, collectivityId);
        ps.setDate(2, java.sql.Date.valueOf(from));
        ps.setDate(3, java.sql.Date.valueOf(to));

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int countUpToDateMembers(int collectivityId, LocalDate from, LocalDate to) throws SQLException {

        String sql = """
            SELECT COUNT(DISTINCT m.id_membre)
            FROM member m
            LEFT JOIN collectivity_transaction t
                ON t.member_id = m.id_membre
                AND t.creation_date BETWEEN ? AND ?
            WHERE m.id_collectivite = ?
            GROUP BY m.id_membre
            HAVING COALESCE(SUM(t.amount), 0) >= (
                SELECT COALESCE(SUM(cf.amount), 0)
                FROM membership_fee cf
                WHERE cf.id_collectivite = ?
                  AND cf.status = 'ACTIVE'
            )
        """;

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setDate(1, java.sql.Date.valueOf(from));
        ps.setDate(2, java.sql.Date.valueOf(to));
        ps.setInt(3, collectivityId);
        ps.setInt(4, collectivityId);

        ResultSet rs = ps.executeQuery();

        int count = 0;
        while (rs.next()) {
            count++;
        }

        return count;
    }
}


