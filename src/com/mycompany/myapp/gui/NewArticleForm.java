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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.service.ServiceArticle;
import com.mycompany.myapp.service.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author Baleina
 */
public class NewArticleForm extends Form{
     private Resources theme;
     
    public NewArticleForm() {
      theme = UIManager.initFirstTheme("/theme");  
       this.setTitle("Nouvel article");
       this.setLayout(BoxLayout.yCenter());
       
        Image img13 = theme.getImage("img13.png");
        Container img = new Container(BoxLayout.y());
        img.add(img13);
        this.add(img);
       Label titre = new Label("Titre");
       TextField titreS = new TextField("");
       Label contenu = new Label("Contenu");
       TextArea contenuS = new TextArea("", 5, 20);
       contenuS.getAllStyles().setBgColor(14803425);
       titreS.getAllStyles().setBgColor(14803425);
       
       Button valider = new Button("valider");
        Container c = new Container(BoxLayout.xCenter());
        Container c1 = new Container(BoxLayout.xCenter());
        c.add(titre);
        c1.add(contenu);
      valider.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                 if((titreS.getText().length()==0 || contenuS.getText().length() == 0))
                                   {
                                       Dialog.show("Alert", "Veuillez remplir les champs", new Command("OK"));
                                   }
                 else if(titreS.getText().length() < 10 || contenuS.getText().length() < 20 )
                 {
                      Dialog.show("Alert", "longueur de texte minimum : 10 et 20", new Command("OK"));
                 }

                                   else
                                   {
                                       try{
                                           Article a = new Article();
                                           a.setTitre(titreS.getText());
                                           a.setContenu(contenuS.getText());
                                          
                                           if(ServiceArticle.getInstance().ajouterArticle(a))
                                               Dialog.show("Succès !", "Article ajouté avec succès", new Command("OK"));
                                           else
                                           {
                                                Dialog.show("ERROR", "Server error", new Command("OK"));
                                           }
                                       }catch (NumberFormatException e) {
                                            Dialog.show("ERROR", "", new Command("OK"));
                                   }
                                }
 titreS.setText("");
      contenuS.setText("");
           }
           
      });
  
      
     this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                                new ListArticleForm(this).show();
               
               } );
      
       addAll(c,titreS,c1,contenuS,valider);
       
    }
    
}
