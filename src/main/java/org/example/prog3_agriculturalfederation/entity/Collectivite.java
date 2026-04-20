package org.example.prog3_agriculturalfederation.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Collectivite {
    private Integer idCollectivite;
    private String nomCollectivite;
    private String ville;
    private String specialite;
    private LocalDate creationDate;
    private Boolean autorisation;
    private String numero;
    private Integer idFederation;

    public Collectivite(Integer idCollectivite, String nomCollectivite, String ville, String specialite, LocalDate creationDate, Boolean autorisation, String numero, Integer idFederation) {
        this.idCollectivite = idCollectivite;
        this.nomCollectivite = nomCollectivite;
        this.ville = ville;
        this.specialite = specialite;
        this.creationDate = creationDate;
        this.autorisation = autorisation;
        this.numero = numero;
        this.idFederation = idFederation;
    }

    public Integer getIdCollectivite() {
        return idCollectivite;
    }

    public void setIdCollectivite(Integer idCollectivite) {
        this.idCollectivite = idCollectivite;
    }

    public String getNomCollectivite() {
        return nomCollectivite;
    }

    public void setNomCollectivite(String nomCollectivite) {
        this.nomCollectivite = nomCollectivite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getIdFederation() {
        return idFederation;
    }

    public void setIdFederation(Integer idFederation) {
        this.idFederation = idFederation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Collectivite that = (Collectivite) o;
        return Objects.equals(idCollectivite, that.idCollectivite) && Objects.equals(nomCollectivite, that.nomCollectivite) && Objects.equals(ville, that.ville) && Objects.equals(specialite, that.specialite) && Objects.equals(creationDate, that.creationDate) && Objects.equals(autorisation, that.autorisation) && Objects.equals(numero, that.numero) && Objects.equals(idFederation, that.idFederation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCollectivite, nomCollectivite, ville, specialite, creationDate, autorisation, numero, idFederation);
    }

    @Override
    public String toString() {
        return "Collectivite{" +
                "idCollectivite=" + idCollectivite +
                ", nomCollectivite='" + nomCollectivite + '\'' +
                ", ville='" + ville + '\'' +
                ", specialite='" + specialite + '\'' +
                ", creationDate=" + creationDate +
                ", autorisation=" + autorisation +
                ", numero='" + numero + '\'' +
                ", idFederation=" + idFederation +
                '}';
    }
}
