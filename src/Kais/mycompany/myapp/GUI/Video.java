/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import com.codename1.components.MediaPlayer;
import com.codename1.io.Log;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;

/**
 *
 * @author DevelopAndroid
 */
public class Video extends Form{
Form hi;
    public Video(Form previous) {
        hi=this;
         
        setTitle("MediaPlayer");
        setLayout(new BorderLayout());
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
     
    //  final Form hiy = new Form("MediaPlayer", new BorderLayout());
       // hi.setToolbar(new Toolbar());
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
        hi.getToolbar().addCommandToRightBar("", icon, (evt) -> {
            Display.getInstance().openGallery((e) -> {
                if (e != null && e.getSource() != null) {
                    String file = (String) e.getSource();
                    try {
                        com.codename1.media.Media video = MediaManager.createMedia(file, true);
                        hi.removeAll();
                        hi.add(BorderLayout.CENTER, new MediaPlayer(video));
                        video.play();
                        hi.revalidate();
                        
                    } catch (IOException err) {
                        Log.e(err);
                    }
                }
            }, Display.GALLERY_VIDEO);
        });
        hi.show();

    }
    
}
