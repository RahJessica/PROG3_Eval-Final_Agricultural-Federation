package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.example.prog3_agriculturalfederation.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody CreateMemberDTO dto) {
        return ResponseEntity.status(201).body(service.createMember(dto));
    }
}