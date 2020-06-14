/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Reclamation;
import Entite.User;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.AjouterReclamationn;
import service.reclamation;

/**
 *
 * @author Nasri
 */
public class AjouterReclamation {
    
  Form f;
    Label sub;
    Label   etat , reponse , liste , sub2 ,reponse2;
    Command cmd4; 
    Button ad; 

    Font smallItalicMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL);
    Font mediumItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
      Font largeItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE);
    User user; 
    TextArea des;
    TextField sujet; 
    public AjouterReclamation(Resources theme , User user) {

        this.user=user; 
         cmd4 = new Command("");
         reponse = new Label(""); 
         reponse2= new Label(""); 


   
          f = new Form(); 
      
         
          liste = new Label("  : Ajouter une reclamation"); 
          liste.getStyle().setFgColor(5876137);
          liste.getStyle().setFont(largeItalicSystemFont);
          f.add(liste); 
          Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

           c1.add(reponse); 
          sujet = new TextField("sujet");
          c1.add(sujet); 
       
          
        des = new TextArea(10, 50);
        des.setHint("description ici");
        c1.add(des); 
      
        AjouterReclamationn serv = new AjouterReclamationn(user); 
       ListeReclamation ll = new ListeReclamation(theme,user); 
                   HomePage H = new HomePage(theme); 


        ad = new Button("Ajouter reclamation");
        ad.addActionListener((e)->
        {
            serv.AjoutReclmation(sujet.getText(), des.getText());
            reponse2.setText("reclamation ajouter avec succées");
            ll.getF().show();

        });
        
        c1.add(ad); 
        c1.add(reponse2); 

        
        f.add(c1); 
        
        
          f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);

          theme = UIManager.initFirstTheme("/theme");
          ListeReclamation listee = new ListeReclamation(theme, user); 


           f.getToolbar().addCommandToSideMenu("Aceuill", null,
        e->
        {
            UserInterface u = new UserInterface(user); 
            u.getF().show();
            
           
        });  
        
        f.getToolbar().addCommandToSideMenu("modifier profil", null,
        e->
        {
            EditProfil aa = new EditProfil(user); 
            aa.getF().show();
            
          
        });
        f.getToolbar().addCommandToSideMenu("liste de réclamation", null,
        e->{
            listee.getF().show();
          
           
        });
        
      f.getToolbar().addCommandToSideMenu("déconnexion", null,
        e->{
            
            H.getF().show();
            System.err.println("test valider");
        });
        

        
        

    }

  

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}

