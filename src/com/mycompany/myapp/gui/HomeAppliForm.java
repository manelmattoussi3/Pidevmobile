/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Baleina
 */
public class HomeAppliForm extends Form{
    private Resources theme;
    public HomeAppliForm()
    {
        setLayout(BoxLayout.y());
        
        Label ltitre = new Label("Bienvenu(e) dans notre application !");
        
        add(ltitre);
        
        Toolbar tb = this.getToolbar();
         theme = UIManager.initFirstTheme("/theme");
         Image icon = theme.getImage("acc.jpg");
         Container topbar= BorderLayout.east(new Label(icon));
         topbar.add(BorderLayout.SOUTH, new Label("Tunisia Got Talent", "SlideMenuTagline"));
         topbar.setUIID("SlideCommand");
         tb.addComponentToSideMenu(topbar);
         
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {});
        tb.addMaterialCommandToSideMenu("HomeActualite", FontImage.MATERIAL_HOME, (evt) -> {new HomeForm().show();});
        tb.addMaterialCommandToSideMenu("HomeSamar", FontImage.MATERIAL_HOME, (evt) -> {new Accueil().show();
                });
        tb.addMaterialCommandToSideMenu("HomeManel", FontImage.MATERIAL_HOME, (evt) -> {new Home(this).show();});
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {});
    }
    
}
