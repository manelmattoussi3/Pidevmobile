/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.User;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import service.ServiceUser;

/**
 *
 * @author Nasri
 */
public class CoatchInterface {

    Form f,f1;
    SpanLabel lb;
    Resources theme;
    Command cmd1, cmd2, cmd3, cmdBack, cmdExit;

  
    public CoatchInterface() 
    {
                f1 = new Form(new FlowLayout(CENTER, CENTER));

         UIBuilder ui = new UIBuilder();
     //    Container cnt1 = ui.createContainer();
        cmd1 = new Command("Page 1");
        cmd2 = new Command("Page 2");
        cmd3 = new Command("Page 3");
//        cmdBack = new Command("Back", theme.getImage("back-command.png"));
        cmdExit = new Command("Exit");
   
        f1.getToolbar().addCommandToSideMenu(cmd2);
        f1.getToolbar().addCommandToSideMenu(cmd3);
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
