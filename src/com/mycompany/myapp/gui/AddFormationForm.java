/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Formation;
import com.codename1.ui.FontImage;
import com.mycompany.myapp.Service.ServiceFormation;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import java.util.ArrayList;
import com.mycompany.myapp.Service.ServiceCoach;
import com.mycompany.myapp.entities.Coach;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Image;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.list.GenericListCellRenderer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 *
 * @author DevelopAndroid
 */
public class AddFormationForm extends Form {

    //String destinationUrl = "";
    Form f;

    public AddFormationForm(Form previous) {

        f = this;
        setTitle("Ajouter Formation");
        TextModeLayout tl = new TextModeLayout(3, 2);
        setLayout(tl);
        //  Form listeetudiants = new Form("Ajouter Formation ", BoxLayout.y());
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        // TextField tfid = new TextField(""," ID");

        // TextField tfdescription = new TextField("", " Description");
        // TextField tfdatedebut = new TextField("","Date_Debut");
        //TextField tfdatefin = new TextField("","Date_Fin");
        // Picker date_debut = new Picker();
        // Picker date_fin = new Picker();
        // TextField tfnbrplace = new TextField("", "Nbr_Place");
        //  Validator val = new Validator();
//val.addConstraint(tfdescription, new LengthConstraint(2));
//val.addConstraint(price, new NumericConstraint(true));
//Form f = new Form("Pixel Perfect", tl);
//TextComponent title = new TextComponent().label("Title");
//TextComponent price = new TextComponent().label("Nbr_Place");
//TextComponent location = new TextComponent().label("Location");
        Picker date_debut = new Picker();
        // PickerComponent date_debut = PickerComponent.createDate(new Date()).label("Date_Debut");
        //PickerComponent date_fin = PickerComponent.createDate(new Date()).label("Date_Fin");
        Picker date_fin = new Picker();
        TextComponent description = new TextComponent().label("Description").multiline(true);
        TextComponent tfnbrplace = new TextComponent().label("Nbr_Place");
        TextComponent categorie = new TextComponent().label("Categorie").multiline(true);
        TextArea pdff = new TextArea();
        Label mok = new Label("Date_Debut");
        Label mom = new Label("Date_Fin");
        Label pd = new Label("Ecrivez une nouvelle Formation");
        ArrayList<Coach> listCoach = ServiceCoach.getInstance().getCoach();
        ComboBox<String> comboCoach = new ComboBox<>();
        for (Coach coach : listCoach) {
            comboCoach.addItem(coach.getNom());
        }
        comboCoach.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        f.add(tl.createConstraint().widthPercentage(60), categorie);
        // f.add(tfnbrplace);
        f.add(tl.createConstraint().widthPercentage(40), tfnbrplace);
        //f.add(tl.createConstraint().widthPercentage(40), date_debut);
        f.addAll(mok, date_debut);
        f.addAll(mom, date_fin);
        f.add(tl.createConstraint().horizontalSpan(2), description);
        f.addAll(pd, pdff);
        f.add(tl.createConstraint().horizontalSpan(2), comboCoach);
        Validator val = new Validator();
        val.addConstraint(description, new LengthConstraint(2));
        val.addConstraint(categorie, new LengthConstraint(2));
        val.addConstraint(pdff, new LengthConstraint(2));
        val.addConstraint(tfnbrplace, new NumericConstraint(true));

        f.setEditOnShow(categorie.getField());

        ArrayList<Formation> listeFormation = ServiceFormation.getInstance().getAllFormations();

        Button btnvalider = new Button("Ajouter Formation");

        btnvalider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((description.getText().length() == 0) || (tfnbrplace.getText().length() == 0) || (categorie.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all the fields", new Command("ok"));
                } else {
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = formater.format(date_debut.getDate());
                    String dateFin = formater.format(date_fin.getDate());
                    Formation formation = new Formation(description.getText(), dateDebut, dateFin, tfnbrplace.getText(), categorie.getText(), listCoach.get(comboCoach.getSelectedIndex()).getIdcoach(),pdff.getText());

                    ServiceFormation.getInstance().addFormation(formation);
                    /*  try {
                        Formation f = new Formation(description.getText(), date_debut.getText(), date_fin.getText(), Integer.parseInt(tfnbrplace.getText()));
                        if (ServiceFormation.getInstance().addFormation(f)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "nbr_place doit etre un nombre", new Command("OK"));
                    }
                     */
                    new showListFormation(previous).showBack();

                }

            }
        });
        Button b = new Button("Browse Files");
        CheckBox multiSelect = new CheckBox("Multi-select");
        b.addActionListener(e -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf, .csv, text/plain", e2 -> {
                    if (e2 == null || e2.getSource() == null) {
                        f.add("No file was selected");
                        f.revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);

                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        f.add("No file was selected");
                        f.revalidate();
                    } else {
                        System.out.println("File path: " + file);

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                        }
                        FileSystemStorage fs = FileSystemStorage.getInstance();
                        if ("txt".equals(extension)) {

                            try {
                                InputStream fis = fs.openInputStream(file);
                                f.addComponent(new SpanLabel(Util.readToString(fis)));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }
                        } else {
                            f.add("Selected file " + file);
                        }
                        File f = new File(file);
                        String contents = "";
                        try (InputStream fis = fs.openInputStream(file)) {
                            contents = Util.readToString(fis);
                        } catch (Exception ex) {
                            Log.e(ex);
                            return;
                        }
                        contents += "\nTest";
                        try (OutputStreamWriter writer = new OutputStreamWriter(fs.openOutputStream(file, 0), "UTF-8")) {
                            writer.write(contents);
                        } catch (IOException ex) {
                            Log.e(ex);
                            return;
                        }

                    }
                    f.revalidate();
                });
            }
        });
        f.addComponent(tl.createConstraint().widthPercentage(60), b);

        Button testImage = new Button("Browse Images");
        testImage.addActionListener(e -> {
            if (FileChooser.isAvailable()) {

                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2 -> {
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            try {
                                Image img = Image.createImage(path);
                                f.add(new Label(img));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }

                        }
                        return;

                    }
                    if (e2 != null && e2.getSource() != null) {

                        String file = (String) e2.getSource();
                        try {
                            Image img = Image.createImage(file);
                            f.add(new Label(img));
                            if (true) {
                                return;
                            }
                        } catch (Exception ex) {
                            Log.e(ex);
                        }

                        String filestack = "http://solutions.weblite.ca/testupload.php";

                        MultipartRequest request = new MultipartRequest();

                        request.setUrl(filestack);

                        request.setPost(true);
                        try {
                            request.addData("fileUpload", file, "/");

                            request.setFilename("fileUpload", "myfile.png");

                            request.setReadResponseForErrors(true);
                            NetworkManager.getInstance().addToQueueAndWait(request);
                        } catch (Throwable t) {
                            Log.e(t);
                        }
                    }
                });
            }
        });
        f.add(tl.createConstraint().widthPercentage(40), testImage);

        // hi.show();
        f.setTintColor(0x77EE8CAE);
        btnvalider.addActionListener((e) -> Dialog.show("Ajouter Formation", "Formation ajouté avec succée", "OK", null));

        f.add(tl.createConstraint().horizontalSpan(2), btnvalider);
        f.add(multiSelect);
        f.show();
//current.add(showDialog);
        // addAll(description, date_debut, date_fin, categorie,tfnbrplace, btnvalider);
//current.show();*/
    }

}
