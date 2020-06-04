/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import Kais.mycompany.myapp.Service.ServiceFormation;
import Kais.mycompany.myapp.entities.Formation;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.xml.Element;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;

/**
 *
 * @author DevelopAndroid
 */
public class FormationDetails extends Form{
    
    public FormationDetails(Form previous) {}
     
    public FormationDetails(Formation formation) {
       Form current;
      current = this;
      setTitle("Détails ");
        setLayout(BoxLayout.y());
        
     //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Label descriptionDetails = new Label(formation.getDescription());
        //  Label imageDetails = new Label(S.getImage());
Button back = $(new Button("Back")).addActionListener(e2 -> {
       
      //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      new Home(current).showBack();}).asComponent(Button.class);
        
        Button btnSupprimer = new Button("supprimer formation");
        
        btnSupprimer.addActionListener((evt) -> {
            ServiceFormation.getInstance().deleteFormation(formation);
            new showListFormation(this).showBack();
        });
      String a="http://localhost/pidev-integration/web/app_dev.php/Projet/pdf/"+formation.getId();
         Button devGuide = new Button("Show PDF");
       
         devGuide.addActionListener(e -> {
       FileSystemStorage fs = FileSystemStorage.getInstance();
      
       String fileName = fs.getAppHomePath()+formation.getDescription();
      
      // fs
       //PDFDocument PdfWriter = new PDFDocument();        
//PdfWriter.createPage(formation.getCategorie());
/*PDFDocument pdf = new PDFDocument();
PdfWriter writer = PdfWriter.getInstance(pdf, outputStream);
pdf.addWriter(writer);*/

        if (!fs.exists(fileName)) {
          Util.downloadUrlToFile(a, fileName,true);
        
            } 
            Display.getInstance().execute(fileName);
   /*   Element response = new Element("Artikelliste");
Element artikel1 = new Element("Artikel");
Element artikel2 = new Element("Artikel");
artikel1.setAttribute("nummer", "5678");
artikel1.setAttribute("bezeichnung", "KettenhandschuhXML");
artikel2.setAttribute("nummer", "1011");
artikel2.setAttribute("bezeichnung", "MesserXML");

response.addChild(artikel1);
response.addChild(artikel2);

OutputStream os = Storage.getInstance().createOutputStream("test1.pdf");
OutputStreamWriter writer = new OutputStreamWriter(os);
PDFDocument PdfWriter = new PDFDocument();        
PdfWriter.getPage(TOP);

FileSystemStorage.getInstance().openOutputStream("test1234.pdf").write(response.toString().getBytes());*/
       
         });
         btnSupprimer.addActionListener((e) -> Dialog.show("Supprimer Formation", "Formation supprimé avec succée", "OK", null));
        add(descriptionDetails);
       addAll(btnSupprimer,devGuide);
        Button btnModifier = new Button("modifier formation");
       add(btnModifier);
       add(back);
        show();
        btnModifier.addActionListener(e -> new ModifierFormationForm(current, formation).show());

    }
}
