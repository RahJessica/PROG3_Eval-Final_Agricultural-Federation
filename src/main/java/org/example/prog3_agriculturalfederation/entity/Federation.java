package org.example.prog3_agriculturalfederation.entity;

import java.util.Objects;

public class Federation {
    private Integer idFederation;
    private String nomFederation;

    public Federation(Integer idFederation, String nomFederation) {
        this.idFederation = idFederation;
        this.nomFederation = nomFederation;
    }

    public Integer getIdFederation() {
        return idFederation;
    }

    public void setIdFederation(Integer idFederation) {
        this.idFederation = idFederation;
    }

    public String getNomFederation() {
        return nomFederation;
    }

    public void setNomFederation(String nomFederation) {
        this.nomFederation = nomFederation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Federation that = (Federation) o;
        return Objects.equals(idFederation, that.idFederation) && Objects.equals(nomFederation, that.nomFederation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFederation, nomFederation);
    }

    @Override
    public String toString() {
        return "Federation{" +
                "idFederation=" + idFederation +
                ", nomFederation='" + nomFederation + '\'' +
                '}';
    }
}
