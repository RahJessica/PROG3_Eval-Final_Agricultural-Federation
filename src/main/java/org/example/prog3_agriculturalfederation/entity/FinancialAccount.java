package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.AccountType;

public class FinancialAccount {
    private Integer id;
    private Integer collectivityId;
    private AccountType type;
    private double balance;

    // BANK
    private String holderName;
    private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String ribKey;

    // MOBILE
    private String mobileService;
    private String mobileNumber;

    public FinancialAccount() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(Integer collectivityId) {
        this.collectivityId = collectivityId;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileService() {
        return mobileService;
    }

    public void setMobileService(String mobileService) {
        this.mobileService = mobileService;
    }

    public String getRibKey() {
        return ribKey;
    }

    public void setRibKey(String ribKey) {
        this.ribKey = ribKey;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public FinancialAccount(String accountNumber, double balance, String bankCode, String bankName, String branchCode, Integer collectivityId, String holderName, int id, String mobileNumber, String mobileService, String ribKey, AccountType type) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.collectivityId = collectivityId;
        this.holderName = holderName;
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.mobileService = mobileService;
        this.ribKey = ribKey;
        this.type = type;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "FinancialAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", id='" + id + '\'' +
                ", collectivityId='" + collectivityId + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                ", holderName='" + holderName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", ribKey='" + ribKey + '\'' +
                ", mobileService='" + mobileService + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}