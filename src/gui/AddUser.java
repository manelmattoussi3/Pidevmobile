/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entite.User;
import Entite.UserAdd;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;
import service.AdUser;

/**
 *
 * @author Nasri
 */
public class AddUser 
{   
    Form f;
    SpanLabel lb;
    private String aa;
    private  UserInterface user_interface; 
        private Resources theme;

  
    
    public  AddUser()
    {
        
       
        
        Container c = new Container(BoxLayout.y());
        f = new Form("Inscription");
        
        TextField username = new TextField(null, "username");
        c.add(username);
        TextField mail = new TextField(null, "mail");
        c.add(mail);
        TextField nom = new TextField(null, "Nom");
        c.add(nom);
        TextField prenom = new TextField(null, "Prénom");
        c.add(prenom);
        TextField telephone = new TextField(null, "telephone");
        c.add(telephone);
        
        
       
        Picker date = new Picker();
        c.add(date);
        
        CheckBox spectateur = new CheckBox("spectateur");
        c.add(spectateur);
        CheckBox admin = new CheckBox("admin");
        c.add(admin);
        CheckBox tennis = new CheckBox("jury");
        c.add(tennis);
        CheckBox coatch = new CheckBox("coatch");
        c.add(coatch);
        CheckBox sponsor = new CheckBox("sponsor");
        c.add(sponsor);
        
        TextField password = new TextField("", "password", 0, TextField.PASSWORD);
        c.add(password);
   

        Button inscription = new Button("Inscription"); 
          c.add(inscription);
          

        f.add(c);
      
        AdUser a = new AdUser(); 
        /* entite nouveau utlisateur*/
        UserAdd user = new UserAdd(); 
       
        /* size */
        int size=  a.listeUsers().size(); 
        /* traitement */
        inscription.addActionListener((ev)->
        {
            user.setNb(size+1);
            user.setNom(nom.getText());
            user.setPrenom(prenom.getText());
            user.setDate(prenom.getText());
            user.setMail(mail.getText());
           
            user.setPassword(password.getText()); 
            float tel = Float.parseFloat(telephone.getText());
            user.setTel((int)tel);
            user.setUsername(username.getText());
             if (spectateur.isSelected()) 
                {
                    user.setRole("ROLE_SPECTATEUR");
                }
             if (coatch.isSelected())
             {
                 user.setRole("ROLE_COACH");
             }
             if (admin.isSelected())
             {
                 user.setRole("ROLE_ADMIN");
             }
             if (sponsor.isSelected())
             {
                 user.setRole("ROLE_SPONSOR");
             }
             
             if    (Recherche(a.listeUsers(), username.getText(), mail.getText())=="valide")
             {
                          a.AddUserr(user); 
                          Affichage afff = new Affichage(); 
                          afff.getF().show();

             }
             else
             {
                 System.err.println("email ou usename deja inscrit");
             }
            
            
            
        });
                  theme = UIManager.initFirstTheme("/theme");

         f.getToolbar().addCommandToRightBar("back", null, (ev)->
          {
              HomePage H = new HomePage(theme); 
              H.getF().show();
          });
        
    }
    
      public Form getF() 
      {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    ArrayList<User> listuser = new ArrayList<>();
    public String Recherche(ArrayList<User> listuser , String username ,String email)
     {
         try
         {
              for (int i=0; i<listuser.size(); i++)
                    {
         //             if ((listuser.get(i).getUsername()==username)&& (listuser.get(i).getEmail()==email))
                       String u= listuser.get(i).getUsername(); 
                       String u1=listuser.get(i).getEmail(); 
                       if ((u.equals(username)))

                            {
                                
                                System.err.println("teest");


                                return "false"; 
                            }
                       if ((u1.equals(email)))
                       {
                                  return "false"; 
                         
                       }
                       
                   }
         }
         catch(Exception ex)
         {
         }
        
                     System.err.println("accepter ");

   
         return "valide"; 
     }

}
