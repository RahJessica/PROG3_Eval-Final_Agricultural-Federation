package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;

import java.time.LocalDate;

public class MemberPayment {
    private Integer id;
    private int amount;
    private PaymentMode paymentMode;
    private String membershipFeeId;
    private Integer accountId;
    private Integer memberId;
    private LocalDate creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getMembershipFeeId() {
        return membershipFeeId;
    }

    public void setMembershipFeeId(String membershipFeeId) {
        this.membershipFeeId = membershipFeeId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
