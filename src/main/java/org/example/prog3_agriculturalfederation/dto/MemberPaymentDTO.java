package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;

import java.time.LocalDate;

public class MemberPaymentDTO {
    private Integer id;
    private Integer amount;
    private PaymentMode paymentMode;
    private FinancialAccountDTO accountCredited;
    private LocalDate creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public FinancialAccountDTO getAccountCredited() {
        return accountCredited;
    }

    public void setAccountCredited(FinancialAccountDTO accountCredited) {
        this.accountCredited = accountCredited;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
