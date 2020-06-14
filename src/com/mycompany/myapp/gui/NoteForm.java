/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.service.ServiceFavoris;
import com.mycompany.myapp.service.ServiceNote;

/**
 *
 * @author Baleina
 */
public class NoteForm extends Form{
    Form current;
    public NoteForm(Form previous) {
        current = this;
        this.setTitle("Ajouter une note");
        this.setLayout(BoxLayout.yCenter());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        Container c1 = new Container(BoxLayout.xCenter());
        Label l = new Label("Ajouter votre note");
        c1.add(l);
        Slider s = new Slider();
        s.setMinValue(0);
        s.setMaxValue(10);
        s.setEditable(true);
        s.addDataChangedListener((t,i)->{
            l.setText("vous avez attribuer   "+s.getProgress()+ "  comme note");
        });
        
        Button b = new Button("Valider");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
     
                   try{
                       Note n = new Note(s.getProgress());
                       if(ServiceNote.getInstance().addNote(n))
                           Dialog.show("Success", "Connection accepted", new Command("OK"));
                       else
                       {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       }
                   }catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
               }
            }
            
      
        
            
        });
        
        this.addAll(c1,s,b);
}              
}
