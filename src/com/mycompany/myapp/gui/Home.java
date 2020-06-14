package com.mycompany.myapp.gui;

import com.mycompany.myapp.Service.ServiceCoach;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.Formation;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MediaPlayer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SliderBridge;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;

import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.media.MediaManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;

import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Form;

import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;

import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;

import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import javafx.scene.media.Media;

public class Home extends Form {

    /*public Home(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }*/
    Form home;
   
    private Resources theme;
    public Formation formation;
//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
        setInlineStylesTheme(resourceObjectInstance);
        setInlineAllStyles("bgType:image_scaled_fill; bgImage:2f204b69d244421fe4e9e4e199561998.jpg;");
        setTitle("Home");
        setName("Home");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public Home(Form Current) {

        try {

            home = this;
            Tabs tabs = new Tabs();

            Style s = UIManager.getInstance().getComponentStyle("Tab");
//s.setFgColor(0x77EE8CAE);
            FontImage mus = FontImage.createMaterial(FontImage.MATERIAL_MUSIC_NOTE, s);

            ImageViewer iv = new ImageViewer(Image.createImage("/khawla.jpg"));
            iv.setImageInitialPosition(ImageViewer.IMAGE_FILL);

            tabs.addTab("Tunisian Got Talent", mus, iv);

            home.setLayout(new BorderLayout());
            home.add(BorderLayout.CENTER, tabs);
            tabs.getUIID();

            home.show();
        } catch (IOException ex) {

        }
        Toolbar.setGlobalToolbar(true);

       
        getToolbar().getAllStyles().setBgColor(0xEE8CAE);
        getToolbar().addMaterialCommandToSideMenu("List Formation", FontImage.MATERIAL_LIST, e -> new showListFormation(home).show());
        getToolbar().addMaterialCommandToSideMenu("A propos de Formation", FontImage.MATERIAL_QUEUE_MUSIC, e -> new showAboutFormation(home).show());
        getToolbar().addMaterialCommandToSideMenu("Ajouter Formation", FontImage.MATERIAL_ADD, e -> new AddFormationForm(home).show());
        
         getToolbar().addMaterialCommandToSideMenu("List Conseil", FontImage.MATERIAL_ATTACH_FILE, e -> new ShowListConseil(home).show());
         getToolbar().addMaterialCommandToSideMenu("Ajouter Conseil", FontImage.MATERIAL_ADD, e -> new AddConseilForm(home).show());
        // getToolbar().addMaterialCommandToSideMenu("Supprimer Formation", FontImage.MATERIAL_DELETE, e -> new SupprimerFormationForm(home).show());
        getToolbar().addMaterialCommandToSideMenu("ListCoach", FontImage.MATERIAL_LIST, e -> new showCoach(home).show());
        // getToolbar().addMaterialCommandToSideMenu("Map", FontImage.MATERIAL_MAP, e -> showMap(home));
        // getToolbar().addMaterialCommandToSideMenu("PDF", FontImage.MATERIAL_FILE_UPLOAD, e -> new showPDF(home).show());
        getToolbar().addMaterialCommandToSideMenu("Arborcense de Fichier", FontImage.MATERIAL_FILE_DOWNLOAD, e -> new PDF(home).show());
        getToolbar().addMaterialCommandToSideMenu("Partager avec Amis", FontImage.MATERIAL_SHARE, e -> new partagerxsociaux(home).show());
        //getToolbar().addMaterialCommandToSideMenu("Video", FontImage.MATERIAL_VIDEO_LIBRARY, e -> new Video(home).show());
        getToolbar().addMaterialCommandToSideMenu("voir ailleurs ", FontImage.MATERIAL_NETWORK_CELL, e -> VoirAieur(home));
        //getToolbar().addMaterialCommandToSideMenu("vos Propositions", FontImage.MATERIAL_WRAP_TEXT, e -> Proposition(home));

    /*   getToolbar().addMaterialCommandToSideMenu("Back", FontImage.MATERIAL_ARROW_BACK, e -> {
          
                Back();
          
                
            
        });*/
        //getToolbar().addMaterialCommandToSideMenu("openpdf", FontImage.MATERIAL_LIST, e -> new openpdf(home).show());
        //getToolbar().addMaterialCommandToSideMenu("op", FontImage.MATERIAL_LIST, e -> new FormationDetails(home).show());
        //getToolbar().addSearchCommand(e -> showCoach(home));
        Form detailsFormation = new Form("Details", BoxLayout.y());
        
    }

  
    private void VoirAieur(Form atras) {
        /*  Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("Title");
        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        TextField searchField = new TextField("", "Toolbar Search");
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        hi.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> {
            String t = searchField.getText();
            if (t.length() < 1) {
                for (Component cmp : hi.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for (Component cmp : hi.getContentPane()) {
                    String val = null;
                    if (cmp instanceof Label) {
                        val = ((Label) cmp).getText();
                    } else {
                        if (cmp instanceof TextArea) {
                            val = ((TextArea) cmp).getText();
                        } else {
                            val = (String) cmp.getPropertyValue("text");
                        }
                    }
                    boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                    cmp.setHidden(!show);
                    cmp.setVisible(show);
                }
            }
            hi.getContentPane().animateLayout(250);
        });
        hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync();
        });
        hi.add("A Game of Thrones").
                add("A Clash Of Kings").
                add("A Storm Of Swords").
                add("A Feast For Crows").
                add("A Dance With Dragons").
                add("The Winds of Winter").
                add("A Dream of Spring");
        hi.show();*/
        Form hi = new Form("Navigateur", new BorderLayout());
        hi.getToolbar().setBackCommand("retour", (ev4) -> {
            show();
        });
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("https://www.francemusique.fr/savoirs-pratiques/j-apprends-la-musique-en-ligne-37092/");
        //browser.setURL("http://localhost/pidev-integration/web/app_dev.php/Projet/Affichage");
        hi.add(BorderLayout.CENTER, browser);
        hi.show();

    }

    private void Proposition(Form atras) {
        Form hi = new Form("Ecrivez Vos Propositions", new BorderLayout());
        hi.getToolbar().setBackCommand("retour", (ev4) -> {
            show();
        });
        BrowserComponent bc = new BrowserComponent();
        bc.setPage("<html lang=\"en\">\n"
                + " <head>\n"
                + " <meta charset=\"utf-8\">\n"
                + " <script>\n"
                + " function fnc(message) {\n"
                + " document.write(message);\n"
                + " };\n"
                + " </script>\n"
                + " </head>\n"
                + " <body >\n"
                + " <p>Demo</p>\n"
                + " </body>\n"
                + "</html>", null);
        TextField tf = new TextField();

        hi.add(BorderLayout.CENTER, bc).
                add(BorderLayout.SOUTH, tf);
        bc.addWebEventListener("en charge", (e) -> bc.execute("fnc('<p>Tunisian Got Talent</p>')"));
        tf.addActionListener((e) -> bc.execute("fnc('<p>" + tf.getText() + "</p>')"));
        hi.show();
    }

    // private void  createStarRankSlider() {
    /*Form hi =new Form();
        final DefaultListModel<String> options = new DefaultListModel<>();
        AutoCompleteTextField ac = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if (text.length() == 0) {
                    return false;
                }
                String[] l = searchLocations(text);
                if (l == null || l.length == 0) {
                    return false;
                }
                options.removeAll();
                for (String s : l) {
                    options.addItem(s);
                }
                return true;
            }
        };
        ac.setMinimumElementsShownInPopup(5);
        hi.add(ac);
        hi.add(new SpanLabel("This demo requires a valid google API key to be set below "
                + "you can get this key for the webservice (not the native key) by following the instructions here: "
                + "https://developers.google.com/places/web-service/get-api-key"));
        hi.add(apiKey);
        hi.getToolbar().addCommandToRightBar("Get Key", null, e
                -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
        hi.show();
    }
    TextField apiKey = new TextField();

    String[] searchLocations(String text) {
        try {
            if (text.length() > 0) {
                ConnectionRequest r = new ConnectionRequest();
                r.setPost(false);
                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
                r.addArgument("key", apiKey.getText());
                r.addArgument("input", text);
                NetworkManager.getInstance().addToQueueAndWait(r);
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;
            }
        } catch (Exception err) {
            Log.e(err);
        }
        return null;*/
    //  SwipeableContainer swip = new SwipeableContainer(bottom, top);
    private void openpdf(Form atras) {
        Form hi = new Form("Hi World");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        hi.addComponent(new Label("Hi World"));
        Button b = new Button("Browse Files");
        CheckBox multiSelect = new CheckBox("Multi-select");
        b.addActionListener(e -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf, .csv, text/plain", e2 -> {
                    if (e2 == null || e2.getSource() == null) {
                        hi.add("No file was selected");
                        hi.revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);

                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        hi.add("No file was selected");
                        hi.revalidate();
                    } else {
                        System.out.println("File path: " + file);

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                        }
                        FileSystemStorage fs = FileSystemStorage.getInstance();
                        if ("txt".equals(extension)) {

                            try {
                                InputStream fis = fs.openInputStream(file);
                                hi.addComponent(new SpanLabel(Util.readToString(fis)));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }
                        } else {
                            hi.add("Selected file " + file);
                        }
                        File f = new File(file);
                        String contents = "";
                        try (InputStream fis = fs.openInputStream(file)) {
                            contents = Util.readToString(fis);
                        } catch (Exception ex) {
                            Log.e(ex);
                            return;
                        }
                        contents += "\nTest";
                        try (OutputStreamWriter writer = new OutputStreamWriter(fs.openOutputStream(file, 0), "UTF-8")) {
                            writer.write(contents);
                        } catch (IOException ex) {
                            Log.e(ex);
                            return;
                        }

                    }
                    hi.revalidate();
                });
            }
        });
        hi.addComponent(b);

        Button testImage = new Button("Browse Images");
        testImage.addActionListener(e -> {
            if (FileChooser.isAvailable()) {

                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2 -> {
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            try {
                                Image img = Image.createImage(path);
                                hi.add(new Label(img));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }

                        }
                        return;

                    }
                    if (e2 != null && e2.getSource() != null) {

                        String file = (String) e2.getSource();
                        try {
                            Image img = Image.createImage(file);
                            hi.add(new Label(img));
                            if (true) {
                                return;
                            }
                        } catch (Exception ex) {
                            Log.e(ex);
                        }

                        String filestack = "http://solutions.weblite.ca/testupload.php";

                        MultipartRequest request = new MultipartRequest();

                        request.setUrl(filestack);

                        request.setPost(true);
                        try {
                            request.addData("fileUpload", file, "/");

                            request.setFilename("fileUpload", "myfile.png");

                            request.setReadResponseForErrors(true);
                            NetworkManager.getInstance().addToQueueAndWait(request);
                        } catch (Throwable t) {
                            Log.e(t);
                        }
                    }
                });
            }
        });
        hi.add(testImage);
        hi.add(multiSelect);
        hi.show();
    }

    private void showCoach() {
       
 Form hi = new Form("Download Progress", new BorderLayout());
Slider progress = new Slider();
Button download = new Button("Download");
download.addActionListener((e) -> {
    ConnectionRequest cr = new ConnectionRequest("https://www.codenameone.com/img/blog/new_icon.png", false);
    SliderBridge.bindProgress(cr, progress);
    NetworkManager.getInstance().addToQueueAndWait(cr);
    if(cr.getResponseCode() == 200) {
        hi.add(BorderLayout.CENTER, new ScaleImageLabel(EncodedImage.create(cr.getResponseData())));
        hi.revalidate();
    }
});
hi.add(BorderLayout.SOUTH, progress).add(BorderLayout.NORTH, download);
hi.show();
    }

    private void Back(Form previous) throws IOException {
       new AccueilForm().showBack();
    }
}
