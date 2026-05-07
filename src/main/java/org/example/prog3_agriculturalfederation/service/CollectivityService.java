package org.example.prog3_agriculturalfederation.service;

import org.example.prog3_agriculturalfederation.dto.*;
import org.example.prog3_agriculturalfederation.entity.*;
import org.example.prog3_agriculturalfederation.entity.enums.Status;
import org.example.prog3_agriculturalfederation.repository.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectivityService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;
    private final MembershipFeeRepository feeRepository;
    private final TransactionRepository transactionRepository;
    private final CollectivityStatisticsRepository collectivityStatisticsRepository;

    public CollectivityService(MemberRepository memberRepository,
                               CollectivityRepository collectivityRepository,
                               MembershipFeeRepository feeRepository,
                               TransactionRepository transactionRepository,
                               CollectivityStatisticsRepository collectivityStatisticsRepository) {
        this.memberRepository = memberRepository;
        this.collectivityRepository = collectivityRepository;
        this.feeRepository = feeRepository;
        this.transactionRepository = transactionRepository;
        this.collectivityStatisticsRepository = collectivityStatisticsRepository;
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

    public CollectivityDTO updateCollectivityInformation(Integer id,
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

    public List<MembershipFeeDTO> createMembershipFees(Integer collectivityId,
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

    public List<MembershipFeeDTO> getMembershipFees(Integer collectivityId) {

        if (collectivityRepository.findById(collectivityId) == null) {
            throw new RuntimeException("Collectivity not found");
        }

        return feeRepository.findAllByCollectivity(collectivityId)
                .stream()
                .map(this::toFeeDTO)
                .toList();
    }

    public List<CollectivityTransactionDTO> getTransactions(Integer collectivityId,
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

    public List<CollectivityTransactionDTO> getTransactions(Integer collectivityId,
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

    public CollectivityDTO getCollectivityById(Integer id) {

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

    public List<CollectivityDTO> getAllCollectivities() {

        List<Collectivity> collectivities = collectivityRepository.findAll();

        return collectivities.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<MemberStatisticsDTO> getStatistics(Integer collectivityId,
                                                   LocalDate from,
                                                   LocalDate to) {

        Collectivity c = collectivityRepository.findById(collectivityId);
        if (c == null) throw new RuntimeException("Collectivity not found");

        List<Member> members = memberRepository.findByCollectivityId(collectivityId);

        List<MembershipFee> activeFees =
                feeRepository.findAllByCollectivity(collectivityId)
                        .stream()
                        .filter(f -> f.getStatus() == Status.ACTIVE)
                        .toList();

        List<CollectivityTransaction> transactions =
                transactionRepository.findBetweenDates(collectivityId, from, to);

        Map<String, Double> paidByMember = new HashMap<>();

        for (CollectivityTransaction t : transactions) {
            String memberId = t.getMemberId();

            if (memberId == null) continue;

            paidByMember.put(
                    memberId,
                    paidByMember.getOrDefault(memberId, 0.0) + t.getAmount()
            );
        }

        double expected = calculateExpected(activeFees, from, to);

        List<MemberStatisticsDTO> result = new ArrayList<>();

        for (Member m : members) {

            String key = "C" + collectivityId + "-M" + m.getId();
            double paid = paidByMember.getOrDefault(key, 0.0);

            MemberStatisticsDTO dto = new MemberStatisticsDTO();
            dto.setMemberId(m.getId());
            dto.setName(m.getFirstName());
            dto.setTotalPaid(paid);
            dto.setTotalUnpaid(Math.max(0, expected - paid));

            result.add(dto);
        }

        return result;
    }

    private double calculateExpected(List<MembershipFee> fees,
                                     LocalDate from,
                                     LocalDate to) {

        double total = 0;

        for (MembershipFee fee : fees) {

            if (fee.getFrequency() == null) {
                continue;
            }

            switch (fee.getFrequency()) {

                case WEEKLY:
                    long weeks = java.time.temporal.ChronoUnit.WEEKS.between(from, to);
                    total += weeks * fee.getAmount();
                    break;

                case MONTHLY:
                    long months = java.time.temporal.ChronoUnit.MONTHS.between(from, to);
                    total += months * fee.getAmount();
                    break;

                case YEARLY:
                    long years = java.time.temporal.ChronoUnit.YEARS.between(from, to);
                    total += years * fee.getAmount();
                    break;

                case PUNCTUALLY:
                    if (!fee.getEligibleFrom().isAfter(to)) {
                        total += fee.getAmount();
                    }
                    break;
            }
        }

        return total;
    }

    public List<CollectivityOverallStatisticsDTO> getOverallStatistics( LocalDate from, LocalDate to) {
        try {
            List<Collectivity> collectivities = collectivityRepository.findAll();

            List<CollectivityOverallStatisticsDTO> result = new ArrayList<>();

            for (Collectivity c : collectivities) {

                CollectivityOverallStatisticsDTO dto = new CollectivityOverallStatisticsDTO();

                dto.id = c.getIdCollectivity();

                dto.complianceRate =
                        collectivityStatisticsRepository.getComplianceRate(c.getIdCollectivity(), from, to);

                dto.newMembers =
                        collectivityStatisticsRepository.countNewMembers(c.getIdCollectivity(), from, to);

                result.add(dto);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}