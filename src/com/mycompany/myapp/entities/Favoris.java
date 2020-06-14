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
public class Favoris {
     private int idFavoris;
    private String titre;
    private String contenu;
    

    public Favoris(int idFavoris, String titre) {
        this.idFavoris = idFavoris;
        this.titre = titre;
    }

    public Favoris(String titre) {
        this.titre = titre;
    }
    
    

    public Favoris(int idFavoris, String titre, int utilisateur_id, int actualite_id) {
        this.idFavoris = idFavoris;
        this.titre = titre;
       this.contenu = contenu;
    }
    
    
    public Favoris() {
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
  
    public int getIdFavoris() {
        return idFavoris;
    }

    public void setIdFavoris(int idFavoris) {
        this.idFavoris = idFavoris;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    

    @Override
    public String toString() {
        return "Favoris{" + "idFavoris=" + idFavoris + ", titre=" + titre + '}';
    }
        
}
