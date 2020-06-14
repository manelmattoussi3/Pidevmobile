/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Baleina
 */
public class Actualite {
    private int idActualite;
    private String titre;
    private String contenu;
    private String dateAjout;
    private int idcategorie;
    
    private List<Note> Note = new ArrayList<>();
    private List<Commentaire> Commentaire = new ArrayList<>();
    private List<Historique> Historique = new ArrayList<>();
    //private List<Categorie> categorie = new ArrayList<>();
    public Actualite() {
    }

    public Actualite(int idActualite, String titre, String contenu, String dateAjout, int idcategorie) {
        this.idActualite = idActualite;
        this.titre = titre;
        this.contenu = contenu;
        this.dateAjout = dateAjout;
        this.idcategorie = idcategorie;
    }
    
    

    public Actualite(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

   
    
    public Actualite(String titre, int idcategorie) {
        this.titre = titre;
       
    }
    
    
    
    public int getIdActualite() {
        return idActualite;
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

    public List<Note> getNote() {
        return Note;
    }

    public void setIdActualite(int idActualite) {
        this.idActualite = idActualite;
    }

    public int getIdcategorie() {
        return idcategorie;
    }
    
    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
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

    public void setNote(List<Note> Note) {
        this.Note = Note;
    }

    @Override
    public String toString() {
        return "Actualite{" + "idActualite=" + idActualite + ", titre=" + titre + ", contenu=" + contenu + ", dateAjout=" + dateAjout+"'}";
    }

}
