/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Actualite;

/**
 *
 * @author Baleina
 */
public class ContenuActualiteForm extends Form{
    Form current;
    private Resources theme;
    
    public ContenuActualiteForm(Form previous, Actualite a)
    {
        current = this;
        theme = UIManager.initFirstTheme("/theme");
        
         SpanLabel sp1 = new SpanLabel(a.getContenu());
                sp1.getAllStyles().setFgColor(986895);
                
                    Image img = theme.getImage("img3.png");
                    Image img2= theme.getImage("img5.png");
                    Image img3= theme.getImage("img6.png");
                    Button b =new Button(img);
                    Button b1 =new Button(img2);
                    Button b2 =new Button(img3);
                    b.setUIID("label");
                    b2.setUIID("label");
                    b1.setUIID("label");
                    b1.getAllStyles().setBgColor(1054704);
                    b.getAllStyles().setBgColor(1054704);
                    b2.getAllStyles().setBgColor(1054704);
                    
                     Image contenu = theme.getImage("img8.png");
                     Container container1 = new Container(BoxLayout.yLast());
                     container1.add(contenu);
                   
                     b.addActionListener((evt1) -> {
                         new AjouterFavorisForm(previous, a);
                    });
                     
                    b1.addActionListener((evt2) -> {
                        new AjouterNoteForm(previous, a.getIdActualite());
                    });
                    b2.addActionListener((evt3) -> {
                       //new CommentaireForm(previous).show();
                    new CommentForm(previous, a.getIdActualite());
                    });
                    
                    
                    
                    Container c1 = new Container(BoxLayout.xCenter());
                    c1.addAll(b,b1,b2);
                    c1.getStyle().setBgColor(1054704);
                    
                    c1.getStyle().setBgTransparency(255);
                    c1.getAllStyles().setAlignment(CENTER);
                
                this.addAll(container1,sp1,c1);
                
//                contenuActualite.getToolbar().addCommandToLeftBar("Retour", null, (evt1) -> {
//                    
//                });
                this.show();
                   this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                           new ListActualiteForm(previous).show();
                                
                   });
  
    }
}
