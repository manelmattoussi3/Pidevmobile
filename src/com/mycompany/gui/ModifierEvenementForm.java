/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import com.mycompany.services.EvenementService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Khawla
 */
public class ModifierEvenementForm  extends BaseForm{

        private static String i;
        String path1;
        String fileNameServer;
        String nameFile;
        Image iimg =null;
        NewsfeedForm n ;
        Form current;
public ModifierEvenementForm(Resources res , Evenement edit_ev ) throws ParseException {
        super(BoxLayout.y());
        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Modifier ");
        getContentPane().setScrollVisible(true);
        Image img = res.getImage("feedback.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
             
         Button likes = new Button();
        Style heartStyle = new Style(likes.getUnselectedStyle());
      //  heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
        
        likes.addActionListener(e -> {
            new NewsfeedForm(res).showBack();
        });
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(4,likes,
                                FlowLayout.encloseCenter(
                                        )
                        )
                )
        ));
        
       ButtonGroup barGroup = new ButtonGroup();
       RadioButton element = RadioButton.createToggle("Proposez votre événement", barGroup);
       element.setUIID("SelectBar");
       add(LayeredLayout.encloseIn(
           GridLayout.encloseIn(1, element)));
      TextField title = new TextField("");
      title.setText(edit_ev.getTitle());
      title.setUIID("LabelIcon");
      addStringValue("Titre",title);
   
/*    Picker dateDebut = new Picker();
    dateDebut.setType(Display.PICKER_TYPE_DATE);
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");

    dateDebut.setDate(sdf.parse(edit_ev.getDatedebut()));
        addStringValue("Date", dateDebut);
        
    }*/

        TextField dateDebut = new TextField("");
        dateDebut.setText(edit_ev.getDatedebut());
        dateDebut.setUIID("LabelIcon");
        addStringValue("Date debut",dateDebut);
        TextField dateFin = new TextField("");
        dateFin.setText(edit_ev.getDatefin());
        dateFin.setUIID("LabelIcon");
        addStringValue("Date fin",dateFin);
        TextField desc = new TextField("");
        desc.setText(edit_ev.getDescription());
        desc.setUIID("LabelIcon");
        addStringValue("Description",desc);
        TextField nbrMin = new TextField("");
        nbrMin.setText(String.valueOf(edit_ev.getNombreMinParticipants()));
        nbrMin.setUIID("LabelIcon");
        addStringValue("Nbr Min",nbrMin);
        TextField nbrMax = new TextField("");
        nbrMax.setText(String.valueOf(edit_ev.getNombreMaxParticipants()));
        nbrMax.setUIID("LabelIcon");
        addStringValue("Nbr Max",nbrMax);
        ComboBox<String> cat;
        Vector<String> vector = new Vector();
           vector.add("Musique");
           vector.add("Dance");
           vector.add("Peinture");
        cat = new ComboBox<>(vector);
        cat.setUIID("TextFieldBlack");
        addStringValue("Categorie", cat);
        TextField prix = new TextField("");
        prix.setText(String.valueOf(edit_ev.getPrix()));
        prix.setUIID("LabelIcon");
        addStringValue("Prix",prix);
        //  TextField path = new TextField("");
        Button foto = new Button("Parcourir");
        addStringValue("Parcourir votre image...", foto);
        TextField path = new TextField("", "Path");
        path.setUIID("TextFieldBlack");
        addStringValue("Path", path);
      
      //*********************************open Gallery**************************//

       foto.addActionListener(ee -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                         path1 = (String) ev.getSource();
                        try {
                            iimg = Image.createImage(FileSystemStorage.getInstance().openInputStream(path1));
                            path.setText(path1.substring(7));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                }

            }, Display.GALLERY_IMAGE);
        });
       
       
        Button modifier = new Button("Modifier");
        modifier.setUIID("Edit");
        addStringValue("", modifier);
        modifier.addActionListener((e)-> {
        String name=System.currentTimeMillis()+".png";
        FileUploader fu = new FileUploader("localhost:8080/framework5/web/uploads/");
        try{
                fileNameServer = fu.upload(path.getText());
       //*************************************************************taw nfsrouh**********************//
       FileSystemStorage.getInstance().openOutputStream(FileSystemStorage.getInstance().getAppHomePath()+ name).write(0);
       path1 = fileNameServer;
       nameFile= path1.substring(path1.lastIndexOf('/') + 1);

       }catch(Exception ex ) {
           ex.printStackTrace();
       }
       
         Evenement newEvent = new Evenement(edit_ev.getId(),title.getText(),
         desc.getText(),cat.getSelectedItem(),dateDebut.getText(),dateFin.getText(),Integer.parseInt(nbrMin.getText()),
         Integer.parseInt(nbrMax.getText()),0,path.getText(),Integer.parseInt(prix.getText()));
         EvenementService.getInstance().modifierEvenement(newEvent,path1);
         element.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     
                     new NewsfeedForm(res).show();
                 }
             });
        
       
        });
    }
    
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
        add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
     
 
    
}
