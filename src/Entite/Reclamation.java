/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Nasri
 */
public class Reclamation 
{
    String mail; 
    String sujet; 
    String reclamation; 
    String etat; 
    String reponse ; 
    public Reclamation()
    {
         
     }

    public Reclamation(String mail, String sujet, String reclamation, String etat, String reponse) {
        this.mail = mail;
        this.sujet = sujet;
        this.reclamation = reclamation;
        this.etat = etat;
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "mail=" + mail + ", sujet=" + sujet + ", reclamation=" + reclamation + ", etat=" + etat + ", reponse=" + reponse + '}';
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getReclamation() {
        return reclamation;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    
}
