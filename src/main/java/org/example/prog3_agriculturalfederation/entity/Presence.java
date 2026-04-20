package org.example.prog3_agriculturalfederation.entity;

import java.util.Objects;

public class Presence {
    private Integer idPresence;
    private Integer idActivite;
    private Boolean present;
    private Boolean excuse;
    private String motif;
    private Integer idMembre;

    public Presence(Integer idPresence, Integer idActivite, Boolean present, Boolean excuse, String motif, Integer idMembre) {
        this.idPresence = idPresence;
        this.idActivite = idActivite;
        this.present = present;
        this.excuse = excuse;
        this.motif = motif;
        this.idMembre = idMembre;
    }

    public Integer getIdPresence() {
        return idPresence;
    }

    public void setIdPresence(Integer idPresence) {
        this.idPresence = idPresence;
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Boolean getExcuse() {
        return excuse;
    }

    public void setExcuse(Boolean excuse) {
        this.excuse = excuse;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
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
        Presence presence = (Presence) o;
        return Objects.equals(idPresence, presence.idPresence) && Objects.equals(idActivite, presence.idActivite) && Objects.equals(present, presence.present) && Objects.equals(excuse, presence.excuse) && Objects.equals(motif, presence.motif) && Objects.equals(idMembre, presence.idMembre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPresence, idActivite, present, excuse, motif, idMembre);
    }

    @Override
    public String toString() {
        return "Presence{" +
                "idPresence=" + idPresence +
                ", idActivite=" + idActivite +
                ", present=" + present +
                ", excuse=" + excuse +
                ", motif='" + motif + '\'' +
                ", idMembre=" + idMembre +
                '}';
    }
}
