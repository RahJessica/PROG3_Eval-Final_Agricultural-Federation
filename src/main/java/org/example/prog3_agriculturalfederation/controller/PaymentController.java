package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateMemberPaymentDTO;
import org.example.prog3_agriculturalfederation.dto.MemberPaymentDTO;
import org.example.prog3_agriculturalfederation.entity.MemberPayment;
import org.example.prog3_agriculturalfederation.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<List<MemberPaymentDTO>> createPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentDTO> dtos) {

        return ResponseEntity.status(201)
                .body(service.createPayments(id, dtos));
    }
}