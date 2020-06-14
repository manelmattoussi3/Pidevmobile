/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Nasri
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;

/**
 *
 * @author mahjoub
 */
public class HomePage {

    Form f;
    Container cn1;
    ImageViewer iv;
    Image img;
Label vvv , insc , conn;
    public HomePage(Resources theme) {
        f = new Form("Tunisia GotTallent", new FlowLayout(CENTER, CENTER));
        cn1 = new Container();
        iv = new ImageViewer();
        img = theme.getImage("logo123.jpg");
        vvv = new Label(" Welcome To The Application ! ");
        /*
        insc = new Label("connecter"); 
        Style aa = insc.getAllStyles();
        aa.setAlignment(CENTER);
        aa.setBorder(RoundBorder.create().
        rectangle(true).
        color(0xffffff).
        strokeColor(0).
        strokeOpacity(120));
        
        conn = new Label(" créer un cmpte"); 
        Style bb = conn.getAllStyles();
        bb.setAlignment(CENTER);
        */
        
        iv.setImage(img.scaled(1200, 1200));
        
        iv.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
            }
        });
        cn1.add(iv);
       

        f.add(cn1);
        Container c = new Container(BoxLayout.y());
         Button loginButton = new Button("Se connecter");
         loginButton.addActionListener((e)->
         {
             Affichage A = new Affichage(); 
             A.getF().show();
         });
        Button AddUSer = new Button("créer un compte");
        AddUSer.addActionListener((e)->
        {
            AddUser a = new AddUser(); 
            a.getF().show();
        });
        c.addAll(loginButton, AddUSer); 
        f.add(c); 
        
/*
      
        c.add(vvv);
        c.add(insc); 
        c.add(conn); 
        f.add(c); 
    */   
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
