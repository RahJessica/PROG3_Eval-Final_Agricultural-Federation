package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;

public class MemberController {
    @PostMapping("/members")
    public ResponseEntity<Member> create(@RequestBody CreateMemberDTO dto) {
        return ResponseEntity.status(201).body(service.createMember(dto));
    }
}
