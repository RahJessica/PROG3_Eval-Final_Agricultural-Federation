package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.TypeCompte;

import java.math.BigDecimal;
import java.util.Objects;

public class Compte {
    private Integer idCompte;
    private TypeCompte typeCompte;
    private BigDecimal soldeCompte;
    private Integer idFederation;
    private Integer idCollectivite;
    private Integer idMembre; // compte Mobile money assigné à un membre

    public Compte(Integer idCompte, TypeCompte typeCompte, BigDecimal soldeCompte, Integer idFederation, Integer idCollectivite, Integer idMembre) {
        this.idCompte = idCompte;
        this.typeCompte = typeCompte;
        this.soldeCompte = soldeCompte;
        this.idFederation = idFederation;
        this.idCollectivite = idCollectivite;
        this.idMembre = idMembre;
    }

    public Integer getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Integer idCompte) {
        this.idCompte = idCompte;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public BigDecimal getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(BigDecimal soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    public Integer getIdFederation() {
        return idFederation;
    }

    public void setIdFederation(Integer idFederation) {
        this.idFederation = idFederation;
    }

    public Integer getIdCollectivite() {
        return idCollectivite;
    }

    public void setIdCollectivite(Integer idCollectivite) {
        this.idCollectivite = idCollectivite;
    }

    public Integer getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Objects.equals(idCompte, compte.idCompte) && typeCompte == compte.typeCompte && Objects.equals(soldeCompte, compte.soldeCompte) && Objects.equals(idFederation, compte.idFederation) && Objects.equals(idCollectivite, compte.idCollectivite) && Objects.equals(idMembre, compte.idMembre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompte, typeCompte, soldeCompte, idFederation, idCollectivite, idMembre);
    }

    @Override
    public String toString() {
        return "Compte{" +
                "idCompte=" + idCompte +
                ", typeCompte=" + typeCompte +
                ", soldeCompte=" + soldeCompte +
                ", idFederation=" + idFederation +
                ", idCollectivite=" + idCollectivite +
                ", idMembre=" + idMembre +
                '}';
    }
}
