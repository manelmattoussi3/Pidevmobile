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
public class AjouterReclamationn
{
    User user ; 
    public AjouterReclamationn(User user )
    {
        this.user=user; 
    }
      
      
       public void AjoutReclmation (String sujet, String reclamationn)
      {      
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/api/AddReclamation/"+user.getId()+"/"+sujet+"/"+reclamationn); 
    
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
      
    
}
