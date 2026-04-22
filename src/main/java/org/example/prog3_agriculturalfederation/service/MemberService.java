package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.entity.Member;
import org.example.prog3_agriculturalfederation.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

        private final MemberRepository repository;

        public MemberService(MemberRepository repository) {
            this.repository = repository;
        }

        public List<Member> createMembers(List<CreateMemberDTO> dtos) {

            if (dtos == null || dtos.isEmpty()) {
                throw new RuntimeException("Liste vide");
            }

            for (CreateMemberDTO dto : dtos) {

                if (dto.firstName == null || dto.lastName == null) {
                    throw new RuntimeException("Nom et prénom obligatoires");
                }

                if (dto.idCollectivity == null || !repository.collectiviteExists(dto.idCollectivity)) {
                    throw new RuntimeException("Collectivité inexistante");
                }

                if (!dto.registrationFeePaid || !dto.membershipDuesPaid) {
                    throw new RuntimeException("Cotisation ou frais d'inscription non payés");
                }
            }

            return repository.saveAll(dtos);
        }
    }

