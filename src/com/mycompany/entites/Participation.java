/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author Khawla
 */
public class Participation {
    
        private int id;
    private int evenement_id;
    private String etat;
    private String prenom;
    private String Email;
    private int Cin;
    private int numTel;
    private int iduser;
        private String titre;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public Participation(String nom, String username, String titre) {
        this.etat = nom;
        this.username = username;
        this.titre = titre;
    }


    public Participation(String nom, String prenom, String Email, int Cin, int numTel, int User_id, String titre) {
        this.etat = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Cin = Cin;
        this.numTel = numTel;
        this.iduser = User_id;
        this.titre = titre;
    }

    public int getEvanement_id() {
        return evenement_id;
    }

    public void setEvanement_id(int evanement_id) {
        this.evenement_id = evanement_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public Participation(int id, int evanement_id, String nom, String prenom, String Email, int Cin, int numTel, int User_id) {
        this.id = id;
        this.evenement_id = evanement_id;
        this.etat = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Cin = Cin;
        this.numTel = numTel;
        this.iduser = User_id;
    }
    
    public Participation() {
        
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", nom=" + etat + ", username=" + username + ", titre=" + titre + '}';
    }

 
    public Participation(int evanement_id, String nom, String prenom, String Email, int Cin, int numTel, int User_id) {
        this.evenement_id = evanement_id;
        this.etat = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.Cin = Cin;
        this.numTel = numTel;
        this.iduser = User_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getNom() {
        return etat;
    }

    public void setNom(String nom) {
        this.etat = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getCin() {
        return Cin;
    }

    public void setCin(int Cin) {
        this.Cin = Cin;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public int getUser_id() {
        return iduser;
    }

    public void setUser_id(int User_id) {
        this.iduser = User_id;
    }

    
    
    
}
