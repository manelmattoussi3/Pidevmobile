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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.CommentaireForum;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Baleina
 */
public class ServiceCommentaireForum {
    
    public ArrayList<CommentaireForum> commentaireForum;
    public static ServiceCommentaireForum instance=null;
    public boolean resultOk;
    private ConnectionRequest req;
    
    private ServiceCommentaireForum()
    {
        req = new ConnectionRequest();
    }
    
    public static ServiceCommentaireForum getInstance()
    {
        if(instance == null)
        {
            instance = new ServiceCommentaireForum();
        }
        
        return instance;
    }
    
     public boolean ajouterCommentaire(CommentaireForum c)
    {
        //String url = Statics.Base_URL + "/commentaireActu/new?contenu=" +c.getContenu()+"&actualite_id="+c.getArticle_id();
        //String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus(); //création de l'URL
        String url = Statics.Base_URL + "/commentaireForum/new/" + c.getArticle_id() +"?contenu="+ c.getContenu();
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
    
   public ArrayList<CommentaireForum> parseCommentaireForum(String jsonText)
    {
        try {
            commentaireForum =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> commentaireListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)commentaireListJson.get("root");
            for(Map<String,Object> obj : list){
                CommentaireForum c = new CommentaireForum();
                //float id = Float.parseFloat(obj.get("id").toString());
                //c.setId((int)id);
                c.setId(((int)Float.parseFloat(obj.get("id").toString())));
                c.setContenu(obj.get("contenu").toString());
                c.setDate_ajout(obj.get("dateAjout").toString());
               
              
                //c.setUtilisateur(1);
                commentaireForum.add(c);
            }


        } catch (IOException ex) {

        }
        return commentaireForum;
    }
    
    public ArrayList<CommentaireForum> getAllCommentaire(int a)
    {
        String url = Statics.Base_URL+"/article/" + a;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaireForum = parseCommentaireForum(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        return commentaireForum; 
    }
    
}
