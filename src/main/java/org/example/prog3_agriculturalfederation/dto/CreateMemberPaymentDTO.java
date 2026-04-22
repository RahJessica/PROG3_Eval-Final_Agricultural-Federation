package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;

public class CreateMemberPaymentDTO {
    private int amount;
    private String membershipFeeIdentifier;
    private String accountCreditedIdentifier;
    private PaymentMode paymentMode;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMembershipFeeIdentifier() {
        return membershipFeeIdentifier;
    }

    public void setMembershipFeeIdentifier(String membershipFeeIdentifier) {
        this.membershipFeeIdentifier = membershipFeeIdentifier;
    }

    public String getAccountCreditedIdentifier() {
        return accountCreditedIdentifier;
    }

    public void setAccountCreditedIdentifier(String accountCreditedIdentifier) {
        this.accountCreditedIdentifier = accountCreditedIdentifier;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
