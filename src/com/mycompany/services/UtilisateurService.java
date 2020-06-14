/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Utilisateur;
import com.mycompany.gui.NewsfeedForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.WalkthruForm;

import java.util.Map;
//import javafx.scene.text.Text;

/**
 *
 * @author Chédy
 */
public class UtilisateurService {
    
 
       // public ArrayList<Utilisateur> utilisateurs;
    
    public static UtilisateurService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public UtilisateurService() {
         req = new ConnectionRequest();
    }

    public static UtilisateurService getInstance() {
        if (instance == null) {
            instance = new UtilisateurService();
        }
        return instance;
    }
    
    
    public  void login(TextField username,TextField password,Resources res) {
        String  url = "http://127.0.0.1:8000/api/login"
                + "?username="+username.getText().toString()+"&password="+password.getText().toString()
                   ;
               req = new ConnectionRequest(url,false);
               req.setUrl(url);
              //  req.addArgument("username",username.getText());
                //req.addArgument("password",password.getText());
                req.addResponseListener((action) -> {
                     try {
            
                            JSONParser j = new JSONParser();
                            String json = new String(req.getResponseData()) + "";
                            if (json.equals("failed")){

                                Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                             
                            }
                            else{

                            System.out.println("hello ="+ json);

                               Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                            System.out.println(user);
                                float id = Float.parseFloat(user.get("id").toString());
                                System.out.println((int)id);
                                SessionManager.setId((int)id);
                                SessionManager.setPass(password.getText());
                                SessionManager.setUserName(user.get("username").toString());
                                SessionManager.setEmail(user.get("email").toString());
                                
                                if (user.get("roles") !=null){
                                SessionManager.setRoles(user.get("roles").toString());
                                }
                            
                               
                                 if(user.size()>0 ) {
                                           
           
                  NewsfeedForm n = new NewsfeedForm(Resources.getGlobalResources());
                n.revalidate();
                n.refreshTheme();
                n.repaint();
                n.animateLayout(1000);
                n.show();
            
         
                     new NewsfeedForm(res).show();
            

                            }
                            
                            }
                            
                     }
                   catch(Exception e ){
                       e.printStackTrace();
                   }
                                
                });
                              NetworkManager.getInstance().addToQueue(req);
          
    }
    
    
    
    
    
    
 
     
     public String getUsername(String us) {
        ArrayList<String> listTopics = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/login?username="+us);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        listTopics.add(obj.get("username").toString());
                       

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopics.get(0);
    }
     
  public static Image UrlImage(String photo){
        String url ="http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/images_user?img="+photo;
                EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("photo-profile.jpg"), false);
                Image urli = URLImage.createToStorage(placeholder,"Medium_"+url, url,URLImage.RESIZE_SCALE);
                
        return urli;        
    }
    
    
    public static void EditUser(String username, String password, String email, String photo){
        
    String url = "http://127.0.0.1/ProjetWebSymfony/test/web/app_dev.php/api/edit_user";
                MultipartRequest req = new MultipartRequest();
                
        try {
            req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("username", username);
                req.addArgument("password", password);
                req.addArgument("email", email);
                System.out.println(email);
            if(!photo.equals("")){    
            req.addData("photo", photo,"image/jpeg");
            req.setFilename("photo", username+".jpg");
            }
             req.addResponseListener((response)-> {
                        
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);                                                
                        if (s.equals("success")) {                            
                        } else { 
                            Dialog.show("Erreur", "Echec de modification", "Ok", null);
                        }                    
                });

                NetworkManager.getInstance().addToQueueAndWait(req);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
   
    
        
     
    
}
    
    
      