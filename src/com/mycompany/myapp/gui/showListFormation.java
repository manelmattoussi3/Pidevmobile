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
import static com.codename1.charts.util.ColorUtil.blue;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author DevelopAndroid
 */
public class showListFormation extends Form {

    Form liste;
    public Formation formation;
    public Coach co;

    public showListFormation(Form previous) {
        /*    Form listFormation = new ListFormationForm();
        // setTitle("List Formation");

        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceFormation.getInstance().getAllFormations().toString());
        // add(sp);

        //listFormation.getToolbar().setBackCommand("", e -> atras.showBack());
       listFormation.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e-> previous.showBack());
        listFormation.show();*/

        liste = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setTitle("Tunisian Got Talent");
        setLayout(BoxLayout.y());

        //add(new Label("choose an option"));
        //Button btnListFormation = new Button("List Formation");
        // Button btnaddformation = new Button("add Formation");
        // btnaddformation.addActionListener(e -> new AddFormationForm(current).show());
        // btnListFormation.addActionListener(e -> new ListFormationForm(current).show());
        // addAll(btnaddformation, btnListFormation);
        //Form liste = new Form("Liste Candidat", BoxLayout.y());
        //  Form liste = new Form("Liste des formations",BoxLayout.y());
        //Form addFormationForm = new Form("add des formations");
        ServiceFormation sf = new ServiceFormation();

        //  btnListFormation.addActionListener((evt) -> {
        ArrayList<Formation> listeFormation = new ArrayList<>();

        listeFormation = sf.getAllFormations();

        for (Formation f : listeFormation) {
            Container c = new Container(BoxLayout.y());
            Label mo = new Label("Description:");
            mo.getAllStyles().setBgColor(0xEE8CAE);
            Label ldescription = new Label(f.getDescription());
            Label mok = new Label("Date_Debut:");
            Label ldatedebut = new Label(f.getDate_Debut());
            Label mom = new Label("Date_Fin:");
            Label ldatefin = new Label(f.getDate_Fin());
            Label moll = new Label("Nbr_Place:");
            Label lnbr = new Label(f.getNbr_Place());
            Button devGuide = new Button("Show PDF");
            devGuide.addActionListener(e -> {
                new showPDF(formation).show();
            });
            // Label ldate = new Label(f.getCoach_id());
            /*  Label mollt = new Label("Coach ");
            
            ArrayList<Coach> listCoach = ServiceCoach.getInstance().getCoach();
            ComboBox<String> comboCoach = new ComboBox<>();
            for (Coach coach : listCoach) {
                comboCoach.addItem(coach.getNom());
            }
            c.addAll(mollt,comboCoach);*/
            Label categori = new Label("Categorie:");

            Label lcategorie = new Label(f.getCategorie());
           // Label cate = new Label("Coach:");
           // Label username = new Label(" :"+ f.getCoach_id());
           // Label lcat = new Label(co.getNom());
  ServiceCoach sp = new ServiceCoach ();
        ArrayList<Coach > listeCoach  = new ArrayList<>();
            
            listeCoach = sp.getCoach();
           /* for(Coach P:listeCoach){
                
               
                Label cate = new Label("Coach:");
                Label lnom = new Label(co.getNom());
                Container c6 = new Container(BoxLayout.x());
                 c6.addAll(cate,lnom);
             c.add(c6);
           }*/
            //f.getNbr_Place();
            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            Container c4 = new Container(BoxLayout.x());
            Container c5 = new Container(BoxLayout.x());
            // Container c6 = new Container(BoxLayout.x());
            //TextField tfnbrplace=new TextField();
            // Label lnbrplace = new Label(f.get("nbrPlace"));
            c1.addAll(mo, ldescription);
            c2.addAll(mok, ldatedebut);
            c3.addAll(mom, ldatefin);
            c4.addAll(moll, lnbr);
            c5.addAll(categori, lcategorie);
          // c6.addAll(cate,lnom);
            c.add(c5);
            c.add(c2);
            c.add(c3);
            c.add(c4);
            c.add(c1);
           // c.add(c6);
            c.add(devGuide);

            c.add(FlowLayout.encloseCenterMiddle(createStarRankSlider()));

            //liste.setTintColor(0x7700ff00);  
            // c.addAll(mom, ldatefin, moll, mollt);
            /*   InteractionDialog dlg = new InteractionDialog("Bienvenu au formation");
dlg.setLayout(new BorderLayout());
dlg.add(BorderLayout.CENTER, new Label("formation musique "));
Button close = new Button("Close");
close.addActionListener((ee) -> dlg.dispose());
dlg.addComponent(BorderLayout.SOUTH, close);
Dimension pre = dlg.getContentPane().getPreferredSize();
dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);*/
 /* ldescription.addPointerPressedListener((evt3) -> {
            new showAboutFormation(previous).show();
        });*/
            ldescription.addPointerPressedListener((evt3) -> {
                new FormationDetails(f).show();
            });

            liste.add(c);
            /* liste.getToolbar().addCommandToLeftBar("Back", null, (evt2) -> {

                    liste.removeAll();

                    show();

                });*/

        }

        liste.show();
        // Label username = new Label(" :"+ comment.getUser_id());

        // liste.show();
//});
    }

    public SwipeableContainer createRankWidget(String title, String year) {
        MultiButton button = new MultiButton(title);
        button.setTextLine2(year);
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()),
                button);
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

}
