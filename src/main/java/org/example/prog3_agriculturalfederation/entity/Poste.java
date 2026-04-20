package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.TypePoste;

import java.util.Objects;

public class Poste {
    private Integer idPoste;
    private TypePoste nomPoste;
    private Integer idMembre;

    public Poste(Integer idPoste, TypePoste nomPoste, Integer idMembre) {
        this.idPoste = idPoste;
        this.nomPoste = nomPoste;
        this.idMembre = idMembre;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public TypePoste getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(TypePoste nomPoste) {
        this.nomPoste = nomPoste;
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
        Poste poste = (Poste) o;
        return Objects.equals(idPoste, poste.idPoste) && nomPoste == poste.nomPoste && Objects.equals(idMembre, poste.idMembre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPoste, nomPoste, idMembre);
    }

    @Override
    public String toString() {
        return "Poste{" +
                "idPoste=" + idPoste +
                ", nomPoste=" + nomPoste +
                ", idMembre=" + idMembre +
                '}';
    }
}
