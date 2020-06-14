/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author DevelopAndroid
 */
public class Coach {

    private int idcoach;

    private  String nom;
    private String prenom;

    public Coach() {
    }

  /*  public Coach(int idcoach, String nom, String prenom) {
        this.idcoach = idcoach;

        this.nom = nom;
        this.prenom = prenom;

    }*/

    public int getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(int idcoach) {
        this.idcoach = idcoach;
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

    @Override
    public String toString() {
        return "Coach{" + "idcoach=" + idcoach + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

}
