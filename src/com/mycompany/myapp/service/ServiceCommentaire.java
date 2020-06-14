/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Baleina
 */
public class ServiceCommentaire {
    
    public ArrayList<Commentaire> commentaire;
    public static ServiceCommentaire instance=null;
    public boolean resultOk;
    private ConnectionRequest req;
    
    private ServiceCommentaire()
    {
        req = new ConnectionRequest();
    }
    
    public static ServiceCommentaire getInstance()
    {
        if(instance == null)
        {
            instance = new ServiceCommentaire();
        }
        
        return instance;
    }
    
    public boolean ajouterCommentaire(Commentaire c)
    {
        //String url = Statics.Base_URL + "/commentaireActu/new?contenu=" +c.getContenu()+"&actualite_id="+c.getArticle_id();
        //String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus(); //création de l'URL
        String url = Statics.Base_URL + "/commentaireActu/new/" + c.getActualite_id() +"?contenu="+ c.getContenu();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOk = req.getResponseCode() == 200;
                    req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    public ArrayList<Commentaire> parseCommentaire(String jsonText)
    {
        try {
            commentaire =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> commentaireListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)commentaireListJson.get("root");
            for(Map<String,Object> obj : list){
                Commentaire c = new Commentaire();
                //float id = Float.parseFloat(obj.get("id").toString());
                //c.setIdCommentaire((int)id);
                c.setContenu(obj.get("contenu").toString());
                c.setActualite_id(((int)Float.parseFloat(obj.get("id").toString())));
               // float idActualite = Float.parseFloat(obj.get("article_id").toString());
               c.setDateAjout(obj.get("dateAjout").toString());
                //c.setArticle_id((int)idActualite);
                //c.setUtilisateur(1);
               // c.setUtilisateur(obj.get("username").toString());
                commentaire.add(c);
            }


        } catch (IOException ex) {

        }
        return commentaire;
    }
    
    public ArrayList<Commentaire> getAllCommentaire(int a)
    {
        String url = Statics.Base_URL+"/actualite/" + a;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaire = parseCommentaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        return commentaire; 
    }
}
