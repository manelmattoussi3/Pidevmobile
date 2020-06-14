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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import com.mycompany.entites.Localisation;
import com.mycompany.entites.Participation;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
import java.util.Date;


/**
 *
 * @author Khawla
 */
public class EvenementService {
    public ArrayList<Evenement> evenement;
    public static EvenementService instance = null;
    public boolean resultOK;
    String nbrBeforeConvert;
    int nbAfterConvert;
    private ConnectionRequest req;
    public EvenementService() {
        req = new ConnectionRequest();
    }
    
    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }
    /*Tranformation de web service*/
    public ArrayList<Evenement> getEvenemnet() {
            evenement = new ArrayList<>();
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1:8000/api/evenement/affiche");
            JSONParser j = new JSONParser();
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    try{
                        Map<String,Object> mapEvenement = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenement.get("root");
                      
                       for(Map<String,Object> obj :listOfMaps) {
                       
                        Evenement e = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        e.setTitle(obj.get("titre").toString());
                        e.setDescription(obj.get("description").toString());
                        e.setCategories(obj.get("categories").toString());
                        String DateS = String.valueOf(obj.get("dateDebut"));
                        e.setDatedebut(DateS);
                        String DateS1 = String.valueOf(obj.get("dateFin"));
                        e.setDatefin(DateS1);
                        float nbmin =Float.parseFloat(obj.get("nombreMinparticipants").toString());
                        String localisation = String.valueOf(obj.get("localisation"));
                        float nbmax =Float.parseFloat(obj.get("nombreMaxparticipants").toString());
                        String isp =obj.get("isPublic").toString();
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        String image = obj.get("imagepath").toString();
                        e.setNombreMinParticipants((int)nbmin);
                        e.setNombreMaxParticipants((int)nbmax);
                        e.setPrix((int)prix);
                        e.setLocalisation(localisation);
                        if(isp.equals("true"))
                        e.setIsPublic(1);
                        else 
                        e.setIsPublic(0);
                        e.setImagepath(image);
                        e.setId((int)id);
                evenement.add(e);
                
                    }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
            });
        
            
            
        NetworkManager.getInstance().addToQueueAndWait(con);
        return evenement;
        }
    
    
    
    public Evenement DetailEvenement(int id , Evenement e) {
        String url ="http://127.0.0.1:8000/api/evenement/one-event?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try{
                        Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                        float id = Float.parseFloat(obj.get("id").toString());
                        e.setTitle(obj.get("titre").toString());
//                      e.setDescription(obj.get("description").toString());
                        e.setCategories(obj.get("categories").toString());
                        e.setDatedebut(obj.get("dateDebut").toString().substring(0, 10));
                        e.setDatefin(obj.get("dateFin").toString().substring(0, 10));
                        float nbmin =Float.parseFloat(obj.get("nombreMinparticipants").toString());
                        float nbmax =Float.parseFloat(obj.get("nombreMaxparticipants").toString());
                        String image = obj.get("imagepath").toString();
                        e.setNombreMinParticipants((int)nbmin);
                        e.setNombreMaxParticipants((int)nbmax);
                        e.setImagepath(image);
                    
                }catch(Exception e ) {
                    System.out.println("error retalted to sql!!");
                }
                String str = new String(req.getResponseData());
                System.out.println(str);
            }
        });
    
          
        NetworkManager.getInstance().addToQueueAndWait(req);

    return e;
}
    
    
    public void ajoutEvenement(Evenement e,String nom  ) {
        
        MultipartRequest mr = new MultipartRequest();
        String url = "http://127.0.0.1:8000/api/evenement/ajouter?title="+e.getTitle()+
                "&description="+e.getDescription()+"&categories="+e.getCategories()+
                "&dateDebut="+e.getDatedebut()+"&dateFin="+e.getDatefin()+
                "&nombreMinparticipants="+e.getNombreMinParticipants()+"&nombreMaxparticipants="+e.getNombreMaxParticipants()
                +"&isPublic="+e.getIsPublic()+"&localisation="+e.getLocalisation()+
                
                "&prix="+e.getPrix()+"&imagepath="+e.getImagepath();
        try{
        mr.setUrl(url);
        
        
      
        
        mr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                    //Dialog.show("Failed", null, new Command("OK"));
                    String str = new String(mr.getResponseData());
                    System.out.println("data ="+str);
                    Dialog.show("Success","Evenement a ete bien ajouté", "OK",null);
                    NewsfeedForm form = new NewsfeedForm(Resources.getGlobalResources());
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
    
    
    public void modifierEvenement(Evenement e ,String path ) {
        
        ConnectionRequest multipartRequest = new ConnectionRequest();
        String url = "http://127.0.0.1:8000/api/evenement/update?imagepath="+path;
        
        try {
            multipartRequest.setUrl(url);
            multipartRequest.setPost(true);
            multipartRequest.addArgument("id", String.valueOf(e.getId()));
            multipartRequest.addArgument("title", e.getTitle());
            multipartRequest.addArgument("description", e.getDescription());
            multipartRequest.addArgument("dateDebut", e.getDatedebut());
            multipartRequest.addArgument("dateFin", e.getDatefin());
            multipartRequest.addArgument("categories", e.getCategories());

            multipartRequest.addArgument("isPublic",String.valueOf(e.getIsPublic()));   
            
            
            
            multipartRequest.addArgument("localisation",e.getLocalisation());   
            
            
           
            if(e.getPrix() !=0 ) {
                multipartRequest.addArgument("prix", String.valueOf(e.getPrix()));
            }
            if(e.getNombreMinParticipants() !=0 && e.getNombreMaxParticipants() !=0) {
                 multipartRequest.addArgument("nombreMinparticipants",String.valueOf( e.getNombreMinParticipants()));
            multipartRequest.addArgument("nombreMaxparticipants", String.valueOf(e.getNombreMaxParticipants()));
          
            }
           
            multipartRequest.addResponseListener((response) -> {
                String s = new String(multipartRequest.getResponseData());
                System.out.println("data edit response from server = "+s);
                if(s.equals("success"))
                    Dialog.show("Success", "Evenement a ete bien modifié", "OK", null);
                else 
                   Dialog.show("fail", "Modification echouée", "OK", null);
        
            });
            
            NetworkManager.getInstance().addToQueueAndWait(multipartRequest);

            
        }catch(Exception exception  ) {
            exception.printStackTrace();
        }
    }
   
    
    public void SupprimerEvenement(int id_e) {
             String url ="http://127.0.0.1:8000/api/evenement/supprimer?id="+id_e;
             ConnectionRequest con = new ConnectionRequest();
             con.setUrl(url);
                      NetworkManager.getInstance().addToQueueAndWait(con);

         }
         
    public int getNbrEvenement() {
              String url ="http://127.0.0.1:8000/api/evenement/getNbr";
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

         
    public int getNbrParticpation() {
              String url ="http://127.0.0.1:8000/api/allparticipation/getNbre";
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

         
    public void participerEvenement(int iduser, int idevent) {
        String url ="http://127.0.0.1:8000/api/evenement/confirmerParticpant?User_id="+iduser+"&id="+idevent;
        
        req.setUrl(url);
        
              
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                    //Dialog.show("Failed", null, new Command("OK"));
                    String str = new String(req.getResponseData());
                    System.out.println("data ="+str);
            }
        
    });
       NetworkManager.getInstance().addToQueueAndWait(req);
        }
   
    public void envyerDemandeParticipation(int iduser,int idevenet) {
        String url="http://127.0.0.1:8000/api/evenement/envoyerDemande?iduser="+iduser+"&id="+idevenet;
           req.setUrl(url);
           
           System.out.println(req.getRequestBody());
           NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    public ArrayList<Participation> getParticpationByEvent(int id) {
            ArrayList<Participation> participations = new ArrayList<>();
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1:8000/api/participation/getParticipationByEvent?id="+id);
            JSONParser j = new JSONParser();
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    try{
                        Map<String,Object> mapEvenement = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenement.get("root");
                      
                    for(Map<String,Object> obj :listOfMaps) {
                    Map<String, Object> evenement = (Map<String, Object>)obj.get("evenement");
                    Map<String, Object> user = (Map<String, Object>)obj.get("User");

                        Participation participation= new Participation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String titre = evenement.get("title").toString();
                        float iduser = Float.parseFloat(user.get("id").toString());
                        float idevent = Float.parseFloat(evenement.get("id").toString());
                        String username = user.get("username").toString();
                        String etat = obj.get("nom").toString();

                        participation.setTitre(titre);
                        participation.setUsername(username);   
                        participation.setNom(etat);         
                        participation.setId((int)id);
                        participation.setUser_id((int)iduser);
                        participation.setEvanement_id((int)idevent);
                participations.add(participation);
                
                    }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
            });
        
            
            
            NetworkManager.getInstance().addToQueueAndWait(con);
        return participations;
        }
    
    public ArrayList<Evenement> getSearchListEvenement(String q) {

        ArrayList<Evenement> participations = new ArrayList<>();
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1:8000/api/evenement/search?q="+q);
            JSONParser j = new JSONParser();
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    try{
                        String s =new String(con.getResponseData());
                        
                        if(s.equals("fail"))
                            return ;
                        else{
                              Map<String,Object> mapEvenement = j.parseJSON(new CharArrayReader(s.toCharArray(
                              )));
                              List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenement.get("root");
                      
                            for(Map<String,Object> obj :listOfMaps) {

                        Evenement participation= new Evenement();
                        String titre = obj.get("titre").toString();
                        String image = obj.get("imagepath").toString();
                        float id = Float.parseFloat(obj.get("id").toString());
                        participation.setTitle(titre);
                        participation.setImagepath(image);
                       
                        participation.setId((int)id);
                        participations.add(participation);
                
                    }}
                        
                        
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                
                      
                                            }

                    
                    
                
            });
        
            
            
            NetworkManager.getInstance().addToQueueAndWait(con);
        return participations;
       
    }     
        

  
}
