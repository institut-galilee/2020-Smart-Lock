package com.example.smartlockapp;

public class Historique {
    private String  id, pseudo, statut;


    public Historique(String id, String pseudo, String statut) {
        this.id = id;
        this.pseudo = pseudo;
        this.statut = statut;
    }

    public Historique() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
