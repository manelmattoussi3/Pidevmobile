/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.service.ServiceCommentaire;

/**
 *
 * @author Baleina
 */
public class CommentForm extends Form{
    Form current;
    private Resources theme;
    public CommentForm(Form previous, int valeur)
    {
        theme = UIManager.initFirstTheme("/theme");
        current = this;
        
        Container labelb = new Container(BoxLayout.yBottom());
                       
                    
                       Label c = new Label("Ajouter votre commentaire");
          
                        TextArea commment = new TextArea("", 5, 20);
                        commment.getAllStyles().setBgColor(14803425);

                        Button valider = new Button("Commenter");
                         Container content = new Container(BoxLayout.xCenter());
                        content.add(c);
                        Container content1 = new Container(BoxLayout.yCenter());
                        content1.addAll(commment,valider);
                        
                         Image imageComment = theme.getImage("img13.jpg"); 
                         add(imageComment);
                         
                             for (Commentaire c2 : ServiceCommentaire.getInstance().getAllCommentaire(valeur))
                        {
                                 if(valeur == c2.getActualite_id())
                                 {
                                      Label sp4 = new Label(c2.getDateAjout());
                                      SpanLabel  sp3 = new SpanLabel(c2.getContenu());
                                       
                                       sp4.getAllStyles().setFgColor(986895);
                                      
                                      labelb.addAll(sp4,sp3);
                                     
                                 }

                        }
                          
                            valider.addActionListener(new ActionListener(){
                               @Override
                               public void actionPerformed(ActionEvent evt) {
                                   if((commment.getText().length()==0))
                                   {
                                       Dialog.show("Alerte", "veuillez remplir tous les champs", new Command("OK"));
                                       
                                   }
                                   
                                   else if(commment.getText().length() < 20 )
                 {
                      Dialog.show("Alert", "longueur de texte minimum : 20", new Command("OK"));
                 }

                                   else
                                   {
                                       try{
                                           Commentaire c = new Commentaire();
                                           c.setActualite_id(valeur);
                                           c.setContenu(commment.getText());
                                           if(ServiceCommentaire.getInstance().ajouterCommentaire(c))
                                           {
                                              Dialog.show("Succès", "Commentaire ajouté avec succès", new Command("OK"));
                                            
                                              
                                           }
                                                else
                                           {
                                                Dialog.show("ERROR", "Server error", new Command("OK"));
                                                
                                           }
                                          // new CommentForm(previous, valeur);
                                         
                                       }catch (NumberFormatException e) {
                                            Dialog.show("ERROR", "", new Command("OK"));
                                   }
                                }
                                   
                                    commment.setText("");
                  
           }
                               
                             
   
        });      
                        
            addAll(content,content1,labelb);                
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                                new ListActualiteForm(previous).show();
               
               } );
           
         this.show();
                                 
    }
}
