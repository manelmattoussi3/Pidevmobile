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
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Baleina
 */
public class ServiceNote {
    public ArrayList<Note> noteList;

    public static ServiceNote instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceNote() {
         req = new ConnectionRequest();
    }

    public static ServiceNote getInstance() {
        if (instance == null) {
            instance = new ServiceNote();
        }
        return instance;
    }
    
     public boolean addNote(Note n) {
       // String url = Statics.Base_URL + "/note/new?valeur=" + n.getValeur()+"&actualite_id="+ n.getActualite_id();
       String url = Statics.Base_URL + "/note/new/" + n.getActualite_id() +"?valeur="+ n.getValeur();
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
     
public ArrayList<Note> parseNote(String jsonText){
        try {
            noteList=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> noteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)noteListJson.get("root");
            for(Map<String,Object> obj : list){
                Note n = new Note();
               // float id = Float.parseFloat(obj.get("id").toString());
                float id = Float.parseFloat(obj.get("idNote").toString());
                float idActualite = Float.parseFloat(obj.get("actualite_id").toString());
                float valeur = Float.parseFloat(obj.get("valeur").toString());
                n.setIdNote((int)id);
                n.setValeur((int)valeur);
                n.setUtilisateur_id(1);
                n.setActualite_id((int)idActualite);
                
                noteList.add(n);
            }


        } catch (IOException ex) {

        }
        return noteList;

}    
public ArrayList<Note> getAllNote(){
        String url = Statics.Base_URL +"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                noteList = parseNote(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return noteList;
    }
}
