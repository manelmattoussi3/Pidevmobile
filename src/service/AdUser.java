/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.User;
import Entite.UserAdd;
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
public class AdUser 
{
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

      
      public ArrayList<User> listeUsers ()
      {      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/users"); 
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
      
      
      public boolean Test ( String nom , String prenom , String mail , String tel )
      {
          if (nom=="")
          {
             return false ; 
                     
          }else
          {
              if (prenom =="")
              {
                  return false ; 
              }else
              {
                  if (mail=="")
                  {
                      return false ; 
                  }else
                      try {
			Integer.parseInt(tel);
                        } catch (NumberFormatException e)
                        {
                                return false;
                        }
                      
              }
          }
          
          return true ; 
      }
              

      public ArrayList<User> AddUserr (UserAdd a )
      {      
        
        ConnectionRequest con = new ConnectionRequest();
        try
        {
        con.setUrl("http://127.0.0.1:8000/api/adduser/" +a.getNb()+ "/" +a.getUsername()+ "/" +a.getMail()+ "/" +a.getNom()+ "/" +a.getPrenom()+"/" +a.getTel()+ "/" +a.getDate()+ "/" +a.getRole()+ "/"+ a.getPassword() ); 
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listuser = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser;
        }
        catch ( Exception ex)
        {
            System.err.println("ereurn en inscription");
        }
           
        
        return listuser; 
    }
     
    
}
