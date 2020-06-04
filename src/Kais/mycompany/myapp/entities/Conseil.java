/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.entities;

/**
 *
 * @author DevelopAndroid
 */
public class Conseil {

    private int idcons;
    private String descriptioncons;
    private String Imagecons;
    private String Categoricons;
    private int Coach_id;
    private int Candidat_id;

    public int getCoach_id() {
        return Coach_id;
    }

    public void setCoach_id(int Coach_id) {
        this.Coach_id = Coach_id;
    }

    public int getCandidat_id() {
        return Candidat_id;
    }

    public void setCandidat_id(int Candidat_id) {
        this.Candidat_id = Candidat_id;
    }

    public int getIdcons() {
        return idcons;
    }

    public void setIdcons(int idcons) {
        this.idcons = idcons;
    }

    public String getDescriptioncons() {
        return descriptioncons;
    }

    public void setDescriptioncons(String descriptioncons) {
        this.descriptioncons = descriptioncons;
    }

    public String getImagecons() {
        return Imagecons;
    }

    public void setImagecons(String Imagecons) {
        this.Imagecons = Imagecons;
    }

    public String getCategoricons() {
        return Categoricons;
    }

    public void setCategoricons(String Categoricons) {
        this.Categoricons = Categoricons;
    }

    public Conseil() {
    }

    public Conseil(String descriptioncons,String Categoricons ,String Imagecons,int Coach_id,int Candidat_id) {
        this.idcons = idcons;
        this.descriptioncons = descriptioncons;
        this.Imagecons = Imagecons;
        this.Categoricons = Categoricons;
        this.Coach_id=Coach_id;
        this.Candidat_id=Candidat_id;
    }

    @Override
    public String toString() {
        return "Conseil{" + "idcons=" + idcons + ", descriptioncons=" + descriptioncons + ", Imagecons=" + Imagecons + ", Categoricons=" + Categoricons + ", Coach_id=" + Coach_id + ", Candidat_id=" + Candidat_id + '}';
    }

   

}
