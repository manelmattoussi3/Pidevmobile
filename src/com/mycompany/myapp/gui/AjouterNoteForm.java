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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Actualite;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.service.ServiceNote;

/**
 *
 * @author Baleina
 */
public class AjouterNoteForm extends Form{
    Form current;
    private Resources theme;
    
    public AjouterNoteForm(Form previous, int a)
    {
        current = this;
        theme = UIManager.initFirstTheme("/theme");
        setLayout(BoxLayout.yCenter());
        
      //  a.getIdActualite();  valeur1 = a.getIdActualite();
                   //     System.out.println(valeur1);
                       //new NoteForm(previous).show();
                               Image imageNote = theme.getImage("img12.jpg"); 
                               Container imgNote = new Container(BoxLayout.y());
                               imgNote.add(imageNote);
                               this.add(imgNote);
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
                                               n.setActualite_id(a);
                                               n.setValeur(s.getProgress());
                                               if(ServiceNote.getInstance().addNote(n))
                                                   Dialog.show("Succès", "Note ajoutée avec succès", new Command("OK"));
                                               
                                           }catch (NumberFormatException e) {
                                                Dialog.show("ERROR", "Note must be a number", new Command("OK"));
                                       }
                                    }

                                    });
//                                
                                this.addAll(c2,s,b4);
//                                note.getToolbar().addCommandToLeftBar("Retour", null, (evt1) -> {
//                            new ListActualiteForm(previous).show();
//                  
//                });
                                
                                   this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt1 -> {
                                    
                                
               } );
                                
                                this.show();
    }
}
