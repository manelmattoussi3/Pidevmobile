/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.Reclamation;
import Entite.User;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.reclamation;

/**
 *
 * @author Nasri
 */
public class ListeReclamation {

    Form f;
    Label sub;
    Label des , etat , reponse , liste , sub2 ;
    Command cmd4; 

    Font smallItalicMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL);
    Font mediumItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
      Font largeItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE);
    User user; 

    public ListeReclamation(Resources theme , User user) {

         cmd4 = new Command("");
         this.user=user; 
    
            
     
           f = new Form(); 
          ArrayList<Reclamation> listreclamtion = new ArrayList<>();
          reclamation service = new reclamation(user); 
          listreclamtion = service.listeReclmation(user.getId());
          System.out.println(listreclamtion);
          liste = new Label("liste des reclamations"); 
          liste.getStyle().setFgColor(5876137);
          liste.getStyle().setFont(largeItalicSystemFont);
          f.add(liste); 
          
        
         for (Reclamation r : listreclamtion ) 
         {
               System.err.println(r.getMail());
              
            Container contY = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String sujet= "sujet: "+r.getSujet(); 
            sub = new Label(sujet);
            String reclamationn="reclamation: "+r.getReclamation(); 
            sub2=new Label(reclamationn); 
            des = new Label(reclamationn );
            String etatt= "etat:  "+r.getEtat(); 
            etat = new Label(etatt);
            String reponnse= "Reponse: "+r.getReponse(); 
            reponse = new Label(reponnse); 
            
            //des.getStyle().setFgColor(5855577);
            des.getStyle().setFont(smallItalicMonospaceFont);
            sub.getStyle().setFont(smallItalicMonospaceFont);
       
            etat.getStyle().setFont(smallItalicMonospaceFont); 
            reponse.getStyle().setFont(smallItalicMonospaceFont); 
            
            contY.add(sub);
          
            contY.add(des);
            contY.add(etat);
            contY.add(reponse);
            
            contY.add("________________________________________________");

            
            f.add(contY);
         }
         
                  
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);
        f.getToolbar().addCommandToSideMenu(cmd4);

          theme = UIManager.initFirstTheme("/theme");

        f.getToolbar().addCommandToSideMenu("Aceuill", null,
        e->
        {
            UserInterface u = new UserInterface(user); 
            u.getF().show();
            
           
        });
        f.getToolbar().addCommandToSideMenu("modifier profil", null,
        e->
        {
            EditProfil u = new EditProfil(user); 
            u.getF().show();
          
        });
          f.getToolbar().addCommandToSideMenu("envoyer une reclamation", null,
        e->
        {
            
           
          
        });
       
        
        f.getToolbar().addCommandToSideMenu("déconnexion", null,
        e->{
            
           
        });
        

    }

  

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
