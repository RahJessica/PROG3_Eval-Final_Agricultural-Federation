package org.example.prog3_agriculturalfederation.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Collectivity {
    private Integer idCollectivity;
    private String nameCollectivity;
    private String town;
    private String speciality;
    private LocalDate creationDate;
    private Boolean autorisation;
    private String numero;
    private Integer idFederation;
    private List<Member> members;

    public Collectivity() {}

    public Collectivity(Integer idCollectivity, String nameCollectivity, String town, String speciality, LocalDate creationDate, Boolean autorisation, String numero, Integer idFederation, List<Member> members) {
        this.idCollectivity = idCollectivity;
        this.nameCollectivity = nameCollectivity;
        this.town = town;
        this.speciality = speciality;
        this.creationDate = creationDate;
        this.autorisation = autorisation;
        this.numero = numero;
        this.idFederation = idFederation;
        this.members = members;
    }

    public Integer getIdCollectivity() {
        return idCollectivity;
    }

    public void setIdCollectivity(Integer idCollectivity) {
        this.idCollectivity = idCollectivity;
    }

    public String getNameCollectivity() {
        return nameCollectivity;
    }

    public void setNameCollectivity(String nameCollectivity) {
        this.nameCollectivity = nameCollectivity;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Collectivity that = (Collectivity) o;
        return Objects.equals(idCollectivity, that.idCollectivity) && Objects.equals(nameCollectivity, that.nameCollectivity) && Objects.equals(town, that.town) && Objects.equals(speciality, that.speciality) && Objects.equals(creationDate, that.creationDate) && Objects.equals(autorisation, that.autorisation) && Objects.equals(numero, that.numero) && Objects.equals(idFederation, that.idFederation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCollectivity, nameCollectivity, town, speciality, creationDate, autorisation, numero, idFederation);
    }

    @Override
    public String toString() {
        return "Collectivity{" +
                "idCollectivity=" + idCollectivity +
                ", nameCollectivity='" + nameCollectivity + '\'' +
                ", town='" + town + '\'' +
                ", speciality='" + speciality + '\'' +
                ", creationDate=" + creationDate +
                ", autorisation=" + autorisation +
                ", numero='" + numero + '\'' +
                ", idFederation=" + idFederation +
                '}';
    }
}