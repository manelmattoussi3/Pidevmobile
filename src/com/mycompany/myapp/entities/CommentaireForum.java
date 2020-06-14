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
public class CommentaireForum {
    
    private int id;
    private int article_id;
    private String contenu;
    private String date_ajout;
    private int user_id;

    public CommentaireForum() {
    }

    public CommentaireForum(int id, int article_id, String contenu, String date_ajout, int user_id) {
        this.id = id;
        this.article_id = article_id;
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

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
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
        return "commentaireForum{" + "id=" + id + ", article_id=" + article_id + ", contenu=" + contenu + ", date_ajout=" + date_ajout + ", user_id=" + user_id + '}';
    }
    
    
    
}
