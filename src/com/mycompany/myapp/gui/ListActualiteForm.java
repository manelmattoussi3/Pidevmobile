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
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.CommentaireForum;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Historique;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.service.ServiceActualite;
import com.mycompany.myapp.service.ServiceArticle;
import com.mycompany.myapp.service.ServiceCommentaire;
import com.mycompany.myapp.service.ServiceCommentaireForum;
import com.mycompany.myapp.service.ServiceFavoris;
import com.mycompany.myapp.service.ServiceHistorique;
import com.mycompany.myapp.service.ServiceNote;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Baleina
 */
public class ListActualiteForm extends Form{
private Resources theme;
Form current;
 int valeur1;
 Label sp4;
 
    public ListActualiteForm(Form previous) {
         theme = UIManager.initFirstTheme("/theme");
        current = this;
        setTitle("Actualités");
        Form contenuActualite = new Form("Actualités");
        Form note = new Form("Noter l'actualité");
        Form favoris = new Form("Ajouter des Favoris");
        Form commentaire = new Form("Ajouter votre commentaire");
        contenuActualite.setLayout(BoxLayout.y());
        ServiceHistorique sh = new ServiceHistorique();
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        Container c1 = new Container(BoxLayout.xCenter());
       
        
        Container container = new Container(BoxLayout.xCenter());
        Container container1 = new Container(BoxLayout.yLast());
        
        Container content2 = new Container(BoxLayout.yCenter());
        Image img9 = theme.getImage("img2.png");
        container.add(img9);
        add(container);
        for(Actualite a : ServiceActualite.getInstance().getAllActualite())
        {
            Label article = new Label(a.getTitre());
            article.getAllStyles().setFgColor(4934475);
            content2.add(article);
           
            System.out.println(a.getIdActualite());
           
            
            article.addPointerPressedListener((evt) -> {
                Date d = new Date();
                
                Historique h = new Historique();
                h.setTitre(a.getTitre());
                h.setContenu(a.getContenu());
                h.setDateAjout(d.toString());
                try {
                    sh.ajouterHistorique(h);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                a.getIdActualite(); int valeur; valeur = a.getIdActualite();
                System.out.println(valeur);
                Label sp0 = new Label(a.getTitre());
                SpanLabel sp1 = new SpanLabel(a.getContenu());
                sp1.getAllStyles().setFgColor(986895);
                sp0.getAllStyles().setFgColor(0);
                
                    Image img = theme.getImage("img3.png");
                    Image img2= theme.getImage("img5.png");
                    Image img3= theme.getImage("img6.png");
                    Button b =new Button(img);
                    Button b1 =new Button(img2);
                    Button b2 =new Button(img3);
                    b.setUIID("label");
                    b2.setUIID("label");
                    b1.setUIID("label");
                   
                    Button consulterCommentaire = new Button("Cosulter Commentaire");
                    
                    consulterCommentaire.addActionListener((evt5) -> {
                        Form commentaireList = new Form("Liste de commentaire");
                        
                         Container labelb = new Container(BoxLayout.yBottom());
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
                         
                         commentaireList.add(labelb);
                         
                          commentaireList.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt20 -> {
                               
                                contenuActualite.show();
                                
               } );
                          commentaireList.show();
                    });
                    
                    
                    
                     
                   
                     b.addActionListener((evt1) -> {
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
                         
                         favoris.add(imageFavoris);
                         favoris.addAll(Cfavoris);
                         
//                          favoris.getToolbar().addCommandToLeftBar("Retour", null, (evt2) -> {
//                            new ListActualiteForm(previous).show();
//                  
//                });
                          
                             favoris.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt2 -> {
                               favoris.removeAll();
                                contenuActualite.show();
                                
               } );

                         favoris.show();
                    });
                     
                    b1.addActionListener((evt2) -> {
                        a.getIdActualite();  valeur1 = a.getIdActualite();
                        System.out.println(valeur1);
                       //new NoteForm(previous).show();
                               Image imageNote = theme.getImage("img12.jpg"); 
                               Container imgNote = new Container(BoxLayout.y());
                               imgNote.add(imageNote);
                               note.add(imgNote);
                               Container c2 = new Container(BoxLayout.xCenter());
                               
                                Label l = new Label("Ajouter votre note");
                                
                                c2.add(l);
                                Slider s = new Slider();
                                s.setMinValue(0);
                                s.setMaxValue(10);
                                s.setEditable(true);
                                s.addDataChangedListener((t,i)->{
                                    l.setText("vous avez attribuer   "+s.getProgress()+ "  comme note");
                                });
        
                                Button b4 = new Button("Valider");
                                b4.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                           try{
                                               Note n = new Note();
                                               n.setActualite_id(valeur1);
                                               n.setValeur(s.getProgress());
                                               if(ServiceNote.getInstance().addNote(n))
                                                   Dialog.show("Succès", "Note ajoutée avec succès", new Command("OK"));
                                               
                                           }catch (NumberFormatException e) {
                                                Dialog.show("ERROR", "Note must be a number", new Command("OK"));
                                       }
                                    }

                                    });
//                                
                                note.addAll(c2,s,b4);
//                                note.getToolbar().addCommandToLeftBar("Retour", null, (evt1) -> {
//                            new ListActualiteForm(previous).show();
//                  
//                });
                                
                                   note.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                                note.removeAll();
                                contenuActualite.show();
                                
               } );
                                note.setLayout(BoxLayout.yCenter());
                                note.show();
                    });
                    b2.addActionListener((evt3) -> {
                       //new CommentaireForm(previous).show();
                  
                       
                       Label c = new Label("Ajouter votre commentaire");
          
                        TextArea commment = new TextArea("", 5, 20);
                        commment.getAllStyles().setBgColor(14803425);

                        Button valider = new Button("Commenter");
                         Container content = new Container(BoxLayout.xCenter());
                        content.add(c);
                        Container content1 = new Container(BoxLayout.yCenter());
                        content1.addAll(commment,valider);
                        
                         Image imageComment = theme.getImage("img13.jpg"); 
                         commentaire.add(imageComment);
                         
                          commentaire.addAll(content,content1);
                          
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
                                              
                                              // commentaire.removeComponent(labelb);
                                               //commentaire.add(labelb);
                                          
                                           }
                                                else
                                           {
                                                Dialog.show("ERROR", "Server error", new Command("OK"));
                                           }
                                       }catch (NumberFormatException e) {
                                            Dialog.show("ERROR", "", new Command("OK"));
                                   }
                                }

                                    commment.setText("");
                                    
           }
                               
                             
   
        });                 
                            
                                commentaire.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                                commentaire.removeAll();
                                contenuActualite.show();
                                
               } );
//                            commentaire.getToolbar().addCommandToLeftBar("Retour", null, (evt1) -> {
//                                commentaire.removeAll();
//                                contenuActualite.show();
//                                
//                });
//              
                            commentaire.show();
           
                    });
                    
                    
                    
                    c1.addAll(b,b1,b2);
                  
                    
                    c1.getStyle().setBgTransparency(255);
                    c1.getAllStyles().setAlignment(CENTER);
                
                contenuActualite.addAll(container1,sp0,sp1,c1, consulterCommentaire);
                
//                contenuActualite.getToolbar().addCommandToLeftBar("Retour", null, (evt1) -> {
//                    
//                });
                
                   contenuActualite.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                           new ListActualiteForm(previous).show();
                                
               } );
                
                
              
               
               contenuActualite.show();
            });
           
             
        }
        add(content2);
    }
    
}
