/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Service;

/**
 *
 * @author DevelopAndroid
 */
import com.mycompany.myapp.gui.showListFormation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.utils.Statics;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.TextModeLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceFormation {

    public ArrayList<Formation> formation;
    
    public static ServiceFormation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFormation() {
         req = new ConnectionRequest();
    }

    public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }

    public boolean addFormation(Formation f) {
       
        String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/Ajoutmobile?description="+f.getDescription()+"&date_debut="+f.getDate_Debut()+"&date_fin="+f.getDate_Fin()+"&nbr_place="+f.getNbr_Place()+"&categorie="+f.getCategorie()+"&coach_id="+ String.valueOf(f.getCoach_id())+"&pdf="+f.getPdf() ;
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

    public ArrayList<Formation> parseFormation(String jsonText){
        try {
            formation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> formationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)formationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Formation f = new Formation();
                float id = Float.parseFloat(obj.get("id").toString());
               // float coach_id = Float.parseFloat(obj.get("coach").toString());
                f.setId((int)id);
                f.setDescription(obj.get("description").toString());
                f.setDate_Debut(obj.get("dateDebut").toString());
                f.setDate_Fin(obj.get("dateFin").toString());
                 //f.setNbr_Place(((int)Float.parseFloat(obj.get("nbrPlace").toString())));
                 f.setNbr_Place(obj.get("nbrPlace").toString());
                 f.setCategorie(obj.get("categorie").toString());
                 //f.setCoach_id(((Double)obj.get("id")).intValue());
                // f.setPdf(obj.get("pdf").toString());
              // f.setCoach_id((int)coach_id);
                formation.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return formation;
    }
    
    public ArrayList<Formation> getAllFormations(){
        String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/Affichagemobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formation = parseFormation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return formation;
    }
    public Boolean updateFormation(Formation formation){
     String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/modifiermobile/"+formation.getId() + "?description="+ formation.getDescription() + "&date_debut=" + formation.getDate_Debut() +"&date_fin="+formation.getDate_Fin()+"&nbr_place="+formation.getNbr_Place()+"&categorie="+formation.getCategorie()+"&coach_id="+ formation.getCoach_id();
     System.out.println(url);    
     req.setUrl(url);
	         req.setPost(false);
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
    
   public void deleteFormation(Formation f) {
        
            String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/supprimermobile/"+ f.getId() ;
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
   

}

