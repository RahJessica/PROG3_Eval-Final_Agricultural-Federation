package org.example.prog3_agriculturalfederation.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class FinancialAccountRepository {
    private final Connection connection;

    public FinancialAccountRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean existCashAccount (String collectivityId) throws SQLException {
        String sql = " select count(id_compte) from compte where id_collectivity=? and type= 'CASH'" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, collectivityId);

        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }
}
