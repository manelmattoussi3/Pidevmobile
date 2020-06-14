/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import com.mycompany.entites.Publication;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.PublicationForm;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khawla
 */
public class PublicationService {
    public ArrayList<Publication> publication;
    public static PublicationService instance = null;
    public boolean resultOK;
    String nbrBeforeConvert;
    int nbAfterConvert;
    private ConnectionRequest req;
    public PublicationService() {
        req = new ConnectionRequest();
    }
    
    public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }
    
    public int getNbrPublication() {
    String url ="http://127.0.0.1:8000/api/publication/getNbr";
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
   
    @Override
    public void actionPerformed(NetworkEvent evt) {
        nbrBeforeConvert  = new String(req.getResponseData());
        nbAfterConvert   = Integer.parseInt(nbrBeforeConvert);
            }
            
        });
               
    NetworkManager.getInstance().addToQueueAndWait(req);
    return nbAfterConvert;
    }
    public ArrayList<Publication> getPublication(int UserId) {
            publication = new ArrayList<>();
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1:8000/api/publication/affiche?userId="+UserId);
            JSONParser j = new JSONParser();
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    try{
                     
                    Map<String,Object> mapPublication= j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPublication.get("root");
                      
                    for(Map<String,Object> obj :listOfMaps) {
                       
                        Publication p = new Publication();
                        Map<String, Object> User = (Map<String, Object>) obj.get("User");
                        
                        Map<String, Object> Evenement = (Map<String, Object>) obj.get("evenement");
         
                       // System.out.println("usr= " + User);
                        float id = Float.parseFloat(obj.get("id").toString());
                        p.setTitre(obj.get("titre").toString());
                        p.setCategorie(obj.get("categorie").toString());
                        p.setDescription(obj.get("description").toString());  
                        String file = obj.get("file").toString();
                        float iduser =Float.parseFloat(User.get("id").toString());
                         float idevene =Float.parseFloat(Evenement.get("id").toString());

                        p.setFile(file);
                        p.setId((int)id);
                        p.setUser_id((int)iduser);
                        p.setEvenement_id((int)idevene);
                        
                        System.out.println("id event for pub = "+idevene+" , id user ="+iduser);
                        
                        
                    publication.add(p);
                
                    }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
            });
        
            
            
            NetworkManager.getInstance().addToQueueAndWait(con);
        return publication;
        }
    
    
    /*Tranformation de web service*/
    public ArrayList<Publication> getListPub() {
            publication = new ArrayList<>();
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1:8000/api/publicationBack/all-back");
            JSONParser j = new JSONParser();
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    try{
                    Map<String,Object> mapEvenement = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenement.get("root");
                      
                    for(Map<String,Object> obj :listOfMaps) {
                        Publication p = new Publication();
                        Map<String, Object> User = (Map<String, Object>) obj.get("User");
                        System.out.println("usr= " + User);
                        float id = Float.parseFloat(obj.get("id").toString());
                  float idU = Float.parseFloat(User.get("id").toString());
                        p.setTitre(obj.get("titre").toString());
                        p.setFile(obj.get("file").toString());
                        p.setCategorie(obj.get("categorie").toString());
                        
                        
                        p.setDescription(obj.get("description").toString());  
                        p.setId((int)id);
                        
                        
                      
                publication.add(p);
                
                    }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
            });
        
            
            
            NetworkManager.getInstance().addToQueueAndWait(con);
        return publication;
        }
    
public void ajoutPublication(Publication p  ) {
        
        MultipartRequest mr = new MultipartRequest();
        String url = "http://127.0.0.1:8000/api/publication/add?titre="+p.getTitre()+
                "&description="+p.getDescription()+"&categorie="+p.getCategorie()+
                "&file="+p.getFile()+"&iduser="+SessionManager.getId()+"&idevent="+p.getEvenement_id();
        try{
        mr.setUrl(url);
        mr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                  String str = new String(mr.getResponseData());
                  System.out.println("data ="+str);
                  
                    Dialog.show("Success","Publication a ete bien ajouté", "OK",null);
                  PublicationForm form = new PublicationForm(Resources.getGlobalResources());
                  form.show();
                  form.revalidate();
                  form.refreshTheme();
                  form.repaint();
                  form.show();
                  form.revalidate();
                  form.refreshTheme();
                  form.repaint();
                  form.animateLayout(1000);
              
       
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(mr);
    }catch(Exception ex ) {
        ex.printStackTrace();
    }
    }
    }

    
