package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateMemberPaymentDTO;
import org.example.prog3_agriculturalfederation.dto.MemberPaymentDTO;
import org.example.prog3_agriculturalfederation.entity.CollectivityTransaction;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.example.prog3_agriculturalfederation.entity.MemberPayment;
import org.example.prog3_agriculturalfederation.entity.MembershipFee;
import org.example.prog3_agriculturalfederation.entity.enums.Frequency;
import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;
import org.example.prog3_agriculturalfederation.repository.MemberRepository;
import org.example.prog3_agriculturalfederation.repository.MembershipFeeRepository;
import org.example.prog3_agriculturalfederation.repository.PaymentRepository;
import org.example.prog3_agriculturalfederation.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private final MemberRepository memberRepo;
    private final MembershipFeeRepository feeRepo;
    private final PaymentRepository paymentRepo;
    private final TransactionRepository transactionRepo;

    public PaymentService(MemberRepository memberRepo, MembershipFeeRepository feeRepo, PaymentRepository paymentRepo, TransactionRepository transactionRepo) {
        this.memberRepo = memberRepo;
        this.feeRepo = feeRepo;
        this.paymentRepo = paymentRepo;
        this.transactionRepo = transactionRepo;
    }

    public List<MemberPaymentDTO> createPayments(Integer memberId,
                                                 List<CreateMemberPaymentDTO> dtos) {

        Member member = memberRepo.findById(memberId);
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        List<MemberPayment> payments = new ArrayList<>();

        for (CreateMemberPaymentDTO dto : dtos) {

            MembershipFee fee = feeRepo.findById(dto.getMembershipFeeIdentifier());
            if (fee == null) {
                throw new RuntimeException("Fee not found");
            }

            if (dto.getAmount() <= 0) {
                throw new RuntimeException("Invalid amount");
            }

            MemberPayment payment = new MemberPayment();
            payment.setId(UUID.randomUUID().toString());
            payment.setAmount(dto.getAmount());
            payment.setPaymentMode(dto.getPaymentMode());
            payment.setMembershipFeeId(fee.getId());
            payment.setMemberId(String.valueOf(memberId));
            payment.setAccountId(dto.getAccountCreditedIdentifier());
            payment.setCreationDate(LocalDate.now());

            paymentRepo.save(payment);

            CollectivityTransaction transaction = new CollectivityTransaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setAmount(dto.getAmount());
            transaction.setPaymentMode(dto.getPaymentMode());
            transaction.setCreationDate(LocalDate.now());
            transaction.setAccountId(dto.getAccountCreditedIdentifier());
            transaction.setMemberId(String.valueOf(memberId));

            transactionRepo.save(transaction);

            handleFederationShare(fee, dto.getAmount(), member);

            payments.add(payment);
        }

        List<MemberPaymentDTO> result = new ArrayList<>();

        for (MemberPayment payment : payments) {
            result.add(toDTO(payment));
        }

        return result;
    }

    private void handleFederationShare(MembershipFee fee, double amount, Member member) {

        if (fee.getFrequency() == Frequency.MONTHLY ||
                fee.getFrequency() == Frequency.ANNUALLY) {

            double percentage = 0.1; // 10% reversés
            double federationAmount = amount * percentage;

            CollectivityTransaction federationTx = new CollectivityTransaction();

            federationTx.setId(UUID.randomUUID().toString());
            federationTx.setAmount(federationAmount);
            federationTx.setPaymentMode(PaymentMode.BANK_TRANSFER);
            federationTx.setCreationDate(LocalDate.now());

            federationTx.setCollectivityId("FEDERATION");

            transactionRepo.save(federationTx);
        }
    }

    private MemberPaymentDTO toDTO(MemberPayment payment) {

        MemberPaymentDTO dto = new MemberPaymentDTO();

        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setCreationDate(payment.getCreationDate());

        return dto;
    }
}
