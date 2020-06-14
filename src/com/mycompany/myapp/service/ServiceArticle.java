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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Baleina
 */
public class ServiceArticle {
    public ArrayList<Article> articleList;
    public static ServiceArticle instance=null;
     public boolean resultOk;
    private ConnectionRequest req;

    private ServiceArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }
    
     public boolean ajouterArticle(Article a)
    {
        //String url = Statics.Base_URL + "/commentaireActu/new?contenu=" +c.getContenu()+"&actualite_id="+c.getArticle_id();
        //String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus(); //création de l'URL
        String url = Statics.Base_URL + "/articlem/new?titre=" + a.getTitre() +"&contenu="+ a.getContenu();
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
    
    
    public ArrayList<Article> parseArticle(String jsonText){
        try {
            articleList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> articleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)articleListJson.get("root");
            for(Map<String,Object> obj : list){
                Article a = new Article();
                float id = Float.parseFloat(obj.get("id").toString());
               // float idUser= Float.parseFloat(obj.get("user_id").toString());
               a.setId((int)id);
               // a.setUser_id((int)idUser);
                a.setTitre(obj.get("titre").toString());
                a.setContenu(obj.get("contenu").toString());
               //a.setDate_ajout(obj.get("date_ajout").toString());
               
                articleList.add(a);
            }
            } catch (IOException ex) {

        }
        return articleList;
           
}
    public ArrayList<Article> getAllArticle(){
        String url = Statics.Base_URL+"/listerA";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articleList = parseArticle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articleList;
    }
    
    public ArrayList<Article> getAllArticleContenu(int a){
        String url = Statics.Base_URL+"/article/" + a;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articleList = parseArticle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articleList;
    }
    
}
