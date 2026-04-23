package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.dto.CreateMemberPaymentDTO;
import org.example.prog3_agriculturalfederation.dto.MemberPaymentDTO;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.example.prog3_agriculturalfederation.entity.MemberPayment;
import org.example.prog3_agriculturalfederation.service.MemberService;
import org.example.prog3_agriculturalfederation.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService service;
    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/members")
    public ResponseEntity<List<Member>> create(@RequestBody List<CreateMemberDTO> dtos) {
        return ResponseEntity.status(201).body(service.createMembers(dtos));
    }
}