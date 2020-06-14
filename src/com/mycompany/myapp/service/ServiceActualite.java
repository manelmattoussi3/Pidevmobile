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
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Baleina
 */
public class ServiceActualite {
      public ArrayList<Actualite> actualiteList;

    public static ServiceActualite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceActualite() {
         req = new ConnectionRequest();
    }

    public static ServiceActualite getInstance() {
        if (instance == null) {
            instance = new ServiceActualite();
        }
        return instance;
    }
    
   public ArrayList<Actualite> parseActualite(String jsonText){
        try {
            actualiteList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Actualite a = new Actualite();
                float id = Float.parseFloat(obj.get("id").toString());
                //float idCategorie= Float.parseFloat(obj.get("idcategorie").toString());
                a.setIdActualite((int)id);
               // a.setIdcategorie((int)idCategorie);
               //a.setIdActualite(((int)Float.parseFloat(obj.get("idActualite").toString())));
               a.setTitre(obj.get("titre").toString());
                a.setContenu(obj.get("contenu").toString());
                actualiteList.add(a);
            }


        } catch (IOException ex) {

        }
        return actualiteList;
    }
   
  public ArrayList<Actualite> getAllActualite(){
        String url = Statics.Base_URL+"/listerActu";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                actualiteList = parseActualite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return actualiteList;
    }
   


}
