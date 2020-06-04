/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author DevelopAndroid
 */
public class partagerxsociaux extends Form {

    Form hi;

    public partagerxsociaux(Form previous) {
        hi = this;
        setTitle("ShareButton");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        //Form hi = new Form("ShareButton");
        ShareButton sb = new ShareButton();
        sb.setText("Share Screenshot");
        hi.add(sb);
        Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
        hi.revalidate();
        hi.setVisible(true);
        hi.paintComponent(screenshot.getGraphics(), true);
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);
        }
        sb.setImageToShare(imageFile, "image/png");
        hi.show();
    }
}
