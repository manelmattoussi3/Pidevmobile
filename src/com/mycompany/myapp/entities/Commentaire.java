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
public class Commentaire {
    private int idCommentaire;
    private String titre;
    private String contenu;
    private String dateAjout;
    private int actualite_id;
    private int utilisateur;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, String titre, String contenu, String dateAjout) {
        this.idCommentaire +=1;
        this.titre = titre;
        this.contenu = contenu;
        this.dateAjout = dateAjout;
        
    }

    public Commentaire(String contenu, int actualite_id) {
        this.contenu = contenu;
        this.actualite_id = actualite_id;
    }
    
    public Commentaire(String contenu) {
        this.contenu = contenu;
    }

    public int getActualite_id() {
        return actualite_id;
    }

    public void setActualite_id(int actualite_id) {
        this.actualite_id = actualite_id;
    }
    
    

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }
    
  

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setTitre(String titre) {
        
        
            this.titre = titre;
       
        
    }

    public void setContenu(String contenu) {
        
            this.contenu = contenu;
       
        
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

   

    @Override
    public String toString() {
        return "Commentaire{" + " contenu=" + contenu + '}';
    }
    
    

   
}
