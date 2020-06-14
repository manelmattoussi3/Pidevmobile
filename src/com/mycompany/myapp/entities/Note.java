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
public class Note {
    private int idNote;
    private double valeur;
    private int actualite_id;
    private int utilisateur_id;
    
    public Note()
    {
        
    }
    
    public Note(int idNote, int valeur) {
        this.idNote =idNote;
        this.valeur = valeur;
    }

    public Note(double valeur) {
        this.valeur = valeur;
    }
    
    

    public int getIdNote() {
        return idNote;
    }

    public double getValeur() {
        return valeur;
    }
    
    public int getActualite_id() {
        return actualite_id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public void setValeur(double valeur) {
            this.valeur = valeur;
        
    }

    public void setActualite_id(int actualite_id) {
        this.actualite_id = actualite_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }
 

    @Override
    public String toString() {
        return "Note{" + "idNote=" + idNote + ", valeur=" + valeur + '}';
    }
    
    
}
