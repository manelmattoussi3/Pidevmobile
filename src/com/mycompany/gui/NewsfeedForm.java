/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import com.mycompany.entites.Participation;
import com.mycompany.services.EvenementService;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class NewsfeedForm extends BaseForm {
    
    //Variables
    Container cnt,events ; 
    private static final String 
    MAPS_KEY = "AIzaSyBKXNneTVr8yaKCVD_sCEFj9CNCtcU85V8";
    // Your maps key here
    Button mod,details,image;
    
     public NewsfeedForm(Resources res) {
        
        super("Newsfeed", BoxLayout.y());
        events = new Container(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e-> {
            System.out.println("data is = "+e.getSource());
            RechercherTitre(e.getSource().toString());
            String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        /******************************* clear search*************************/
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
   
        }
        refreshTheme();
        new NewsfeedForm(Resources.getGlobalResources()).show();
        revalidate();
        refreshTheme();
    }
        });
        if(SessionManager.getId() != 0) {
            System.out.println("current user = "+SessionManager.getRoles());
        if(SessionManager.getRoles().equals("[ROLE_ADMIN, ROLE_USER]")) {
        templateBackend(res);
       
  //*****************Afficher liste evenements pour admin***********************************************************
    
        afficherEvenementBack();
     
        }
            
  //****************************************************************************
        else {
            templateFront(res);
      //*****************Afficher liste evenements pour candidat*******************

            afficherEvenementFront();

     //****************************************************************************
  
     
    }
    
    
        }
     }
    
    //********************************arrow template*********//

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }

    //********************************Tab template**********************************************************//

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        //likes.setIcon(heartImage);
       // likes.setTextPosition(RIGHT);

        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
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

        swipe.addTab("", page1);
    }
  
    //-------------Liste des evenements partie admin --------------------------------------------//

    public Container addButton(Image img, String title,String loc,String categorie ,Evenement evv) {
           
        int height = Display.getInstance().convertToPixels(16.5f);
        int width = Display.getInstance().convertToPixels(24.4f);
         ImageViewer image = new ImageViewer(img.scaled(300, 250));
        image.setUIID("Label");
              Button  btn = new Button(" ");
                      btn.setUIID("Label");
              Button btnSupprimer = new Button(" ");
              Button btnLoc = new Button(" ");
              btnLoc.setUIID("Edit");
              btnSupprimer.setUIID("textDialog");
             details= new Button(" ");
              details.setUIID("Details");


       // image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       cnt= BorderLayout.west(image);
        
        //cnt.setLeadComponent(image);
       
        TextArea ta = new TextArea(title);
                TextArea localisation = new TextArea(loc);
                
        localisation.setUIID("NewsTopLine");
        localisation.setEditable(false);

        //
        TextArea label = new TextArea(categorie);
          label.setUIID("NewsTopLine");
        label.setEditable(false);
        //
         MapUI map = new MapUI();
         btnLoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    Coord coord = getCoord(loc);
                    System.out.println("Long lat ==="+coord);
                    MapUI mapUI = new MapUI();
                    mapUI.init(Resources.getGlobalResources());
                    mapUI.start(coord);
             
              
                }catch(Exception exx) {
                    
                }
              

            }});

        btn.setUIID("Edit");
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        Container cntt = new Container();
        Container cnttDelete = new Container();
        Container cnttDetail = new Container();
        Container cntLoc = new Container();                
        cntt.getStyle().setPadding(8, 8, 8, 8);
        cnttDelete.getStyle().setPadding(8, 8, 8, 8);
        cntLoc.getStyle().setPadding(8, 8, 8, 8);
        btnSupprimer.setWidth(500);
        cnttDelete.add(btnSupprimer);
        cntLoc.add(btnLoc);
        cntt.add(btn);
        cnttDetail.add(details);
        //cntt.add(details);
        
        Container  f = new Container();
        f.add(BoxLayout.encloseX(cntt,cnttDelete,cnttDetail,cntLoc));
       // details.setTextPosition(RIGHT);
        cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                ta,label,localisation,f
                ));

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                new ModifierEvenementForm(Resources.getGlobalResources(), evv).show();
                }catch(Exception e) {
                    
                }
            }
        });
          btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    System.out.println("id venet = "+evv.getId());
                    if(evv.getIsPublic() == 0) {
                    EvenementService.getInstance().SupprimerEvenement(evv.getId());
                    
                       Dialog.show("Suppression", "Supprimer ", "OK", null);
                    }
                    else {
                       Dialog.show("Evnement deja publié", "Vous n'avez pas le droit de supprimer ", "OK", null);

                    }

                }catch(Exception e) {
                    
                }
            }
        });
          
          
        

return cnt;
       }    
    
    //-------------Liste des evenements partie Candidat --------------------------------------------//
    
    public Container addButtonCandidat(Image img, String title,String categorie ,Evenement evv,String str) {    
        int height = Display.getInstance().convertToPixels(16.5f);
        int width = Display.getInstance().convertToPixels(24.4f);
        ImageViewer image = new ImageViewer(img.scaled(300, 250));
        image.setUIID("Label");
              Button details = new Button(" ");
              details.setUIID("Details");
        Label likes = new Label(str);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_SHARE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);
        //image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        cnt= BorderLayout.west(image);
        //cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        //
        TextArea label = new TextArea(categorie);
        label.setUIID("NewsTopLine");
        label.setEditable(false);
        //
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        Container cntt = new Container();
        Container cnttDelete = new Container();
        Container cnttDetail = new Container();
        cntt.getStyle().setPadding(8, 8, 8, 8);
        //
        //
        // details.setTextPosition(RIGHT);
        cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                ta,label,details,likes
                ));

         
     details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                        new DetailEvenementForm(Resources.getGlobalResources(), evv).show();
                    }catch(Exception e) {
                    
                }
            }
        });

         likes.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                       new AjoutPublicationForm(Resources.getGlobalResources(), evv).show();

                }catch(Exception e) {
                    
                }
            }
        });

     return cnt;
       }
    
       //-------------Liste des evenements partie Candidat------------------------------------------//

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
      //-------------affichage evenement selon titre------------------------------------------//

    public void afficheEvenementParTitre(Resources res,String title) {
           
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Acceuil", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Demande", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Ajouter un evenement", barGroup);
        popular.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        TextField textSearch = new TextField(title, "titre",20,TextField.ANY);
        textSearch.setUIID("TextField1");
        Label btnSearch = new Label(" ");
        btnSearch.setUIID("NewsTopLine");
        Style btnSearchStyle = new Style(btnSearch.getUnselectedStyle());
        btnSearchStyle.setFgColor(0x021430);
        FontImage btnSearchImage = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, btnSearchStyle);
        btnSearch.setIcon(btnSearchImage);
        btnSearch.setTextPosition(LEFT);
        
        btnSearch.addPointerPressedListener(l -> {
            afficheEvenementParTitre(res, title);
        });
        Label l1 = new Label(" ");
        Label l2 = new Label(" ");
        Container cntSearch = BorderLayout.west(textSearch);
        cntSearch.add(BorderLayout.EAST , BoxLayout.encloseX(l1,l2,btnSearch));
        add(cntSearch);
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, all,featured,popular ),
                FlowLayout.encloseBottom(arrow)
        ));
        
        //Consomation json Rechercher evenement par titre 
        
        
    }
    
     //-------------Recherche des evenements----------------------------------------------------------//

    public void RechercherTitre(String q) {
        
        
        System.out.println("*******************"+EvenementService.getInstance().getSearchListEvenement(q));
     
            removeAll();
               
               if(q == null) {
                   new NewsfeedForm(Resources.getGlobalResources()).showBack();
               }
              for(Evenement evenement :  EvenementService.getInstance().getSearchListEvenement(q)) {
                  String url ="http://127.0.0.1:8000/api/evenement/image?imagepath="+evenement.getImagepath();
                  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getHeight() / 3, 0xFFFFFFFF), true);
                  Image images = URLImage.createToStorage(placeholder, url, "http://127.0.0.1:8000/api/evenement/image?imagepath="
                                                           +evenement.getImagepath(),URLImage.RESIZE_SCALE_TO_FILL);
            add(addSearchButton(images,
            evenement.getTitle(), evenement));   
    }
}
    
 //---------Recherche Evenement methodes---------------------//
    
//---------La premiere methode-----------------------------//

    public  void getCurrentDataTemplateAfterSearch(Resources res) {
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("maisonLogo.png"), spacer1, "","","");
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

     
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Acceuil", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Demande", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Ajouter un evenement", barGroup);
        popular.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, featured, popular),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
       popular.addActionListener((e -> {
        try{
           new AjoutEvenementForm(res,"").show();
           }catch(Exception excp ) {
               excp.printStackTrace();
           }
       }));
       
        featured.addActionListener((e -> {
        try{
           new AjoutEvenementForm(res,"").show();
           }catch(Exception excp ) {
               excp.printStackTrace();
           }
       }));
             
             
             
    }    
    
    //---------La deuxieme methode-----------------------------------------//
    public Container addSearchButton(Image img, String title ,Evenement evv) {
           
        int height = Display.getInstance().convertToPixels(16.5f);
        int width = Display.getInstance().convertToPixels(24.4f);
        ImageViewer image = new ImageViewer(img.scaled(300, 250));
        image.setUIID("Label");
        Button  btn = new Button(" ");
        btn.setUIID("Label");
        Button btnSupprimer = new Button(" ");
        btnSupprimer.setUIID("textDialog");
        details= new Button(" ");
        details.setUIID("Details");


       // image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       cnt= BorderLayout.west(image);
        //cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
        
        //
       btn.setUIID("Edit");
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       Container cntt = new Container();
       Container cnttDelete = new Container();
       Container cnttDetail = new Container();
       cntt.getStyle().setPadding(8, 8, 8, 8);
       cnttDelete.getStyle().setPadding(8, 8, 8, 8);
       btnSupprimer.setWidth(500);
       cnttDelete.add(btnSupprimer);
       cntt.add(btn);
       cnttDetail.add(details);
        //cntt.add(details);
        //
        
        //
        Container  f = new Container();
        f.add(BoxLayout.encloseX(cntt,cnttDelete,cnttDetail));
       // details.setTextPosition(RIGHT);
        cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                ta,f
                ));

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                new ModifierEvenementForm(Resources.getGlobalResources(), evv).show();
                }catch(Exception e) {
                    
                }
            }
        });
          btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    System.out.println("id venet = "+evv.getId());
                    if(evv.getIsPublic() == 0) {
                    EvenementService.getInstance().SupprimerEvenement(evv.getId());
                    
                                       Dialog.show("Suppression", "Supprimer ", "OK", null);
                    }
                    else {
                       Dialog.show("Evnement deja publié", "Vous n'avez pas le droit de supprimer ", "OK", null);

                    }

                }catch(Exception e) {
                    
                }
            }
        });
          

return cnt;
       }
    
    
    public Coord getCoord(String location) {
        Coord ret =null;
        
        try{
            
      ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json",false);
      request.addArgument("key",MAPS_KEY);
      request.addArgument("address",location);
      
      NetworkManager.getInstance().addToQueueAndWait(request);//blech hathi request matet3adich,5ater 3antari9ha haya bch nab3th request l server w nestana response 
              Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));

      if(response.get("results") !=null){
          ArrayList result =(ArrayList)response.get("results");
          LinkedHashMap locationLngLat = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap)result.get(0)).get("geometry")).get("location");
          
          ret = new Coord((double)locationLngLat.get("lat"), (double)locationLngLat.get("lng"));
          
          
      }
      
       
        
        
        }catch(Exception ex) {
                ex.printStackTrace();
                
        
    }
        return ret;
    }
    
    
    public void templateBackend(Resources res) {
         Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("maisonLogo.png"), spacer1, "","","");
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

     
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Acceuil", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Demande", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Ajouter un evenement", barGroup);
        popular.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, featured, popular),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
       popular.addActionListener((e -> {
                   

           try{
           new AjoutEvenementForm(res,"").show();
           }catch(Exception excp ) {
               excp.printStackTrace();
           }
       }));
       
             featured.addActionListener((e -> {
        try{
           new AjoutEvenementForm(res,"").show();
           }catch(Exception excp ) {
               excp.printStackTrace();
           }
       }));
        
    }
    
    
    public void afficherEvenementBack() {
        EvenementService evenementService =   EvenementService.getInstance();
    for(Evenement e : evenementService.getEvenemnet()){
    Display.getInstance().callSerially(() -> {}); 
    String url ="http://127.0.0.1:8000/api/evenement/image?imagepath="+e.getImagepath();
    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getHeight() / 3, 0xFFFFFFFF), true);
    Image images = URLImage.createToStorage(placeholder, url, "http://127.0.0.1:8000/api/evenement/image?imagepath="+e.getImagepath(),URLImage.RESIZE_SCALE_TO_FILL);
    cnt = LayeredLayout.encloseIn(addButton(images,e.getTitle(),e.getLocalisation(),e.getCategories(),e));
    image = new Button(images.scaled(300, 250));
    System.out.println("loc =" +e.getLocalisation());
    events.add(cnt);
    details.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent evt) {
             new ParticiperForm(Resources.getGlobalResources(),e).show();

            }
        });
          
    }
   refreshTheme();
   events.revalidate();
   add(events);
    }

    
    
    public void templateFront(Resources res) {
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("feedback.jpg"), spacer1, "","","");
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Evenements", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Publication", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Statistique", barGroup);
        popular.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, featured, popular),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
       popular.addActionListener((e -> {
         try{
           new StatistiqueForm(res).show();
           }catch(Exception excp ) {
               excp.printStackTrace();
           }
       }));

        
    }
    
    
    public void afficherEvenementFront() {
         EvenementService evenementService =   EvenementService.getInstance();
        for(Evenement e : evenementService.getEvenemnet()){
              Display.getInstance().callSerially(() -> {}); 
        String url ="http://127.0.0.1:8000/api/evenement/image?imagepath="+e.getImagepath();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getHeight() / 3, 0xFFFFFFFF), true);
        Image images = URLImage.createToStorage(placeholder, url, "http://127.0.0.1:8000/api/evenement/image?imagepath="+e.getImagepath(),URLImage.RESIZE_SCALE_TO_FILL);
        cnt = LayeredLayout.encloseIn(addButtonCandidat(images,e.getTitle(),e.getCategories(),e,""));
        image = new Button(images.scaled(300, 250));
        events.add(cnt);
       }
       refreshTheme();
       events.revalidate();
       add(events);
 //---------------------------------------------------------------//

        }
    
    }
 

