/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.Service.ServiceFormation;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;


/**
 *
 * @author DevelopAndroid
 */
public class AccueilForm extends Form{
    Form home;  
     
    public AccueilForm() {
        
  
        try {
            home = this;
            setTitle("      Tunisian Got Talent");
            setLayout(BoxLayout.y());
            ImageViewer iv = new ImageViewer(Image.createImage("/5e58086b7ee04.jpeg"));
            
            iv.setImageInitialPosition(ImageViewer.IMAGE_FIT);
            
            //tabs.addTab("Tunisian Got Talent", mus, iv);
            
            
            Button btnAccueil = new Button("Accueil");
            
            btnAccueil.addActionListener((evt) -> {
                
              //  new Home(home).show();
            });
            Button btnActualite = new Button("Actualité ");
            
            btnAccueil.addActionListener((evt) -> {
                
                 //new Home(home).show();
            });
            Button btnQuiz = new Button("Quiz");
            
            btnAccueil.addActionListener((evt) -> {
                
               //  new Home(home).show();
            });
            Button btnpub = new Button("Publication");
            
            btnAccueil.addActionListener((evt) -> {
                
                // new Home(home).show();
            });
            Button btnformation = new Button("Formation ");
            
            btnAccueil.addActionListener((evt) -> {
                
                 new Home(home).show();
            });
            Button btnspons = new Button("Sponsoring ");
            
            btnAccueil.addActionListener((evt) -> {
                
             //  new Home(home).show();
            });
            /*  Container c1 =new Container(BoxLayout.y());
            c1.add(btnAccueil);
            Container c2 =new Container(BoxLayout.y());
            c1.add(btnActualite);
            */
            addAll(iv,btnAccueil,btnActualite,btnQuiz,btnpub ,btnformation,btnspons);
            
            
            show();
        } catch (IOException ex) {
            
        }
        

    }
    }

