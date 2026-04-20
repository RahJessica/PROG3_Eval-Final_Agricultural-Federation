package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.TypeActivite;

import java.time.LocalDate;
import java.util.Objects;

public class Activite {
    private Integer idActivite;
    private TypeActivite typeActivite;
    private LocalDate dateActivite;
    private Boolean obligatoire;
    private String description;
    private Integer idFederation;
    private Integer idCollectivite;

    public Activite(Integer idActivite, TypeActivite typeActivite, LocalDate dateActivite, Boolean obligatoire, String description, Integer idFederation, Integer idCollectivite) {
        this.idActivite = idActivite;
        this.typeActivite = typeActivite;
        this.dateActivite = dateActivite;
        this.obligatoire = obligatoire;
        this.description = description;
        this.idFederation = idFederation;
        this.idCollectivite = idCollectivite;
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public TypeActivite getTypeActivite() {
        return typeActivite;
    }

    public void setTypeActivite(TypeActivite typeActivite) {
        this.typeActivite = typeActivite;
    }

    public LocalDate getDateActivite() {
        return dateActivite;
    }

    public void setDateActivite(LocalDate dateActivite) {
        this.dateActivite = dateActivite;
    }

    public Boolean getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Activite activite = (Activite) o;
        return Objects.equals(idActivite, activite.idActivite) && typeActivite == activite.typeActivite && Objects.equals(dateActivite, activite.dateActivite) && Objects.equals(obligatoire, activite.obligatoire) && Objects.equals(description, activite.description) && Objects.equals(idFederation, activite.idFederation) && Objects.equals(idCollectivite, activite.idCollectivite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivite, typeActivite, dateActivite, obligatoire, description, idFederation, idCollectivite);
    }

    @Override
    public String toString() {
        return "Activite{" +
                "idActivite=" + idActivite +
                ", typeActivite=" + typeActivite +
                ", dateActivite=" + dateActivite +
                ", obligatoire=" + obligatoire +
                ", description='" + description + '\'' +
                ", idFederation=" + idFederation +
                ", idCollectivite=" + idCollectivite +
                '}';
    }
}
