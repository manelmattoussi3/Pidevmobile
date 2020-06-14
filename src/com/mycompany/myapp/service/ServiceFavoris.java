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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Baleina
 */
public class ServiceFavoris {
    private Database db;
    private boolean exist;

    public ServiceFavoris() {
        
        exist = Database.exists("persist");
        System.out.println(exist);
        
        try {
            db = Database.openOrCreate("persist");
            
            if(!exist)
            {
                db.execute("Create Table favoris (idFavoris INTEGER primary key, titre TEXT, contenu TEXT)");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void ajouterFavoris(Favoris f) throws IOException
    {
        db.execute("insert into favoris(titre,contenu) values('"+f.getTitre()+"','"+f.getContenu()+"')");
        System.out.println("Ajout ok");
     
    }
    
     public void supprimerFavoris() throws IOException
    {
        db.execute("Delete From Favoris");
        System.out.println("suppression ok");
     
    }
    
    public ArrayList<Favoris> getListFavoris() throws IOException
    {
        ArrayList<Favoris> liste = new ArrayList<>();
        Cursor c = db.executeQuery("Select * from favoris");
        while (c.next())
        {
            Row r = c.getRow();
            
            Favoris f = new Favoris();
            
            int idFavoris = r.getInteger(0);
            
            String titre = r.getString(1);
            String contenu = r.getString(2);
            
            
            f.setIdFavoris(idFavoris);
            f.setTitre(titre);
            f.setContenu(contenu);
            
            liste.add(f);
        }
        
        return liste;
    }
}
