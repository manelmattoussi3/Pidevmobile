/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import com.mycompany.services.EvenementService;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import rest.file.uploader.tn.FileUploader;


/**
 *
 * @author Khawla
 */
public class AjoutEvenementForm extends BaseForm {
   private static final String MAPS_KEY = "AIzaSyBKXNneTVr8yaKCVD_sCEFj9CNCtcU85V8"; // Your maps key here
   String path1;   
   String nameFile;
   Image img = null;
   Form current;
   String fileNameServer ;
   int nbMax,nbMin;
   String md1,md;
   Label loc;
   //public String localisation;
    public AjoutEvenementForm(Resources res ,String localisation) throws Exception {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Tunisian Got Talent");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, spacer1, res.getImage("feedback.jpg"), "", "",res);
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Evenements", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajout", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));
        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
        //-----------------Ajout Evenement -------------------------//
        Evenement e = new Evenement();
        TextField titre = new TextField("", "Titre",20,TextField.ANY);
        titre.setUIID("TextFieldBlack");
        addStringValue("Titre", titre);
        TextField description = new TextField("", "Description",20,TextField.ANY);
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);
        //Map
        TextField path = new TextField("", "Path");
        path.setUIID("TextFieldBlack");
        addStringValue("Path", path);
        ComboBox<String> cat;
        Vector<String> vector = new Vector();
           vector.add("Musique");
           vector.add("Dance");
           vector.add("Peinture");
        cat = new ComboBox<>(vector);
        path.setUIID("TextFieldBlack");
        addStringValue("Categorie", cat);
        Picker datedebut = new Picker();
        datedebut.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        addStringValue("Date debut",datedebut);
        Date d = datedebut.getDate();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        md =sdf.format(d);
        System.out.println("here = "+md);
        datedebut.setText(md);
        Picker datefin = new Picker();
        datefin.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        addStringValue("Date fin",datefin);
        Date d1 = datefin.getDate();
        System.out.println(d1);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        md1=sdf1.format(d1);
        datefin.setText(md1);
        TextField min = new TextField("", "Nbr Min",10,TextField.ANY);
        min.setUIID("TextFieldBlack");
        addStringValue("Min", min);
        Container cnt = new Container(BoxLayout.y());
        TextField max = new TextField("", "NbrMax",10,TextField.ANY);
        max.setUIID("TextFieldBlack");
        addStringValue("Max", max);
        //.setUIID("TextFieldBlack");
        loc = new Label("Localisation :"+localisation);
        loc.setUIID("label");
    try{
        loc.addPointerPressedListener(map-> {
            try {
                new MapUIAdmin().start();
                System.out.println("hi");
            } catch (Exception ex) {
                System.out.println("ex =" +ex.getMessage());
            }
        });
        }catch(Exception exception) {
            System.out.println("ex =" +exception.getMessage());
                                    }
        
        cnt.add(loc);
        add(cnt);
        TextField prix = new TextField("", "Prix",20,TextField.NUMERIC);
        prix.setUIID("TextFieldBlack");
        addStringValue("Prix", prix);
        Button imgBtn = new Button("Parcourir");
        Button foto = new Button("Parcourir");
        addStringValue("Parcourir votre image...", foto);
        // ac.setUIID("PaddedLabel");
        //addStringValue("Localisation", ac);
        
        
        //************************************* open Gallery *************************//
foto.addActionListener(ee -> {
    Display.getInstance().openGallery(new ActionListener() {
@Override
    public void actionPerformed(ActionEvent ev) {
        if (ev != null && ev.getSource() != null) {
        path1 = (String) ev.getSource();
                try {
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path1));
                        path.setText(path1.substring(7));
                    } catch (Exception e) {
                                             System.out.println(e.getMessage());
                                          }

                    }
                }

            }, Display.GALLERY_IMAGE);
        });
       
    Button btnAjouter = new Button("Ajouter");
    addStringValue("", btnAjouter);
       btnAjouter.addActionListener((i)-> {
            
       String name=System.currentTimeMillis()+".png";
       FileUploader fu = new FileUploader("localhost:8080/pidev-integration/web/uploads/");
       try{
             if(path.getText().equals("" )){
           }
            else{
             fileNameServer = fu.upload(path.getText());
             FileSystemStorage.getInstance().openOutputStream(FileSystemStorage.getInstance().getAppHomePath()+ name).write(0);
             path1 = fileNameServer;
             nameFile= path1.substring(path1.lastIndexOf('/') + 1);

             }
       
        }catch(Exception ex ) {
           ex.printStackTrace();
       
       }
       
       /**********************************Controle de saisie****************************/
        boolean ck = true;
            if(titre.getText().equals("")) {
                Dialog.show("Erreur","Titre doit etre non vide","OK",null);
                ck=false;
            }
            if(description.getText().equals("")) {
                Dialog.show("Erreur","description doit etre non vide","OK",null);
                ck=false;
            }
            if(cat.getSelectedItem().equals("")) {
                Dialog.show("Erreur","Categorie doit etre non vide","OK",null);
                ck=false;
            }
            System.out.println("date debut time == "+d.getTime());
            System.out.println("date now time == "+new Date().getTime());
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int parse = today.compareTo(md);
                  if(parse >0) {
                       Dialog.show("Erreur","Date current > date debut   ","OK",null);
                       ck=false;
                    }
                  
            if(loc.getText().toString().equals("")) {
                       Dialog.show("Erreur","localisation doit etre non vide","OK",null);
                       ck=false;     
                    }
                  
            if(min.getText().length() == 0 && max.getText().length() == 0 ) {
                 Dialog.show("Erreur","Nbr min,max doivent etre non vides   ","OK",null);
                ck=false;
             }
            if(Integer.parseInt(max.getText().toString()) < Integer.parseInt(min.getText().toString())) {
                 Dialog.show("Erreur","Nbr MAX doivent etre superieur a Nbr Min ","OK",null);
                 ck=false;
             }
             if(prix.getText().length() ==0) {
                 Dialog.show("Erreur","prix doit etre superieur a 0  ","OK",null);
                 ck=false;
             }
             if(path1 == null) {
                 Dialog.show("Erreur","Svp choisir une photo","OK",null);
                 ck=false;
             }
            
            if(ck) {
            EvenementService es = EvenementService.getInstance();
            Evenement evenement = new Evenement(titre.getText().toString()
                                , description.getText().toString(), cat.getSelectedItem().toString()
                                , md,md1,Integer.parseInt(min.getText().toString())
                                ,Integer.parseInt(max.getText().toString()),0, path1, Integer.parseInt(prix.getText()),loc.getText().toString());
            es.ajoutEvenement(evenement,nameFile);
    }
            
    });
}

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }
    private void addTab(Tabs swipe, Label spacer,Image img, String commentsStr, String text,Resources res) {
       int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
       if (img.getHeight() < size) {
           img = img.scaledHeight(size);
        }
        Button likes = new Button();
        Style heartStyle = new Style(likes.getUnselectedStyle());
        //  heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
        likes.addActionListener(e -> {
            new NewsfeedForm(res).showBack();
        });
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        Container page1
                = LayeredLayout.encloseIn(
                       image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes),
                                        spacer
                                )
                        )
                );

        swipe.addTab("",res.getImage("back-logo.png"), page1);
    }
    
    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

}