package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.ModePaiement;
import org.example.prog3_agriculturalfederation.entity.enums.TypeCotisation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cotisation {
    private Integer idCotisation;
    private BigDecimal montant;
    private LocalDate datePaiement;
    private TypeCotisation typeCotisation;
    private ModePaiement modePaiement;
    private Integer idCollectivite;
    private Integer idMembre;
}
