/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.AddFormationForm;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author DevelopAndroid
 */
public class openpdf extends Form{
Form hi;
    public openpdf(Form previous) {
        hi=this;
          //Form hi = new Form("Hi World");
          setTitle("bbbb");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Button back = $(new Button("Back")).addActionListener(e2 -> {
       
      //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      new AddFormationForm(previous).showBack();}).asComponent(Button.class);
         hi.add(back);
        hi.addComponent(new Label("Hi World"));
        Button b = new Button("Browse Files");
        CheckBox multiSelect = new CheckBox("Multi-select");
        b.addActionListener(e -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf, .csv, text/plain", e2 -> {
                    if (e2 == null || e2.getSource() == null) {
                        hi.add("No file was selected");
                        hi.revalidate();
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
                        hi.add("No file was selected");
                        hi.revalidate();
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
                                hi.addComponent(new SpanLabel(Util.readToString(fis)));
                            } catch (Exception ex) {
                                Log.e(ex);
                            }
                        } else {
                            hi.add("Selected file " + file);
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
                    hi.revalidate();
                });
            }
        });
        hi.addComponent(b);

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
                                hi.add(new Label(img));
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
                            hi.add(new Label(img));
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
        hi.add(testImage);
        hi.add(multiSelect);
        hi.show();
    }
    }
    

