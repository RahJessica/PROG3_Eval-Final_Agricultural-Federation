package org.example.prog3_agriculturalfederation.controller;
import org.example.prog3_agriculturalfederation.dto.CollectivityDTO;
import org.example.prog3_agriculturalfederation.dto.CreateCollectivityDTO;
import org.example.prog3_agriculturalfederation.entity.Collectivity;
import org.example.prog3_agriculturalfederation.service.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    }
