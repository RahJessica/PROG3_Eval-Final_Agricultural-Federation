package org.example.prog3_agriculturalfederation.entity;

import org.example.prog3_agriculturalfederation.entity.enums.TypeMandat;

import java.time.LocalDate;
import java.util.Objects;

public class Mandat {
    private Integer idMandat;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeMandat typeMandat;

    public Mandat(Integer idMandat, LocalDate dateDebut, LocalDate dateFin, TypeMandat typeMandat) {
        this.idMandat = idMandat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeMandat = typeMandat;
    }

    public Integer getIdMandat() {
        return idMandat;
    }

    public void setIdMandat(Integer idMandat) {
        this.idMandat = idMandat;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TypeMandat getTypeMandat() {
        return typeMandat;
    }

    public void setTypeMandat(TypeMandat typeMandat) {
        this.typeMandat = typeMandat;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mandat mandat = (Mandat) o;
        return Objects.equals(idMandat, mandat.idMandat) && Objects.equals(dateDebut, mandat.dateDebut) && Objects.equals(dateFin, mandat.dateFin) && typeMandat == mandat.typeMandat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMandat, dateDebut, dateFin, typeMandat);
    }

    @Override
    public String toString() {
        return "Mandat{" +
                "idMandat=" + idMandat +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", typeMandat=" + typeMandat +
                '}';
    }
}
