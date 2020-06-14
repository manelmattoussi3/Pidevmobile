/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Historique;
import com.mycompany.myapp.service.ServiceFavoris;
import com.mycompany.myapp.service.ServiceHistorique;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Baleina
 */
public class HistoriqueForm extends Form{
    Form  current;
     private Resources theme;
    public HistoriqueForm(Form previous) {
         theme = UIManager.initFirstTheme("/theme");
        current = this;
        setLayout(BoxLayout.y());
        this.setTitle("Votre historique");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        //Container c1 = new Container(BoxLayout.y());
        this.getAllStyles().setBgColor(16777215);
        ArrayList<Historique> listeHistorique = new ArrayList<>();
        Form HistoriqueDetails = new Form("Détails Historique");
        ServiceHistorique sh = new ServiceHistorique();
        
            try {
                listeHistorique = sh.getListHistorique();
                for(Historique h : listeHistorique)
                {
                     Label titre = new Label(h.getTitre());
                    //Label contenu = new Label(h.getTitre());
                    Label date = new Label(h.getDateAjout());
                    addAll(titre,date);
                    
                   
                    
                    titre.addPointerPressedListener((evt) -> {
                        SpanLabel detailsHistorique = new SpanLabel();
                        detailsHistorique.setText(h.getContenu());
                        HistoriqueDetails.add(detailsHistorique);
                        HistoriqueDetails.getToolbar().addCommandToLeftBar("Back", null, (evt2) -> {
                            new HistoriqueForm(previous).showBack();
                       
                       
                        });
                         HistoriqueDetails.show();
                    });
                  
                }
               
                
                Image img10 = theme.getImage("img16.png");
                 Button  supprimer  = new Button(img10);
                 supprimer.setUIID("label");
                 Container c1 = new Container(BoxLayout.xRight());
                c1.add(supprimer);
                add(c1);
                //supprimer.setUIID("label");
                 supprimer.addActionListener((evt) -> {
                    
                        try {
                            sh.supprimerHistorique();
                            Dialog.show("Historique", "Historique vidé avec succès", new Command("OK"));
                            
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                       
                    });
                 //c1.refreshTheme();
                 
                //c1.add(supprimer);
               
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    
          
       
                
 // this.addAll(c1);
 
        
}
}