package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.MobileBankingService;

public class MobileBankingAccountDTO {
    private String holderName;
    private MobileBankingService mobileBankingService;
    private Integer mobileNumber;
    private Double amount;

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public MobileBankingService getMobileBankingService() {
        return mobileBankingService;
    }

    public void setMobileBankingService(MobileBankingService mobileBankingService) {
        this.mobileBankingService = mobileBankingService;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
