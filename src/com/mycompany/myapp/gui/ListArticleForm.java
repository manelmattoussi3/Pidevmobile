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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.CommentaireForum;
import com.mycompany.myapp.service.ServiceActualite;
import com.mycompany.myapp.service.ServiceArticle;
import com.mycompany.myapp.service.ServiceCommentaire;
import com.mycompany.myapp.service.ServiceCommentaireForum;

/**
 *
 * @author Baleina
 */
public class ListArticleForm extends Form{
    private Resources theme;
    Form current;
    public ListArticleForm(Form previous) {
        current = this;
        theme = UIManager.initFirstTheme("/theme");
        setTitle("Sujets de discussion");
       this.getAllStyles().setBgColor(16777215);
        Form contenuArticle =new Form("Contenu de nos articles");
        contenuArticle.setLayout(BoxLayout.y());
        
        Container c = new Container(BoxLayout.xCenter());
       
      //  SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceArticle.getInstance().getAllArticle().toString());
       //add(sp);
       Image img10 = theme.getImage("img15.png");
       Button newArticle = new Button(img10);
       newArticle.setUIID("label");
       add(newArticle);
       newArticle.addActionListener((evt) -> {
           new NewArticleForm().show();
           
       });
         
        for(Article a : ServiceArticle.getInstance().getAllArticle())
        {
            
            Label article = new Label(a.getTitre());
            article.getAllStyles().setFgColor(16777215);
            article.getAllStyles().setBgColor(7576688);
            article.getStyle().setBgTransparency(255);
            
            SpanLabel sp = new SpanLabel(a.getContenu());
           
            Container c4 = new Container();
           
              Container c3 = new Container(BoxLayout.y());
              c3.addAll(article,sp);
            
             
             
           
             int valeur = a.getId(); 
            article.addPointerPressedListener((evt) -> {
               
                System.out.println(valeur);
                SpanLabel sp1 = new SpanLabel(a.getTitre());
                SpanLabel sp2 = new SpanLabel(a.getContenu());
                  
//             SpanLabel sp3 = new SpanLabel();
//             sp3.removeAll();
//             sp3.setText(ServiceCommentaireForum.getInstance().getAllCommentaire(valeur).toString());
                Container c1 = new Container(BoxLayout.yBottom());
               
               for(CommentaireForum c2 : ServiceCommentaireForum.getInstance().getAllCommentaire(valeur))
              {
                       if(a.getId() == c2.getId())
                       {
                            SpanLabel  sp3 = new SpanLabel(c2.getContenu());
                            Label sp4  = new Label(c2.getDate_ajout());
                            sp4.getAllStyles().setFgColor(0);
                            System.out.println(c2.getDate_ajout());
                            
                            c1.addAll(sp4,sp3);
                       }
                  
                      System.out.println("----"+c2.getId());
                     
              }
              
               // Image img4 = theme.getImage("img6.png");
                //Button b = new Button(img4); 
                Label titre = new Label("Saisir votre commentaire");
                TextArea commentaire = new TextArea("", 5, 20);
                commentaire.getAllStyles().setBgColor(14803425);
                Button valider = new Button("Valider");
                
                valider.addActionListener(new ActionListener(){
                               @Override
                               public void actionPerformed(ActionEvent evt) {
                                   if((commentaire.getText().length()==0))
                                   {
                                       Dialog.show("Alerte", "veuillez remplir tous les champs", new Command("OK"));
                                   }
                                   
                                   else if(commentaire.getText().length() < 20 )
                 {
                      Dialog.show("Alert", "longueur de texte minimum : 20", new Command("OK"));
                 }

                                   else
                                   {
                                       try{
                                           CommentaireForum c = new CommentaireForum();
                                           c.setArticle_id(valeur);
                                           c.setContenu(commentaire.getText());
                                           if(ServiceCommentaireForum.getInstance().ajouterCommentaire(c))
                                           {
                                               Dialog.show("Succès", "Commentaire ajouté avec succès", new Command("OK"));
                                          
                                           }
                                                else
                                           {
                                                Dialog.show("ERROR", "Server error", new Command("OK"));
                                           }
                                       }catch (NumberFormatException e) {
                                            Dialog.show("ERROR", "", new Command("OK"));
                                   }
                                }

                                    commentaire.setText("");
                                    
           }
                               
                             
   
        });                 
                
//               contenuArticle.getToolbar().addCommandToLeftBar("Retour", null, (evt1) -> {
//                   
//                });
               
               contenuArticle.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                                new ListArticleForm(previous).show();
               
               } );
              
              //c.add(b);
               contenuArticle.addAll(c,titre,commentaire,valider,c1);  
               contenuArticle.show();
            });
            add(c3);
            c3.setLeadComponent(article);
         
        }
        
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                               new HomeForm().show();
               
               });
        
    }
    
}
