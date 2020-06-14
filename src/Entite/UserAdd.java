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
public class UserAdd 
{
    int nb ; 
    String username; 
    String mail; 
    String nom; 
    String prenom; 
    int tel ; 
     String date ; 
    String role ; 
    String password ; 
    
    public UserAdd()
    {
        
    }
    public UserAdd(int nb, String username, String mail, String nom, String prenom, int tel, String date, String role, String password) {
        this.nb = nb;
        this.username = username;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.date = date;
        this.role = role;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AddUser{" + "nb=" + nb + ", username=" + username + ", mail=" + mail + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", date=" + date + ", role=" + role + ", password=" + password + '}';
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    
}
