package org.example.prog3_agriculturalfederation.repository;

import org.example.prog3_agriculturalfederation.entity.FinancialAccount;
import org.example.prog3_agriculturalfederation.entity.enums.AccountType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public void save(FinancialAccount acc) throws SQLException {
        String sql = """
                Insert into compte (id_compte, id_collectivity, type, balance, 
                holder_name, bank_name, bank_code, branch_code, account_number, 
                rib_key, mobile_service, mobile_number) values(?,?,?,?,?,?,?,?,?,?,?,?)""";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, acc.getId());
        preparedStatement.setInt(2, acc.getCollectivityId());
        preparedStatement.setString(3, acc.getType().name());
        preparedStatement.setDouble(4, acc.getBalance());
        preparedStatement.setString(5, acc.getHolderName());
        preparedStatement.setString(6, acc.getBankName());
        preparedStatement.setString(7, acc.getBankCode());
        preparedStatement.setString(8, acc.getBranchCode());
        preparedStatement.setString(9, acc.getAccountNumber());
        preparedStatement.setString(10, acc.getRibKey());
        preparedStatement.setString(11, acc.getMobileService());
        preparedStatement.setString(12, acc.getMobileNumber());

        preparedStatement.execute();
    }

    public List<FinancialAccount> findByCollectivityId(String collectivityId) throws SQLException {

        String sql = "SELECT * FROM financial_account WHERE collectivity_id = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, collectivityId);

        ResultSet rs = stmt.executeQuery();

        List<FinancialAccount> accounts = new ArrayList<>();

        while (rs.next()) {
            FinancialAccount acc = new FinancialAccount();
            acc.setId(rs.getString("id"));
            acc.setType(AccountType.valueOf(rs.getString("type")));
            acc.setBalance(rs.getDouble("balance"));
            acc.setHolderName(rs.getString("holder_name"));

            accounts.add(acc);
        }

        return accounts;
    }

    public double sumTransactions(String accountId, LocalDate at) throws SQLException {

        String sql = """
            SELECT COALESCE(SUM(amount), 0) as total
            FROM collectivity_transaction
            WHERE account_id = ? AND creation_date <= ?
        """;

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, accountId);
        stmt.setDate(2, Date.valueOf(at));

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getDouble("total");
        }

        return 0;
    }
}
