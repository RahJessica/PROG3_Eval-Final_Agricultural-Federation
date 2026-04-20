package org.example.prog3_agriculturalfederation.entity;

import java.util.Objects;

public class Affecter {
    private Integer idPoste;
    private Integer idMandat;

    public Affecter(Integer idPoste, Integer idMandat) {
        this.idPoste = idPoste;
        this.idMandat = idMandat;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public Integer getIdMandat() {
        return idMandat;
    }

    public void setIdMandat(Integer idMandat) {
        this.idMandat = idMandat;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Affecter affecter = (Affecter) o;
        return Objects.equals(idPoste, affecter.idPoste) && Objects.equals(idMandat, affecter.idMandat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPoste, idMandat);
    }

    @Override
    public String toString() {
        return "Affecter{" +
                "idPoste=" + idPoste +
                ", idMandat=" + idMandat +
                '}';
    }
}
