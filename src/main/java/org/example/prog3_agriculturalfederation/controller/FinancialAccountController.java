package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateFinancialAccountDTO;
import org.example.prog3_agriculturalfederation.dto.FinancialAccountDTO;
import org.example.prog3_agriculturalfederation.entity.FinancialAccount;
import org.example.prog3_agriculturalfederation.service.FinancialAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class FinancialAccountController {

    private final FinancialAccountService service;

    public FinancialAccountController(FinancialAccountService service) {
        this.service = service;
    }

    @PostMapping("/collectivities/{id}/accounts")
    public ResponseEntity <?> create(@PathVariable Integer id, @RequestBody CreateFinancialAccountDTO dto) {
        try  {
            FinancialAccount account = service.createAccount(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(account);

        } catch (IllegalArgumentException e ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    @GetMapping("/collectivities/{id}/financialAccount")
    public ResponseEntity<?> getAccounts(
            @PathVariable String id,
            @RequestParam String at
    ) {

        try {
            LocalDate date = LocalDate.parse(at);

            List<FinancialAccountDTO> accounts =
                    service.getAccounts(id, date);

            return ResponseEntity.ok(accounts);

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format");

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
