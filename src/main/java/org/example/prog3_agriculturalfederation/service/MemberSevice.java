package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.example.prog3_agriculturalfederation.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member createMember(CreateMemberDTO dto) {

        if (dto.lastName == null || dto.firstName == null) {
            throw new RuntimeException("Nom et prénom obligatoires");
        }

        if (dto.idCollectivity == null || !repository.collectiviteExists(dto.idCollectivity)) {
            throw new RuntimeException("Collectivité inexistante");
        }

        return repository.save(dto);
    }
}