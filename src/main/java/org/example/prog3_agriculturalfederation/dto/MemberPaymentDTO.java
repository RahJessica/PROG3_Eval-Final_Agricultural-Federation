package org.example.prog3_agriculturalfederation.dto;

import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;

import java.time.LocalDate;

public class MemberPaymentDTO {
    private String id;
    private Integer amount;
    private PaymentMode paymentMode;
    private FinancialAccountDTO accountCredited;
    private LocalDate creationDate;
}
