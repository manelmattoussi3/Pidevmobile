/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Actualite;

/**
 *
 * @author Baleina
 */
public class HomeForm extends Form{
    Form current;
    private Resources theme;

    public HomeForm() {
        current = this;
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        
       theme = UIManager.initFirstTheme("/theme");
//       EncodedImage placeHolderImage = EncodedImage.createFromImage(theme.getImage("img8.png"), false);
//         
//        URLImage urlimage = URLImage.createToStorage(placeHolderImage, "img8.png", "http://127.0.0.10/img/img8.png");
//        //composant graphique pour pour image
//        ImageViewer img = new ImageViewer();
//       
//        
//        img.setImage(urlimage);
        Image img = theme.getImage("img8.jpg");
       Label l = new Label(img);
       
        
        setLayout(BoxLayout.yCenter());
        Button actu = new Button("Consulter les Actualités");
        Button forum = new Button("Aller sur notre espace Forum");
        Button favoris = new Button("Consulter vos favoris");
        Button historique = new Button("Consulter votre historique");
//        actu.setUIID("label");
//        forum.setUIID("label");
//        favoris.setUIID("label");
//        historique.setUIID("label");
        
        actu.addActionListener(evt -> new ListActualiteForm(current).show());
        forum.addActionListener((evt) -> new ListArticleForm(current).show());
        favoris.addActionListener((evt) ->  new FavorisForm(current).show());
        historique.addActionListener((evt) ->  new HistoriqueForm(current).show());
        
        
        
        addAll(l,actu,forum,favoris,historique);
        
        
    }
    
    
}
