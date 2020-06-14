/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;

/**
 *
 * @author DevelopAndroid
 */
public class showAboutFormation extends Form{
Form current;
    
        public showAboutFormation(Form atras) {
            current = this;
        try {
           // Form addformation = new Form("A propos Formation");
           // addformation.getToolbar().setBackCommand("", e -> atras.showBack());
          //current.getToolbar().setBackCommand("retour",(ev3)->{
             //show();
         //});
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> atras.showBack());
            Tabs tabs = new Tabs();
            // Tabs tabs1 = new Tabs();
            Style s = UIManager.getInstance().getComponentStyle("Tab");
            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_PALETTE, s);
            FontImage mus = FontImage.createMaterial(FontImage.MATERIAL_MUSIC_NOTE, s);
            FontImage musi = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
            ImageViewer iv = new ImageViewer(Image.createImage("/mus.jpg"));
            iv.setImageInitialPosition(ImageViewer.IMAGE_FILL);
            ImageViewer ivtre = new ImageViewer(Image.createImage("/moi.jpg"));
            // ivtre.setImageInitialPosition(ImageViewer.IMAGE_FILL);
            tabs.addTab("Musique", mus, iv);
            tabs.addTab("Peinture", icon, ivtre);
            // tabs.addTab("Formation Peinture", mus, ivtre);
            tabs.addTab("voir plus", musi, new TextArea("an art of sound in time that expresses ideas and "
                    + "the tones or sounds employed, occurring in single line (melody) or multiple .\n"
                    + "musical work or compositions for singing or playing.\n"
                    + "the written or printed score of a musical composition.\n"
                    + "such scores collectively.\n"
                    + "any sweet, pleasing, or harmonious sounds or sound:\n"
                    + "the music of the waves."));

            // tabs.addTab("Formation Musique", icon, ivtre);
            // tabs.addTab("fffff",iv,mus,new Label("fffff"));
            
            current.setLayout(new BorderLayout());
            
            current.add(BorderLayout.CENTER, tabs);

            current.show();
        } catch (IOException ex) {

        }
 
    }
    
}
