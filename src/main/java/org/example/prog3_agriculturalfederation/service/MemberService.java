package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.CreateMemberDTO;
import org.example.prog3_agriculturalfederation.dto.RefereeDTO;
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

                List<Integer> refereeIds = dto.getReferees()
                        .stream()
                        .map(Integer::parseInt)
                        .toList();

                List<Member> referees = repository.findByIds(refereeIds);

                if (referees.size() != refereeIds.size()) {
                    throw new RuntimeException("Un ou plusieurs référents inexistants");
                }

                validateReferees(dto.getReferees(), refereeIds);
            }

            return repository.saveAll(dtos);
        }

        // validation de parrainage
        private void validateReferees(List<String> referees, List<Integer> memberIds) {

            if (referees == null || referees.size() < 2) {
                throw new RuntimeException("At least 2 referees required");
            }

            List<Integer> ids = referees.stream()
                    .map(Integer::parseInt)
                    .toList();

            long fromSameCollectivity = ids.stream()
                    .filter(memberIds::contains)
                    .count();

            long fromOtherCollectivities = ids.size() - fromSameCollectivity;

            if (fromSameCollectivity < fromOtherCollectivities) {
                throw new RuntimeException(
                        "At least half of referees must belong to the target collectivity"
                );
            }

            if (fromSameCollectivity < 1) {
                throw new RuntimeException(
                        "At least one referee must belong to the collectivity context"
                );
            }
        }
    }

