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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.service.ServiceFavoris;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Baleina
 */
public class FavorisForm extends Form{
    Form  current;
    private Resources theme;
    public FavorisForm(Form previous) {
        theme = UIManager.initFirstTheme("/theme");
        current = this;
        this.setTitle("Ajouter aux Favoris");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
       
        Form favorisDetails = new Form("Détails Actu");
        
       setLayout(BoxLayout.y());
       this.getAllStyles().setBgColor(16777215);
        ArrayList<Favoris> listeFavoris = new ArrayList<>();
        ServiceFavoris sf = new ServiceFavoris();
            try {
                listeFavoris = sf.getListFavoris();
                
                for(Favoris f : listeFavoris)
                {
                    //Container c = new Container(BoxLayout.x());
                    Label ltitre = new Label(f.getTitre());
                   
                    add(ltitre);
                   // Label lcontenu = new Label(f.getContenu());
                    
                    
                    ltitre.addPointerPressedListener((evt) -> {
                        SpanLabel sp = new SpanLabel();
                        sp.setText(f.getContenu());
                        favorisDetails.add(sp);
                        favorisDetails.getToolbar().addCommandToLeftBar("Back", null, (evt1) -> {
                            new FavorisForm(previous).show();
                        });
                        favorisDetails.show();
                    });
                  
                }
                
                 
                 
                 Image img10 = theme.getImage("img16.png");
                 Button  supprimer  = new Button(img10);
                 supprimer.setUIID("label");
                 Container c1 =new Container(BoxLayout.xRight());
                 c1.add(supprimer);
                 add(c1);
                  //supprimer.setUIID("label");
                 
                 supprimer.addActionListener((evt) -> {
                        try {
                            sf.supprimerFavoris();
                           Dialog.show("Favoris", "Liste de favoris vidée avec succès", new Command("OK"));
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                       
                    });
                
               
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
  
    }
}
