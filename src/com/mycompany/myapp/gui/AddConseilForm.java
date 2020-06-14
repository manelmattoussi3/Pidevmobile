/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.service.CandidatService;
import com.mycompany.myapp.Service.ServiceCoach;
import com.mycompany.myapp.Service.ServiceConseil;
import com.mycompany.myapp.entities.Candidat;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.Conseil;
import com.codename1.components.MultiButton;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author DevelopAndroid
 */
public class AddConseilForm extends Form {

    Form f;
 String destinationUrl = "";
    public AddConseilForm(Form previous) {
        f = this;
        setTitle("Ajouter Conseil");
        TextModeLayout tl = new TextModeLayout(3, 2);
        setLayout(tl);

        TextComponent description = new TextComponent().label("Description").multiline(true);

        TextComponent categorie = new TextComponent().label("Categorie").multiline(true);
 ArrayList<Coach> listCoach = ServiceCoach.getInstance().getCoach();
        ComboBox<String> comboCoach = new ComboBox<>();
        for (Coach coach : listCoach) {
            comboCoach.addItem(coach.getNom());
        }
        comboCoach.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        
         ArrayList<Candidat> listCandidat = CandidatService.getInstance().getCandidat();
        ComboBox<String> comboCandidat = new ComboBox<>();
        for (Candidat candidat : listCandidat) {
            comboCandidat.addItem(candidat.getNom());
        }
       comboCandidat.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
         f.add(tl.createConstraint().widthPercentage(60), categorie);
       
       
        f.add(tl.createConstraint().horizontalSpan(2), description);
      
        f.add( comboCoach);
        f.add( comboCandidat);
        Validator val = new Validator();
        val.addConstraint(description, new LengthConstraint(2));
        val.addConstraint(categorie, new LengthConstraint(2));
         f.setEditOnShow(categorie.getField());

        ArrayList<Conseil> listeConseil = ServiceConseil.getInstance().getAllConseils();
 Button testIm = new Button("Browse Images");
        testIm.addActionListener((ActionEvent e)->{
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(false, ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {                    
                    if(e2!=null && e2.getSource()!=null) {
                       // String path = (String)e2.getSource();
                       String path = "C:\\wamp64\\www\\pidev-integration\\web\\images\\";
                        destinationUrl = "bottom2.jpg";
                        System.out.println(path);
                        Random random = new Random();
                        String letters = "abcdefghijklmnopqrstuvwxyz";         
                        StringBuilder randomName = new StringBuilder("") ;
                        for(int i = 0; i < 10; i++) {
                            randomName.append(letters.charAt(random.nextInt(letters.length())));
                        } 
                        //destinationUrl = "src/Images/" + randomName + ".jpg";
                        //Path destinationPath = Paths.get(destinationUrl);
                        //Path sourcePath = Paths.get(path);
                       /* try {
                            Files.copy(sourcePath , destinationPath);
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterSponsoringForm.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                        try {
                            //Image img = Image.createImage(path);
                            if (true) return;
                        } catch (Exception ex) {
                            Log.e(ex);
                        }
                    }
                  
               });
            }
        });
         Button btnvalider = new Button("Ajouter Conseil");
         btnvalider.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent evt) {
                
                if ((description.getText().length()==0)||(categorie.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", "ok", null);
                } else {
                   
                    Conseil conseil = new Conseil(description.getText(),categorie.getText(), destinationUrl,  listCoach.get(comboCoach.getSelectedIndex()).getIdcoach(),listCandidat.get(comboCandidat.getSelectedIndex()).getId());
          
                    ServiceConseil.getInstance().addConseil(conseil);
                    //new ListeSponsoringForm(current).showBack();
                     
            new ShowListConseil(previous).showBack();
      
                }
            }
        });
       f.add(tl.createConstraint().widthPercentage(40), testIm);

        // hi.show();
        f.setTintColor(0x77EE8CAE);
        btnvalider.addActionListener((e) -> Dialog.show("Ajouter Conseil", "Conseil ajouté avec succée", "OK", null));

        f.add(btnvalider);
       
        f.show();

    }

}
