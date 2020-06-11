package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
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
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import com.mycompany.services.EvenementService;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Hashtable;
import javafx.scene.control.Alert;
/**
 *
 * @author Khawla
 */
public class DetailEvenementForm extends BaseForm {

    public DetailEvenementForm(Resources res,Evenement  evv) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Tunisian Got Talent");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        //***************************************get image*************************************//
        System.out.println("data event = "+evv.toString());
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        addTab(swipe,urlImage(evv), evv.getTitle(),evv);
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
 //--------------------------bch nfsrouh --------------------------------------//
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesEv = RadioButton.createToggle("Mes evenements", barGroup);
        mesEv.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Evenements", barGroup);
        liste.setUIID("SelectBar");
        RadioButton modif = RadioButton.createToggle("Detaill", barGroup);
        modif.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        mesEv.addActionListener((e) -> {
                new NewsfeedForm(res).show();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesEv, liste, modif),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Button btnmodif = new Button("Modifier");
        modif.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(modif, arrow);
        });
        bindButtonSelection(liste, arrow);
        bindButtonSelection(modif, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
        /*------------------show one event -------------------------------------------*/
        //EvenementService es = EvenementService.getInstance();
        // Evenement evv = es.DetailEvenement(cd.getId(), cd);
        Label titre = new Label(evv.getTitle(), "Titre evenement");
        titre.setUIID("TextFieldBlack");
        addStringValue("Titre :", titre);
        Label prix = new Label(String.valueOf(evv.getPrix()), "Prix");
        prix.setUIID("TextFieldBlack");
        addStringValue("Prix :", prix);
        Label datedebut = new Label(evv.getDatedebut(), "Date debut evenement");
        datedebut.setUIID("TextFieldBlack");
        addStringValue("Date debut :", datedebut);
        Label datefin = new Label(evv.getDatefin(), "Date fin evenement");
        datefin.setUIID("TextFieldBlack");
        addStringValue("Date fin :",datefin );
        Label nbrMin = new Label(String.valueOf(evv.getNombreMinParticipants()), "nbrMin");
        nbrMin.setUIID("TextFieldBlack");
        addStringValue("Nombre min :",nbrMin);
        Label nbrMax= new Label(String.valueOf(evv.getNombreMaxParticipants()), "nbrMax");
        nbrMax.setUIID("TextFieldBlack");
        addStringValue("Nombre max :",nbrMax);
        Label Lieu= new Label("Consulter Map", "map");
        Lieu.setUIID("TextFieldBlack");
        addStringValue("Consulter Map :",Lieu);
        Lieu.addPointerPressedListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        MapUI le = new MapUI();
        Coord src = new Coord(36.8065, 10.1815);
        Coord dest = new Coord(35.8256, 10.6084);
        //    le.start(src,dest);                
        };
        });
        
        
        TextArea desc= new TextArea(evv.getDescription());
        desc.setRows(8);
        desc.setColumns(8);
        desc.setHint("ici desc.....");
        desc.setUIID("Container");
        addStringValue("Description :",desc);
        Button participer = new Button("Participer !");
        participer.setUIID("Button");
        addStringValue("", participer);
        participer.addActionListener((e) -> {
            Dialog.show("Participation","Voullez vous vraimenet participer?","OK",null);
            System.out.println("id current user = "+SessionManager.getId());
            EvenementService.getInstance().envyerDemandeParticipation(SessionManager.getId(), evv.getId());
    });
}
    
 public boolean isAnumber(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }
 
 //--------------------------bch nfsrouh --------------------------------------//
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img,String text,Evenement ev) {
           
        ScaleImageLabel image = new ScaleImageLabel(img.scaled(1200,700));
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
        //Button reserver = new Button();
        

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText")
                                       // FlowLayout.encloseIn(likes, comments),
                                )
                        )
                );

        swipe.addTab("", page1);
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
    
public  Image urlImage(Evenement e) {
        String url ="http://127.0.0.1:8000/api/evenement/image?imagePath=" +e.getImagepath();
EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("smily.png"), false);
Image urli = URLImage.createToStorage(placeholder,url, url,URLImage.RESIZE_SCALE);
return urli;}
}