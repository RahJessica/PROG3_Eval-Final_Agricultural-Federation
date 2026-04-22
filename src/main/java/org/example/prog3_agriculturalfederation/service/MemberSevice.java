package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;

public class MemberSevice {
    public Member createMember(CreateMemberDTO dto) {

        if (dto.lastName== null || dto.firstName == null) {
            throw new RuntimeException("Nom et prénom obligatoires");
        }

        if (!repository.collectiviteExists(dto.idCollectivity)) {
            throw new RuntimeException("Collectivité inexistante");
        }

        return repository.save(dto);
    }
}
