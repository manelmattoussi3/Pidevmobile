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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import service.ServiceUser;

/**
 *
 * @author Nasri
 */
public class HomeForm 
{
        Form f;
        SpanLabel lb;
    Command cmd1, cmd2, cmd3, cmdBack, cmdExit,cmd4,cmd5;

  
    public HomeForm() {
        
        f = new Form(new FlowLayout(CENTER, CENTER));
        cmd4 = new Command("");

        cmd1 = new Command("edit profil");
        cmd2 = new Command("envoyez une réclamation");
        cmd3 = new Command("déconnexion");
       Container a = new Container(); 
       
       
              
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);

        f.getToolbar().addCommandToSideMenu(cmd1);
        f.getToolbar().addCommandToSideMenu(cmd2);
        f.getToolbar().addCommandToSideMenu(cmd3);

        
        
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
