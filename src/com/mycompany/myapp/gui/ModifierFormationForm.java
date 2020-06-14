/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.Service.ServiceCoach;
import com.mycompany.myapp.Service.ServiceFormation;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.Formation;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;

/**
 *
 * @author DevelopAndroid
 */
public class ModifierFormationForm extends Form{
Form current;
    public ModifierFormationForm(Form previous,Formation formation) {
        
        current = this;
         setTitle("Modifier Formation ");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
       TextField categorie = new TextField("", "categorie");
        Picker date_debut = new Picker();
        Picker date_fin = new Picker();
        TextField tfnbrplace = new TextField("", "nbrPlace");
        
      TextField description = new TextField("", "description");
        description.setText(formation.getDescription());
        tfnbrplace.setText(formation.getNbr_Place());
       categorie.setText(formation.getCategorie());
        ArrayList<Coach> listCoach = ServiceCoach.getInstance().getCoach();
        ComboBox<String> comboCoach = new ComboBox<>();
        for (Coach coach : listCoach) {
            comboCoach.addItem(coach.getNom());
        }
       
       addAll(categorie,tfnbrplace,date_debut,date_fin,description);
       add(comboCoach);
      
      
         Button btnModifier = new Button("Modifier Formation");
         btnModifier.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent evt) {
                
                if ((description.getText().length() == 0) || (tfnbrplace.getText().length() == 0)|| (categorie.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", "ok", null);
                } else {
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = formater.format(date_debut.getDate());
                    String dateFin = formater.format(date_fin.getDate());
                    Formation newFormation = new Formation(formation.getId() ,description.getText(), dateDebut, dateFin, tfnbrplace.getText(), categorie.getText(),formation.getCoach_id());
                    ServiceFormation.getInstance().updateFormation(newFormation);
                    new showListFormation(previous).showBack();
                }
            }
        });
//addAll(description,tfnbrplace,date_debut,date_fin,categorie,btnModifier);
       add(btnModifier);
       // current.show();
        
  }
        
    }
    

