package org.example.prog3_agriculturalfederation.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Member {
    private Integer idMembre;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String genre;
    private String telephone;
    private String email;
    private LocalDate dateAdhesion;
    private String adresse;
    private String metier;
    private Integer idCollectivite;

    public Member(Integer idMembre, String nom, String prenom, LocalDate dateNaissance, String genre, String telephone, String email, LocalDate dateAdhesion, String adresse, String metier, Integer idCollectivite) {
        this.idMembre = idMembre;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.telephone = telephone;
        this.email = email;
        this.dateAdhesion = dateAdhesion;
        this.adresse = adresse;
        this.metier = metier;
        this.idCollectivite = idCollectivite;
    }

    public Integer getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(LocalDate dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
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
        Member member = (Member) o;
        return Objects.equals(idMembre, member.idMembre) && Objects.equals(nom, member.nom) && Objects.equals(prenom, member.prenom) && Objects.equals(dateNaissance, member.dateNaissance) && Objects.equals(genre, member.genre) && Objects.equals(telephone, member.telephone) && Objects.equals(email, member.email) && Objects.equals(dateAdhesion, member.dateAdhesion) && Objects.equals(adresse, member.adresse) && Objects.equals(metier, member.metier) && Objects.equals(idCollectivite, member.idCollectivite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMembre, nom, prenom, dateNaissance, genre, telephone, email, dateAdhesion, adresse, metier, idCollectivite);
    }

    @Override
    public String toString() {
        return "Membre{" +
                "idMembre=" + idMembre +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", genre='" + genre + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", dateAdhesion=" + dateAdhesion +
                ", adresse='" + adresse + '\'' +
                ", metier='" + metier + '\'' +
                ", idCollectivite=" + idCollectivite +
                '}';
    }
}
