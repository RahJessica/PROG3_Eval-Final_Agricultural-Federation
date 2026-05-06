package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;

public class CreateMemberPaymentDTO {
    private int amount;
    private Integer membershipFeeIdentifier;
    private Integer accountCreditedIdentifier;
    private PaymentMode paymentMode;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Integer getMembershipFeeIdentifier() {
        return membershipFeeIdentifier;
    }

    public void setMembershipFeeIdentifier(Integer membershipFeeIdentifier) {
        this.membershipFeeIdentifier = membershipFeeIdentifier;
    }

    public Integer getAccountCreditedIdentifier() {
        return accountCreditedIdentifier;
    }

    public void setAccountCreditedIdentifier(Integer accountCreditedIdentifier) {
        this.accountCreditedIdentifier = accountCreditedIdentifier;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
