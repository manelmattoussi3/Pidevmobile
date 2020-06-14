/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entite.Reclamation;
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
public class reclamation

{
    User user ; 
    public reclamation(User user )
    {
        this.user=user; 
    }
      public ArrayList<Reclamation> parseListTaskJson(String json) {

        ArrayList<Reclamation> listreclamtion = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

         
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            try
            {

                
               for (Map<String, Object> obj : list)
               {
                Reclamation e = new Reclamation();
                e.setMail(user.getEmail());
                e.setSujet(obj.get("sujet").toString());
                e.setReclamation(obj.get("reclamation").toString());
                e.setEtat(obj.get("etat").toString());
                e.setReponse(obj.get("reponse").toString());
                listreclamtion.add(e);
               }

            }
            catch(Exception ex)
            {
              
                System.err.println(ex.toString());  
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
        System.out.println(listreclamtion);
        return listreclamtion;

    }
      ArrayList<Reclamation> listreclamtion = new ArrayList<>();
       public ArrayList<Reclamation> listeReclmation (int  id)
      {      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/ListeReclamtion/"+id); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // ServiceUser ser = new ServiceUser();
                reclamation ser = new reclamation(user); 
                listreclamtion = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listreclamtion;
    }
      

    
}
