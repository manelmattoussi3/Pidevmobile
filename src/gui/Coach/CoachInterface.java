/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Coach;

import Entite.User;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import gui.AjouterReclamation;
import gui.EditProfil;
import gui.HomePage;
import gui.ListeReclamation;

/**
 *
 * @author Nasri
 */
public class CoachInterface 
{
     Form f;
    SpanLabel lb;
    Command cmd1, cmd2, cmd3, cmdBack, cmdExit,cmd4,cmd5;
    Label erreur ; 
    User user ; 
   private Resources theme;


  
    public CoachInterface(User user) 
    {
        this.user= user; 
         TextField login = new TextField("USERNAME", "Login", 20, TextField.EMAILADDR) ;
         f=new Form(); 
         f.add(login); 
         cmd4 = new Command("");
      
       Container a = new Container(); 
       
       
              
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);

          theme = UIManager.initFirstTheme("/theme");


        f.getToolbar().addCommandToSideMenu("modifier profil", null,
        e->{
            
           EditProfil edit = new EditProfil(user); 
           edit.getF().show();
            System.err.println("test valider");
        });
        f.getToolbar().addCommandToSideMenu("envoyez une réclamation", null,
        e->{
            
            AjouterReclamation rec = new AjouterReclamation(theme , user); 
            rec.getF().show(); 
            System.err.println("test valider");
        });
         f.getToolbar().addCommandToSideMenu("liste de  réclamations", null,
        e->{
            ListeReclamation rec = new ListeReclamation(theme, user);
            rec.getF().show(); 
            System.err.println("test valider");
        });
        
        f.getToolbar().addCommandToSideMenu("déconnexion", null,
        e->{
            
            HomePage H = new HomePage(theme); 
            H.getF().show();
            System.err.println("test valider");
        });
        
        
      
        
    }

    public Form getF()
    {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
