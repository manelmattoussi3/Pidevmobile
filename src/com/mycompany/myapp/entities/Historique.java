/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Baleina
 */
public class Historique {
    private int idHistorique;
    private String titre;
    private String dateAjout;
    private String contenu;
    

    public Historique() {
    }
    
    
    public Historique(int idHistorique, String titre, String dateAjout) {
        this.idHistorique = idHistorique;
        this.titre = titre;
        this.dateAjout = dateAjout;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getIdHistorique() {
        return idHistorique;
    }

  
    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    @Override
    public String toString() {
        return "Historique{" + "idHistorique=" + idHistorique + ", titre=" + titre + ", dateAjout=" + dateAjout + '}';
    }
    
    
}
