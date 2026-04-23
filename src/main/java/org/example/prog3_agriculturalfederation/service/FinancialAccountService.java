package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateFinancialAccountDTO;
import org.example.prog3_agriculturalfederation.dto.FinancialAccountDTO;
import org.example.prog3_agriculturalfederation.entity.FinancialAccount;
import org.example.prog3_agriculturalfederation.entity.enums.AccountType;
import org.example.prog3_agriculturalfederation.repository.FinancialAccountRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FinancialAccountService {
    private final FinancialAccountRepository repository;

    public FinancialAccountService(FinancialAccountRepository repository) {
        this.repository = repository;
    }

    public FinancialAccount createAccount (Integer collectivityId, CreateFinancialAccountDTO dto) throws SQLException {
        AccountType type = AccountType.valueOf(dto.type);

        if(type == AccountType.CAISSE && repository.existCashAccount(String.valueOf(collectivityId))){
            throw new RuntimeException("collectivity already hahs a cash account");
        }


        if(type==AccountType.BANQUE){
            if(dto.bankCode.length() != 5 ||
                dto.branchCode.length()!= 5 ||
                dto.accountNumber.length()!=5 ||
                dto.ribKey.length() != 2) {
                throw new RuntimeException("Invalid bank account format");
            }
        }

        FinancialAccount acc = new FinancialAccount();
        acc.setId(UUID.randomUUID().toString());
        acc.setCollectivityId(collectivityId);
        acc.setType(type);
        acc.setBalance(dto.balance);
        acc.setHolderName(dto.holderName);
        acc.setBankName(dto.bankName);
        acc.setBankCode(dto.bankCode);
        acc.setBranchCode(dto.branchCode);
        acc.setRibKey(dto.ribKey);
        acc.setAccountNumber(dto.accountNumber);
        acc.setMobileNumber(dto.mobileNumber);
        acc.setMobileService(dto.mobileService);

        repository.save(acc);
        return acc;

    }

    public List<FinancialAccountDTO> getAccounts(String collectivityId, LocalDate at) {

        try {

            List<FinancialAccount> accounts =
                    repository.findByCollectivityId(collectivityId);

            if (accounts.isEmpty()) {
                throw new NoSuchElementException("Collectivity not found");
            }
            if (at==null){
                throw new IllegalArgumentException("at parameter required ");
            }
            List<FinancialAccountDTO> result = new ArrayList<>();

            for (FinancialAccount acc : accounts) {

                double transactionsSum =
                        repository.sumTransactions(acc.getId(), at);

                FinancialAccountDTO dto = new FinancialAccountDTO();
                dto.id = acc.getId();
                dto.type = acc.getType().name();

                dto.balance = acc.getBalance() + transactionsSum;

                dto.holderName = acc.getHolderName();

                result.add(dto);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}
