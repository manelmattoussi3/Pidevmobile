/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import Kais.mycompany.myapp.entities.Formation;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import java.io.IOException;

/**
 *
 * @author DevelopAndroid
 */
public class showPDF extends Form {

    Form current;
    Form hi;
 Formation formation;
    //public showPDF(Form previous) {}
    public showPDF(Form previous){
        
        current = this;
        setTitle("PDF Viewer");
        //TextModeLayout tl = new TextModeLayout(3, 2);
        //setLayout(tl);
         setLayout(BoxLayout.y());
      //   Label descriptionDetails = new Label(formation.getDescription());
          //Label descriptionDetails = new Label(formation.getDescription());
        //  Form hi = new Form("PDF Viewer", BoxLayout.y());
        Button back = $(new Button("Back")).addActionListener(e2 -> {

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            new showListFormation(current).showBack();
        }).asComponent(Button.class);
        // hi = this;
        // Button devGuide = new Button("Show PDF");
        // devGuide.addActionListener(e -> {
     /*   FileSystemStorage fs = FileSystemStorage.getInstance();
        String fileName = fs.getAppHomePath() + "pdfhh";
        if (!fs.exists(fileName)) {
            Util.downloadUrlToFile("http://localhost/pidev-integration/web/app_dev.php/Projet/pdff" + f.getId(), fileName, true);
        }
        Display.getInstance().execute(fileName);*/
        //  });
//        String a="http://localhost/pidev-integration/web/app_dev.php/Projet/pdf/"+formation.getId();
        Button devGuide = new Button("Show PDF");
       
         devGuide.addActionListener(e -> {
       FileSystemStorage fs = FileSystemStorage.getInstance();
       String fileName = fs.getAppHomePath()+formation.getDate_Debut();
      
        if (!fs.exists(fileName)) {
          Util.downloadUrlToFile("http://localhost/pidev-integration/web/app_dev.php/Projet/pdf/"+formation.getId(), fileName, true);
        
            } 
            Display.getInstance().execute(fileName);
       
         });
      //  current.add(back);
      current.add(devGuide);
         current.add(back);
        current.add(FlowLayout.encloseCenter(createStarRankSlider()));
        //  new showListFormation(previous).showBack();
        current.show();
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

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private void showStarPickingForm() {
        hi = this;
        Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
        hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
        hi.show();
    }
}
