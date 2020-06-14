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
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.service.ServiceCommentaire;
import com.mycompany.myapp.service.ServiceFavoris;

/**
 *
 * @author Baleina
 */
public class CommentaireForm extends Form{
Form current;
    public CommentaireForm(Form previous) {
       current = this;
        setTitle("Ajouter un commentaire");
        this.setLayout(BoxLayout.yCenter());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        Container c1 = new Container(BoxLayout.xCenter());
      
        Label c = new Label("Ajouter votre commentaire");
          c1.add(c);
        TextArea commment = new TextArea("Commentaire", 10, 20);
        
        Button valider = new Button("Commenter");
        
        valider.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent evt) {
               if((commment.getText().length()==0))
               {
                   Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
               }
               
               else
               {
                   try{
                       Commentaire c = new Commentaire(commment.getText());
                       if(ServiceCommentaire.getInstance().ajouterCommentaire(c))
                           Dialog.show("Success", "Connection accepted", new Command("OK"));
                       else
                       {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       }
                   }catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
               }
            }
            
      
           }
           
        
        });
        
        this.addAll(c1,commment,valider);
        
    }
    
}
    

