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
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import service.UpdateUser;

/**
 *
 * @author Nasri
 */
public class EditProfil
 
    
{
    Form f;
    SpanLabel lb;
    Command cmd1, cmd2, cmd3, cmdBack, cmdExit,cmd4,cmd5;
    User user;
    private Resources theme;

    
    
    
    public EditProfil(User user)
    {
        this.user=user ; 
        f = new Form(new FlowLayout(CENTER, CENTER));

        TextField login = new TextField(user.getNom(), "Login", 20, TextField.EMAILADDR) ;
        TextField prenom = new TextField(user.getPrenom(), "prenom", 20, TextField.EMAILADDR) ;
        TextField telephone = new TextField(String.valueOf(user.getTelephone()), "telephone", 20, TextField.ANY) ;   
        TextField password = new TextField(user.getPassword(), "Password", 20, TextField.PASSWORD) ;
        Button updateButton = new Button("Modifier");
        updateButton.addActionListener((ev)->
        {
            try
            {
            System.err.println("test validder");
            
            UpdateUser update = new UpdateUser(); 
            user.setNom(login.getText());
            user.setPrenom(prenom.getText());
            float tel = Float.parseFloat(telephone.getText());
            user.setTelephone((int)tel);
            user.setPassword(password.getText());   
            user.toString(); 
            update.Updateuser(user); 
            }
            catch(Exception ex)
            {
                System.err.println("bug");
            }
                  
         
        
        }); 
        
        cmd4 = new Command("");
      // cmd1 = new Command("Acceuil");
        cmd2 = new Command("envoyez une réclamation");
        cmd3 = new Command("déconnexion");
        theme = UIManager.initFirstTheme("/theme");

       Container a = new Container(); 
       
       
              
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);


        f.getToolbar().addCommandToSideMenu("Acceuil", null,
        e->
        {
         UserInterface userr = new UserInterface(user); 
         userr.getF().show();
            
        });
         f.getToolbar().addCommandToSideMenu("ajouter une reclamation", null,
        e->{
            
            Affichage f2 = new Affichage(); 

            f2.getF().show();
        });
          f.getToolbar().addCommandToSideMenu("Déconnexion", null,
        e->
        {
           HomePage H = new HomePage(theme); 
           H.getF().show();
            
        });
        

        Container c = new Container(BoxLayout.y()); 
        c.addAll(login,prenom,telephone,password,updateButton); 
        f.add(c); 

        
    }
    
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
