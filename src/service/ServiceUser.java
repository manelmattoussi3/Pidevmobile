/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.User;
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
 * @author Nasri
 */
public class ServiceUser 
{
    
    public String Direction (String a)
      {
          if (a =="false")
          {
              System.err.println("ereuur");
              return "erreur"; 
          }
          else 
              if (a.contains("CONDIDAT"))
              {
                  System.err.println("condiat");
                  return "condidat"; 
              }else
              {
                  if (a.contains("ADMIN"))
                  {
                      System.err.println("admin");
                      return "admin"; 
                  }
                  else 
                  {
                      if (a.contains("USER"))
                      {
                          System.err.println("user");
                          return "user"; 
                      }
                      else
                      {
                          if (a.contains("COACH"))
                          {
                              return "coach"; 
                          }
                          else 
                          {
                              if(a.contains("JURY "))
                              {
                                  return "jury"; 
                              }
                              else
                              {
                                  if(a.contains("SPONSOR"))
                                  {
                                  return "sponsor"; 
                                  }
                              }
                          }
                      }
                      
                  }
              }
          
 
        
          return "pas de reponse"; 
      }
    
    
     public void ajoutTask(User ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://41.226.11.252:1130/tasks/" + ta.getNom() + "/" + ta.getNom();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public ArrayList<User> parseListTaskJson(String json) {

        ArrayList<User> listuser = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

         
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            try
            {
                  for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User e = new User();

                float id = Float.parseFloat(obj.get("id").toString());
                float telephone = Float.parseFloat(obj.get("tel").toString());


                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setEmail(obj.get("email").toString());
                e.setRole(obj.get("roles").toString());
                e.setTelephone((int)telephone);
                e.setDateNa(obj.get("image").toString());
                e.setPassword(obj.get("password").toString()); 
                e.setUsername(obj.get("username").toString());
              
                System.out.println(e);
                
                listuser.add(e);

            }

            }
            catch(Exception ex)
            {
                
            }

          
        } 
        
        catch (IOException ex) 
        {
            System.err.println("test valider");
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listuser);
        return listuser;

    }
    
    
    ArrayList<User> listuser = new ArrayList<>();
    
    public ArrayList<User> getList2(User a ){      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/findMail/"+ a.getEmail() + "/" + a.getPassword()); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listuser = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser;
    }
      public ArrayList<User> connexion(String email , String password ){      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/findMail/"+ email + "/" + password); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                ServiceUser ser = new ServiceUser();
                listuser = ser.parseListTaskJson(new String(con.getResponseData()));
            
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return listuser;
        
    }
      /*traitement de forget password*/
       public ArrayList<User> idmail(String email ){      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/findbymail/"+ email); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                ServiceUser ser = new ServiceUser();
                listuser = ser.parseListTaskJson(new String(con.getResponseData()));
            
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return listuser;
        
    }
       /* changer le mot de passe */
      public ArrayList<User> forget_password(int id , String password){      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/forgetPassword/"+ id+ "/" +password); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                ServiceUser ser = new ServiceUser();
                listuser = ser.parseListTaskJson(new String(con.getResponseData()));
            
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return listuser;
        
    }
    
      User a ; 
      public User Testconnexion(String email , String password )
      {      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/findMail/"+ email + "/" + password); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                ServiceUser ser = new ServiceUser();
                listuser = ser.parseListTaskJson(new String(con.getResponseData()));
            
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if(listuser.isEmpty())
        {
            User user = new User(); 
            return a ; 
             
        }
        else 
        { 
            try
            {
                User user = new User(listuser.get(0).getId(), listuser.get(0).getNom(), listuser.get(0).getPrenom(), listuser.get(0).getEmail(), listuser.get(0).getRole(), listuser.get(0).getTelephone(), listuser.get(0).getDateNa(), listuser.get(0).getPassword(), listuser.get(0).getUsername());
                
                 return user ;

            }
            catch (Exception ex )
            {
                            

            }
        }
    User user_pasvalide = new User("pas valide"); 
            return user_pasvalide ; 
        
    }
      
}
