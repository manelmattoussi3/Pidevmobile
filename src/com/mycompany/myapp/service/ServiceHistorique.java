/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Historique;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Baleina
 */
public class ServiceHistorique {
    private Database db;
    private boolean exist;

    public ServiceHistorique() {
        
        exist = Database.exists("persist");
        System.out.println(exist);
        
        try {
            db = Database.openOrCreate("persist");
            
            if(exist)
            {
                db.execute("Create Table historique (idHistorique INTEGER primary key, titre TEXT, contenu Text , dateHistorique DATETIME)");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void ajouterHistorique(Historique h) throws IOException
    {
        db.execute("insert into historique(titre,contenu,dateHistorique) values('"+h.getTitre()+"','"+h.getContenu()+"','"+h.getDateAjout()+"')");
        System.out.println("Ajout ok");
     
    }
    
    public void supprimerHistorique() throws IOException
    {
        db.execute("Delete From Historique");
        System.out.println("suppression ok");
     
    }
    
    public ArrayList<Historique> getListHistorique() throws IOException
    {
        ArrayList<Historique> liste = new ArrayList<>();
        Cursor c = db.executeQuery("Select * from historique");
        while (c.next())
        {
            Row r = c.getRow();
            
            Historique h = new Historique();
            
            int idHistorique = r.getInteger(0);
            
            String titre = r.getString(1);
            String contenu = r.getString(2);
            String date = r.getString(3);
            
            h.setTitre(titre);
            h.setContenu(contenu);
            h.setDateAjout(date);
            
            liste.add(h);
        }
        
        return liste;
    }
}
