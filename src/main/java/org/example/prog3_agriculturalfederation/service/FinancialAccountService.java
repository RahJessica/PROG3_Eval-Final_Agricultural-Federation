package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateFinancialAccountDTO;
import org.example.prog3_agriculturalfederation.entity.FinancialAccount;
import org.example.prog3_agriculturalfederation.entity.enums.AccountType;
import org.example.prog3_agriculturalfederation.repository.FinancialAccountRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class FinancialAccountService {
    private final FinancialAccountRepository repository;

    public FinancialAccountService(FinancialAccountRepository repository) {
        this.repository = repository;
    }

    public FinancialAccount createAccount (Integer collectivityId, CreateFinancialAccountDTO dto) throws SQLException{
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
        acc.setId(id);
        acc.setCollectivityId(collectivityId);
        acc.setType(type);
        acc.setBalance(dto.balance);
        acc.setHolderName(dto.holderName);
        acc.setBankCode(dto.bankCode);
        acc.setBranchCode(dto.branchCode);
        acc.setRibKey(dto.ribKey);
        acc.setAccountNumber(dto.accountNumber);
        acc.setMobileNumber(dto.mobileNumber);
        acc.setMobileService(dto.mobileService);

        repository.save(acc);
        return acc;

    }
}
