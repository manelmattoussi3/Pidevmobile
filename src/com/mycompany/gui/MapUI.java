/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.providers.GoogleMapsProvider;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.Log;
import com.codename1.location.Location;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.mycompany.entites.Evenement;

/**
 *
 * @author Khawla
 */
public class MapUI extends Form {
private static final String MAPS_KEY = "AIzaSyBKXNneTVr8yaKCVD_sCEFj9CNCtcU85V8"; // Your maps key here
Form hi = new Form("Native Maps Test");
double lat,longitude;
Label label  = new Label();
private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme_1");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
public void start(Coord loc) throws Exception {
      
        if (current != null) {
            current.show();
            return;
        
        }
            
        Command back = new Command("") {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    try {
                        new AjoutEvenementForm(Resources.getGlobalResources(),label.getText()).showBack();
                    } catch (Exception ex) {
                        System.out.println("ex === "+ex.getMessage());
                    }
                
            }
            };
        
            
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));
        FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 5);
        this.addCommand(back);
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(MAPS_KEY);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
        cnt.setCameraPosition( loc );// Tunisia
        });
            cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        loc,
                        "",
                        "",
                        e3->{
                        }
                    );
        Button btnAddMarker = new Button("Retour");
        btnAddMarker.addActionListener(e->{

            try{
            new NewsfeedForm(Resources.getGlobalResources()).show();
            
            }catch(Exception ec) {
                
            }

        });

        Button btnAddPath = new Button("Add Path");
        btnAddPath.addActionListener(e->{

            cnt.addPath(
                    cnt.getCameraPosition(),
                    new Coord(36.81897, 10.16579) // Tunisia
           
            );
        });

        Button btnClearAll = new Button("Clear All");
        btnClearAll.addActionListener(e->{
            cnt.clearMapLayers();
        });

        cnt.addTapListener(e->{
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
               Location l = Display.getInstance().getLocationManager().getCurrentLocationSync();
               String txt = enterName.getText();
               cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        enterName.getText(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                        }
                ); dlg.dispose();
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                    FlowLayout.encloseBottom(btnMoveCamera, btnAddMarker, btnAddPath, btnClearAll)
                )
        );
        hi.add(BorderLayout.CENTER, root);
        hi.show();

    }
    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }
    
    

}

    
    
