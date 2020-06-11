/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.gui.*;
import com.mycompany.services.UtilisateurService;
/**
 *
 * @author khawla
 */
public class LoginForm extends BaseForm {
    
    
 ArrayList<Utilisateur> utilisateur = new ArrayList<>();
 ConnectionRequest connectionRequest;
    public LoginForm(Resources res) {
       
        super(new BorderLayout());
        template(res);
        
      // facebook.addActionListener(e -> new UserForm().show());
                
    }
    public void template(Resources res) {
           if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
    getTitleArea().setUIID("Container");
    setUIID("IMGLogin");
    add(BorderLayout.NORTH,  new Label(res.getImage("loogo.png"),"LogoLabel"));
    TextField username1 = new TextField("", "Username", 20, TextField.ANY);
    TextField password1 = new TextField("", "Password", 20, TextField.ANY);
    username1.setSingleLineTextArea(false);
    password1.setSingleLineTextArea(false);
    Button signIn = new Button("Sign In");
    Button signUp = new Button("Sign Up");
    signUp.addActionListener(e -> new SignUpForm(res).show());
    Button l =new Button("oublier mot de passe?", "CenterLabel");
    Label doneHaveAnAccount = new Label("Vous n'avez aucune compte ?");
        Container content = BoxLayout.encloseY(
                username1,
                createLineSeparator(),
                password1,
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter( signUp),l
       );
               l.addActionListener((e) ->{
                  // new ActivateForm(res).show();
               });


       content.setScrollableY(true);
       add(BorderLayout.CENTER_BEHAVIOR_CENTER, content);
       signIn.requestFocus();
       signIn.addActionListener((e) ->{
             UtilisateurService.getInstance().login(username1, password1, res);
            });
 
    }
}
