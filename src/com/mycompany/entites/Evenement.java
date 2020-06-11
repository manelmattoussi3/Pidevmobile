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
public class Evenement {
    private int id ;
    private String title;
    private String description;;
    private String localisation;
    private String etablissement;
    private int rating;
    private String categories;
    private String datedebut ;
    private String datefin;
    private int NombreMinParticipants;
    private int NombreMaxParticipants;
    private int isPublic;
    private String imagepath;
    private int nbactuel;
    private int is_payed;
    //private File image;
    private int prix;

    public Evenement() {
    }

    public Evenement(String title, String description, String categories, String datedebut, String datefin, int NombreMinParticipants, int NombreMaxParticipants, int isPublic, String imagepath, int prix,String loca) {
        this.title = title;
        this.localisation =loca;
        this.description = description;
        this.categories = categories;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.NombreMinParticipants = NombreMinParticipants;
        this.NombreMaxParticipants = NombreMaxParticipants;
        this.isPublic = isPublic;
        this.imagepath = imagepath;
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

  

    public Evenement(int id, String title, String description, String categories, String datedebut, String datefin, int NombreMinParticipants, int NombreMaxParticipants, int isPublic, String imagepath, int prix) {
        this.id = id;
        this.title = title;
        this.description = description;
   
        this.categories = categories;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.NombreMinParticipants = NombreMinParticipants;
        this.NombreMaxParticipants = NombreMaxParticipants;
        this.isPublic = isPublic;
        this.imagepath = imagepath;
 
        this.prix = prix;
    }

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getNombreMinParticipants() {
        return NombreMinParticipants;
    }

    public void setNombreMinParticipants(int NombreMinParticipants) {
        this.NombreMinParticipants = NombreMinParticipants;
    }

    public int getNombreMaxParticipants() {
        return NombreMaxParticipants;
    }

    public void setNombreMaxParticipants(int NombreMaxParticipants) {
        this.NombreMaxParticipants = NombreMaxParticipants;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getNbactuel() {
        return nbactuel;
    }

    public void setNbactuel(int nbactuel) {
        this.nbactuel = nbactuel;
    }

    public int getIs_payed() {
        return is_payed;
    }

    public void setIs_payed(int is_payed) {
        this.is_payed = is_payed;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", title=" + title + ", description=" + description + ", localisation=" + localisation + ", etablissement=" + etablissement + ", rating=" + rating + ", categories=" + categories + ", datedebut=" + datedebut + ", datefin=" + datefin + ", NombreMinParticipants=" + NombreMinParticipants + ", NombreMaxParticipants=" + NombreMaxParticipants + ", isPublic=" + isPublic + ", imagepath=" + imagepath + ", nbactuel=" + nbactuel + ", is_payed=" + is_payed + ", prix=" + prix + '}';
    }



    
}
