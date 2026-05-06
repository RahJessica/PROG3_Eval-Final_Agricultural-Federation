package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.*;
import org.example.prog3_agriculturalfederation.entity.Collectivity;
import org.example.prog3_agriculturalfederation.entity.FinancialAccount;
import org.example.prog3_agriculturalfederation.service.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
    public class CollectivityController {

        private final CollectivityService service;

        public CollectivityController(CollectivityService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<List<CollectivityDTO>> create(
                @RequestBody List<CreateCollectivityDTO> requests
        ) {
            return ResponseEntity.status(201)
                    .body(service.createCollectivities(requests));
        }

    @PutMapping("/{id}/informations")
    public ResponseEntity<CollectivityDTO> updateInformations(
            @PathVariable Integer id,
            @RequestBody CollectivityInformationDTO request
    ) {
        return ResponseEntity.ok(
                service.updateCollectivityInformation(id, request)
        );
    }

    @PostMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeDTO>> createMembershipFees(
            @PathVariable Integer id,
            @RequestBody List<CreateMembershipFeeDTO> request
    ) {
        return ResponseEntity.ok(
                service.createMembershipFees(id, request)
        );
    }

    @GetMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeDTO>> getMembershipFees(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                service.getMembershipFees(id)
        );
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<CollectivityTransactionDTO>> getTransactions(
            @PathVariable Integer id,
            @RequestParam String from,
            @RequestParam String to
    ) {
        return ResponseEntity.ok(
                service.getTransactions(id, from, to)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectivityDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getCollectivityById(id));
    }

    @GetMapping
    public ResponseEntity<List<CollectivityDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCollectivities());
    }

    @GetMapping("/{id}/statistics")
    public ResponseEntity<?> getStatistics(
            @PathVariable Integer id,
            @RequestParam String from,
            @RequestParam String to
    ) {
        return ResponseEntity.ok(
                service.getStatistics(
                        id,
                        LocalDate.parse(from),
                        LocalDate.parse(to)
                )
        );
    }
}
