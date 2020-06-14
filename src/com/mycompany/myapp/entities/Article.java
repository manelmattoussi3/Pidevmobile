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
public class Article {
    
    private int id;
    private String titre;
    private String contenu;
    private String date_ajout;
    private int user_id;
    private List<Commentaire> commentaire = new ArrayList<>();

    public List<Commentaire> getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(List<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }
    public Article() {
    }

    public Article(int id, String titre, String contenu, String date_ajout, int user_id) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date_ajout = date_ajout;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "article{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", date_ajout=" + date_ajout + ", user_id=" + user_id + '}';
    }
    
    
}
