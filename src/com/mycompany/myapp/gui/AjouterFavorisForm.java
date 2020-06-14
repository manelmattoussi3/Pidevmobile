/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.service.ServiceFavoris;
import java.io.IOException;

/**
 *
 * @author Baleina
 */
public class AjouterFavorisForm extends Form{
      Form current;
    private Resources theme;
    
    public AjouterFavorisForm(Form previous, Actualite a)
    {
        current = this;
        theme = UIManager.initFirstTheme("/theme");
        
        ServiceFavoris sf = new ServiceFavoris();
                         Container containerFavoris = new Container(BoxLayout.xCenter());
                         
                          Image imageFavoris = theme.getImage("img10.jpg"); 
                         
                         Label ltitre = new Label("Ajouter un titre");
                         
                        containerFavoris.add(ltitre);
                        TextField titre = new TextField("", "Ajouter un titre");

                        Button validerFavoris = new Button("Valider");

                       validerFavoris.addActionListener((evt2) -> {
                           if(titre.getText().length() == 0)
                           {
                               Dialog.show("Alerte", "veuillez remplir tous les champs", new Command("OK"));
                           }
                           
                           else if(titre.getText().length() < 5 )
                 {
                      Dialog.show("Alert", "longueur de texte minimum 5", new Command("OK"));
                 }
                           
                           else
                           {
                              Favoris f = new Favoris();
                            f.setTitre(titre.getText());
                            f.setContenu(a.getContenu());
                             try {
                                 
                                 sf.ajouterFavoris(f);
                                 Dialog.show("Succès", "Favoris ajouté avec succès", new Command("OK"));
                             } catch (IOException ex) {
                                 System.out.println(ex.getMessage());
                             } 
                           }
                           
                           titre.setText("");
                           
                       });
                       Container Cfavoris = new Container(BoxLayout.yCenter());
                         Cfavoris.addAll(containerFavoris,titre,validerFavoris);
                         
                         this.add(imageFavoris);
                         this.addAll(Cfavoris);
                         
//                          favoris.getToolbar().addCommandToLeftBar("Retour", null, (evt2) -> {
//                            new ListActualiteForm(previous).show();
//                  
//                });
                          
                             this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt2 -> {
                             this.removeAll();
                               new ContenuActualiteForm(previous, a);
                                
               } );

                         this.show();
    }
}
