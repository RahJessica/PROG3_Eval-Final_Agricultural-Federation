package org.example.prog3_agriculturalfederation.controller;

import org.example.prog3_agriculturalfederation.dto.CreateFinancialAccountDTO;
import org.example.prog3_agriculturalfederation.entity.FinancialAccount;
import org.example.prog3_agriculturalfederation.service.FinancialAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

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
}
