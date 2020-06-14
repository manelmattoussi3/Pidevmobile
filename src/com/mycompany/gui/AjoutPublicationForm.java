/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
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
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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
import com.mycompany.entites.Publication;
import com.mycompany.services.EvenementService;
import com.mycompany.services.PublicationService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Khawla
 */
public class AjoutPublicationForm extends BaseForm {
    
    Form current;
    String path1;   
    String nameFile;
    Image img = null;
    String fileNameServer ;
    int nbMax,nbMin;
    String md1,md;
    Label loc;
    public AjoutPublicationForm(Resources res ,Evenement event ) throws Exception {
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
        addTab(swipe, spacer1, res.getImage("illustration-concept-creativite_114360-1083.jpg"), "", "",res);
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
        RadioButton mesListes = RadioButton.createToggle("Publications", barGroup);
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
        
        Publication e = new Publication();
        TextField titre = new TextField("", "Titre",20,TextField.ANY);
        titre.setUIID("TextFieldBlack");
        addStringValue("Titre", titre);
        TextField description = new TextField("", "Description",20,TextField.ANY);
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);
        TextField path = new TextField("", "File");
        path.setUIID("TextFieldBlack");
        addStringValue("File", path);
        ComboBox<String> cat;
        Vector<String> vector = new Vector();
           vector.add("Musique");
           vector.add("Dance");
           vector.add("Peinture");
        cat = new ComboBox<>(vector);
        path.setUIID("TextFieldBlack");
        addStringValue("Categorie", cat);
        
   
        
        
    Button imgBtn = new Button("Parcourir");
    Button foto = new Button("Parcourir");
    addStringValue("Parcourir votre image...", foto);
    // ac.setUIID("PaddedLabel");
    //addStringValue("Localisation", ac);
    
    //*******************************open Gallery *******************************///
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
                System.out.println("id event="+event.getId());
                PublicationService ps = PublicationService.getInstance();
                Publication pub = new Publication(titre.getText().toString() 
                                , description.getText().toString(), 
                                 cat.getSelectedItem().toString(),nameFile,SessionManager.getId(),event.getId());
                 //new ParticiperForm(res, evv)
                ps.ajoutPublication(pub);
                // new PublicationForm(res).showBack();
   }
        }catch(Exception ex ) {
           ex.printStackTrace();
       
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


    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}
