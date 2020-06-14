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
public class User 
{
    private int id;
    private String nom;
    private String prenom; 
    private String email ; 
    private String role; 
    private int  telephone ; 
    private String dateNa; 
    private String password; 
    private String username; 
            
    public User()
    {
        
    }
      public User(String role)
    {
        this.role=role ; 
    }
    
        public User(int id, String nom, String prenom, String email, String role, int telephone, String dateNa, String password, String username) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
        this.dateNa = dateNa;
        this.password = password;
        this.username = username; 
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDateNa() {
        return dateNa;
    }

    public void setDateNa(String dateNa) {
        this.dateNa = dateNa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", role=" + role + ", telephone=" + telephone + ", dateNa=" + dateNa + ", password=" + password + '}';
    }

}
