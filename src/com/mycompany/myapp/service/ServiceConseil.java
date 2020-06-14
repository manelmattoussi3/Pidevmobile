/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Service;

import com.mycompany.myapp.entities.Conseil;

import com.codename1.io.CharArrayReader;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DevelopAndroid
 */
public class ServiceConseil {
   public ArrayList<Conseil> conseil;
    
    public static ServiceConseil instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceConseil() {
         req = new ConnectionRequest();
    }

    public static ServiceConseil getInstance() {
        if (instance == null) {
            instance = new ServiceConseil();
        }
        return instance;
    } 
     public ArrayList<Conseil> parseConseil(String jsonText){
        try {
            conseil=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> conseilsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)conseilsListJson.get("root");
            for(Map<String,Object> obj : list){
                Conseil co = new Conseil();
                float id = Float.parseFloat(obj.get("id").toString());
                co.setIdcons((int)id);
                co.setDescriptioncons(obj.get("description").toString());
                co.setCategoricons(obj.get("categoricons").toString());
                co.setImagecons(obj.get("image").toString());
                 co.setCoach_id(((Double)obj.get("id")).intValue());
                co.setCandidat_id(((Double)obj.get("id")).intValue());
                conseil.add(co);
            }
            
            
        } catch (IOException ex) {
            
        }
        return conseil;
    }
    public ArrayList<Conseil> getAllConseils(){
        String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/Afficheconseilmobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                conseil = parseConseil(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return conseil;
    }
      public void deleteConseil(Conseil co) {
        
            String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/supprimemobile/"+ co.getIdcons();
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            // return resultOK;
        
    }
      public boolean addConseil(Conseil con) {
       
        String url ="http://localhost/pidev-integration/web/app_dev.php/Projet/Ajoutermobile?description="+con.getDescriptioncons()+"&categoricons="+con.getCategoricons()+"&image="+con.getImagecons()+"&candidat_id="+String.valueOf(con.getCandidat_id())+"&coach_id="+String.valueOf(con.getCoach_id());
        //  String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/Ajoutmobile?description="+f.getDescription()+"&date_debut="+f.getDate_Debut()+"&date_fin="+f.getDate_Fin()+"&nbr_place="+f.getNbr_Place()+"&categorie="+f.getCategorie()+"&coach_id="+ String.valueOf(f.getCoach_id())+"&pdf="+f.getPdf() ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
}
