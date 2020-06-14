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

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Evenement;
import static java.lang.Thread.sleep;
import com.mycompany.gui.*;

/**
 *
 * @author Khawla
 */
public class SplashScreenForm extends Form {

    public SplashScreenForm(Resources res) {
        
        
       
        /*******************Debut Template Code**************************************/
        
        super(new BorderLayout());
        template(res);
        
      /*******************fin Template Code**************************************/

      
      /*******************bloqué processus 5 mili afin d'ouvrir Login Form******/
        Thread timmer=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);

                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                finally {
                 new LoginForm(res).show();

                }
            }
                
        };
        timmer.start();
    }
 /*******************fin Template Code**************************************/

    
    public void template(Resources res) {
        getTitleArea().setUIID("Container");
        setUIID("Welcome");
        Tabs t = new Tabs();
        t.hideTabs();
        t.setUIID("Container");
        t.getContentPane().setUIID("Container");
        add(BorderLayout.CENTER, t);
                    add(BorderLayout.CENTER,  new Label(res.getImage("loogo.png"),"LogoLabel"));
        String[] messages = {
            "Bienvenue a  Tunisan Got Talent "            
        };
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xcccccc);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(5, 5, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xff2d55);
        g.setAntiAliased(true);
        g.fillArc(0, 0, 5, 5, 0, 360);
        RadioButton[] rbs = new RadioButton[t.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        
         Container welcomeNoteArea = BoxLayout.encloseY(message,
                LayeredLayout.encloseIn(
                        radioContainer
                )
        );
        welcomeNoteArea.setUIID("WelcomeNoteArea");
        add(BorderLayout.SOUTH, welcomeNoteArea);
        
    }
  
}
