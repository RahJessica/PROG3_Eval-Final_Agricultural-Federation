package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.*;
import org.example.prog3_agriculturalfederation.entity.*;
import org.example.prog3_agriculturalfederation.repository.CollectivityRepository;
import org.example.prog3_agriculturalfederation.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectivityService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;

    public CollectivityService(MemberRepository memberRepository,
                               CollectivityRepository collectivityRepository) {
        this.memberRepository = memberRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public List<CollectivityDTO> createCollectivities(List<CreateCollectivityDTO> requests) {

        List<Collectivity> collectivitiesToSave = new ArrayList<>();

        for (CreateCollectivityDTO req : requests) {

            if (!Boolean.TRUE.equals(req.getFederationApproval())) {
                throw new RuntimeException("Collectivity must be approved by federation");
            }

            List<Member> members = memberRepository.findByIds(req.getMembers());

            if (members == null || members.isEmpty()) {
                throw new RuntimeException("Members not found");
            }

            validateReferees(req.getReferees(), req.getMembers());

            Collectivity collectivity = new Collectivity();
            collectivity.setTown(req.getTown());
            collectivity.setCreationDate(java.time.LocalDate.now());
            collectivity.setAutorisation(true);

            collectivitiesToSave.add(collectivity);
        }

        collectivityRepository.saveAll(collectivitiesToSave);

        return collectivitiesToSave.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private void validateReferees(List<RefereeDTO> referees, List<Integer> memberIds) {

        if (referees == null || referees.size() < 2) {
            throw new RuntimeException("At least 2 referees required");
        }

        long fromSameCollectivity = referees.stream()
                .filter(r -> memberIds.contains(r.getMemberId()))
                .count();

        if (fromSameCollectivity < 1) {
            throw new RuntimeException("At least one referee must belong to the collectivity context");
        }
    }

    private CollectivityDTO toDTO(Collectivity c) {

        CollectivityDTO dto = new CollectivityDTO();
        dto.setId(c.getIdCollectivity());
        dto.setLocation(c.getTown());

        dto.setMembers(new ArrayList<>());
        dto.setStructure(null);

        return dto;
    }
}