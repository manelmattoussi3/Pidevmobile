/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.Service.ServiceCoach;
import com.mycompany.myapp.entities.Coach;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author DevelopAndroid
 */
public class showCoach extends Form{
Form liste;
    public showCoach(Form atras) {
        liste=this;
        setTitle("Liste Coachs");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> atras.showBack());
      //  Form liste = new Form("Liste Coachs", BoxLayout.y());
       ServiceCoach sp = new ServiceCoach ();
        ArrayList<Coach > listeCoach  = new ArrayList<>();
            
            listeCoach = sp.getCoach();
            for(Coach P:listeCoach){
                Container c = new Container(BoxLayout.y());
                Container c1 = new Container();
                Container c2 = new Container();
                Label nom = new Label("Nom:");
                Label lnom = new Label(P.getNom());
                Label prenom = new Label("Prenom:");
                
                Label lprenom = new Label(P.getPrenom());
                c1.addAll(nom,lnom);
                c2.addAll(prenom,lprenom);
                c.addAll(c1,c2);
                liste.add(c);
                liste.show();
    }
    
}
}