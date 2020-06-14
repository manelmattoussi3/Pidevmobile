/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import API.SMS;
import Entite.User;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.ServiceUser;
import service.forgetPassword;

/**
 *
 * @author Nasri
 */
public class Affichage {

    Form f;
    SpanLabel lb;
    private String aa;
    private  UserInterface user_interface; 
    private Resources theme;
    Label erreur ; 
    private    ArrayList<User> listuser = new ArrayList<>();


  
    public Affichage() {
        
        f = new Form(new FlowLayout(CENTER, CENTER));
     
        
        lb = new SpanLabel("");

        TextField login = new TextField("USERNAME", "Login", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("PASSWORD", "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        Button loginButton = new Button("Se connecter");
        Button forgetPassword = new Button("mot de passe oublier");
        Container c = new Container(BoxLayout.y()); 

        c.addAll( login,  password , loginButton, forgetPassword,lb); 
       
        f.add(c);
        ServiceUser serviceTask=new ServiceUser();
        String aa ;
        SMS sms = new SMS(); 
        forgetPassword pass = new forgetPassword(); 
        String pass1= pass.generatePassword(8).toString(); 


      //  aa=Direction("user"); 
        forgetPassword.addActionListener((e)->
        {
         this.listuser = serviceTask.idmail(login.getText()); 
         if (this.listuser.size()==0)
         {
                lb.setText(" adresse mail invalide");
                

         }
         else 
         {
             try
             {
                 
               serviceTask.forget_password(this.listuser.get(0).getId(),pass1);
               lb.setText(" le mot de passe est changée ");
               sms.envoyer(29216716, "ton compte est connectée " +pass1);
               
             }
             catch (Exception ex)
             {
                 System.err.println("ereeur");  
             }
                  
             

         }
             
            
        });
        
        loginButton.addActionListener((ev)->
        {
            try
            {
               
                this.aa =  serviceTask.Direction(serviceTask.Testconnexion(login.getText(), password.getText()).getRole());

            }
            catch(Exception ex )
            {
                System.err.println("pas valide ");
               lb.setText(" mot de passe ou adresse mail incorrect ");

            }
          if (this.aa=="user")
          {
                this.user_interface = new UserInterface(serviceTask.Testconnexion(login.getText(), password.getText())); 
                user_interface.getF().show();
          }
          if(this.aa=="jury")
          {
              
          }
           if(this.aa=="sponsor")
          {
              
          }
           if(this.aa=="coach")
          {
              
          } 
          
        
        }  
                
                
        
        );
    
          theme = UIManager.initFirstTheme("/theme");
      
          f.getToolbar().addCommandToRightBar("back", null, (ev)->
          {
              HomePage H = new HomePage(theme); 
              H.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}