package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.*;
import org.example.prog3_agriculturalfederation.entity.*;
import org.example.prog3_agriculturalfederation.repository.CollectivityRepository;
import org.example.prog3_agriculturalfederation.repository.MemberRepository;
import org.example.prog3_agriculturalfederation.repository.MembershipFeeRepository;
import org.example.prog3_agriculturalfederation.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectivityService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;
    private final MembershipFeeRepository feeRepository;
    private final TransactionRepository transactionRepository;

    public CollectivityService(MemberRepository memberRepository,
                               CollectivityRepository collectivityRepository,
                               MembershipFeeRepository feeRepository,
                               TransactionRepository transactionRepository) {
        this.memberRepository = memberRepository;
        this.collectivityRepository = collectivityRepository;
        this.feeRepository = feeRepository;
        this.transactionRepository = transactionRepository;
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

            Collectivity collectivity = new Collectivity();
            collectivity.setTown(req.getTown());
            collectivity.setCreationDate(java.time.LocalDate.now());
            collectivity.setAutorisation(true);

            collectivitiesToSave.add(collectivity);
        }

        collectivityRepository.saveAll(collectivitiesToSave); // saveAll dans le repo pour les requetes

        return collectivitiesToSave.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Entity (base) -> DTO (réponse API)
    private CollectivityDTO toDTO(Collectivity c) {

        CollectivityDTO dto = new CollectivityDTO();
        dto.setId(c.getIdCollectivity());
        dto.setLocation(c.getTown());

        dto.setMembers(new ArrayList<>());
        dto.setStructure(null);

        return dto;
    }

    public CollectivityDTO updateCollectivityInformation(String id,
                                                         CollectivityInformationDTO request) {

        Collectivity collectivity = collectivityRepository.findById(id);

        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        if (collectivityRepository.existsByNumber(request.getNumber())) {
            throw new RuntimeException("Number already used");
        }

        if (collectivityRepository.existsByName(request.getName())) {
            throw new RuntimeException("Name already used");
        }

        collectivity.setNameCollectivity(request.getName());
        collectivityRepository.update(collectivity);

        return toDTO(collectivity);
    }

    public List<MembershipFeeDTO> createMembershipFees(String collectivityId,
                                                       List<CreateMembershipFeeDTO> request) {

        Collectivity collectivity = collectivityRepository.findById(collectivityId);

        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<MembershipFee> fees = new ArrayList<>();

        for (CreateMembershipFeeDTO dto : request) {

            if (dto.getAmount() < 0) {
                throw new RuntimeException("Amount must be positive");
            }

            MembershipFee fee = new MembershipFee();
            fee.setAmount(dto.getAmount());
            fee.setLabel(dto.getLabel());
            fee.setFrequency(dto.getFrequency());
            fee.setEligibleFrom(dto.getEligibleFrom());
            fee.setCollectivityId(collectivityId);

            fees.add(fee);
        }

        feeRepository.saveAll(fees);

        return fees.stream().map(this::toFeeDTO).toList();
    }

    public List<MembershipFeeDTO> getMembershipFees(String collectivityId) {

        if (collectivityRepository.findById(collectivityId) == null) {
            throw new RuntimeException("Collectivity not found");
        }

        return feeRepository.findAllByCollectivity(collectivityId)
                .stream()
                .map(this::toFeeDTO)
                .toList();
    }

    public List<CollectivityTransactionDTO> getTransactions(String collectivityId,
                                                            String from,
                                                            String to) {

        if (collectivityRepository.findById(collectivityId) == null) {
            throw new RuntimeException("Collectivity not found");
        }

        return transactionRepository.findBetweenDates(
                        collectivityId,
                        LocalDate.parse(from),
                        LocalDate.parse(to)
                ).stream()
                .map(this::toTransactionDTO)
                .toList();
    }

    private MembershipFeeDTO toFeeDTO(MembershipFee fee) {
        MembershipFeeDTO dto = new MembershipFeeDTO();
        dto.setId(fee.getId());
        dto.setAmount(fee.getAmount());
        dto.setLabel(fee.getLabel());
        return dto;
    }

    private CollectivityTransactionDTO toTransactionDTO(CollectivityTransaction t) {
        CollectivityTransactionDTO dto = new CollectivityTransactionDTO();
        dto.setId(t.getId());
        dto.setAmount(t.getAmount());
        dto.setCreationDate(t.getCreationDate());
        return dto;
    }

    private MemberDTO toMemberDTO(Member m) {
        MemberDTO dto = new MemberDTO();
        dto.setId(m.getId());
        dto.setFirstName(m.getFirstName());
        dto.setLastName(m.getLastName());
        dto.setEmail(m.getEmail());
        dto.setPhoneNumber(m.getPhoneNumber());
        return dto;
    }

    public List<CollectivityTransactionDTO> getTransactions(String collectivityId,
                                                            LocalDate from,
                                                            LocalDate to) {

        if (collectivityRepository.findById(collectivityId) == null) {
            throw new RuntimeException("Collectivity not found");
        }

        return transactionRepository.findBetweenDates(
                        collectivityId,
                        from,
                        to
                ).stream()
                .map(this::toTransactionDTO)
                .toList();

    }

    public CollectivityDTO getCollectivityById(String id) {

        Collectivity collectivity = collectivityRepository.findById(id);

        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<Member> members = memberRepository.findByCollectivityId(
                collectivity.getIdCollectivity()
        );

        CollectivityDTO dto = toDTO(collectivity);

        dto.setMembers(
                members.stream()
                        .map(this::toMemberDTO)
                        .toList()
        );

        return dto;
    }
}