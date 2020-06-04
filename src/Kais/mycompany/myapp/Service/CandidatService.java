/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.Service;

import Kais.mycompany.myapp.entities.Candidat;
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
 * @author Khoubaib
 */
public class CandidatService {
    
 public ArrayList<Candidat> candidat;
    
    public static CandidatService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     public CandidatService() {
         req = new ConnectionRequest();
    }
    
    public static CandidatService getInstance() {
        if (instance == null) {
            instance = new CandidatService();
        }
        return instance;
    }

    
    public ArrayList<Candidat> parseCandidat(String jsonText){
     
        try {
            candidat=new ArrayList<Candidat>();
            JSONParser j = new JSONParser();
            Map<String,Object> candidatListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)candidatListJson.get("root");

            for(Map<String,Object> obj : list){
            
                Candidat t = new Candidat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
               
                
                candidat.add(t);
            }
            
        } catch (IOException ex) {
       
        }
        return candidat;
  
    }
public ArrayList<Candidat> getCandidat(){
       
        String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/Affichecandidat";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() { //recevoir la reponse
            @Override
            public void actionPerformed(NetworkEvent evt) {
                candidat = parseCandidat(new String(req.getResponseData())); //je vais le recevoir sous forme d'un tableau de byte                                        
                req.removeResponseListener(this);                                //je dois le convertir en tableua en chaine de caratere
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  //envoyer des requetes au serveur d'une facon asynchrone
        return candidat;
    }
}

