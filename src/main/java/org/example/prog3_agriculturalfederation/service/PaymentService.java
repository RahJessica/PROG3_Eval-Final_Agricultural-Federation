package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateMemberPaymentDTO;
import org.example.prog3_agriculturalfederation.entity.CollectivityTransaction;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.example.prog3_agriculturalfederation.entity.MemberPayment;
import org.example.prog3_agriculturalfederation.entity.MembershipFee;
import org.example.prog3_agriculturalfederation.entity.enums.Frequency;
import org.example.prog3_agriculturalfederation.entity.enums.PaymentMode;
import org.example.prog3_agriculturalfederation.repository.MemberRepository;
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

    public List<MemberPayment> createPayments(String memberId,
                                              List<CreateMemberPaymentDTO> dtos) {

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<MemberPayment> payments = new ArrayList<>();

        for (CreateMemberPaymentDTO dto : dtos) {

            MembershipFee fee = feeRepo.findById(dto.getMembershipFeeIdentifier())
                    .orElseThrow(() -> new RuntimeException("Fee not found"));

            if (dto.getAmount() <= 0) {
                throw new RuntimeException("Invalid amount");
            }

            MemberPayment payment = new MemberPayment();
            payment.setId(UUID.randomUUID().toString());
            payment.setAmount(dto.getAmount());
            payment.setPaymentMode(dto.getPaymentMode());
            payment.setMembershipFeeId(fee.getId());
            payment.setMemberId(memberId);
            payment.setAccountId(dto.getAccountCreditedIdentifier());
            payment.setCreationDate(LocalDate.now());

            paymentRepo.save(payment);

            CollectivityTransaction transaction = new CollectivityTransaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setAmount(dto.getAmount());
            transaction.setPaymentMode(dto.getPaymentMode());
            transaction.setCreationDate(LocalDate.now());
            transaction.setAccountId(dto.getAccountCreditedIdentifier());
            transaction.setMemberId(memberId);
            transaction.setCollectivityId(member.getCollectivityId());

            transactionRepo.save(transaction);

            handleFederationShare(fee, dto.getAmount(), member);

            payments.add(payment);
        }

        return payments;
    }

    private void handleFederationShare(MembershipFee fee, double amount, Member member) {

        if (fee.getFrequency() == Frequency.MONTHLY ||
                fee.getFrequency() == Frequency.ANNUALLY) {

            double percentage = 0.1; // 10%
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
}
