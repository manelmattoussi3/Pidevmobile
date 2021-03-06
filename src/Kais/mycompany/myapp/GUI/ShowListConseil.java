/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import Kais.mycompany.myapp.Service.ServiceConseil;
import Kais.mycompany.myapp.entities.Coach;
import Kais.mycompany.myapp.entities.Conseil;
import Kais.mycompany.myapp.entities.Formation;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;

/**
 *
 * @author DevelopAndroid
 */
public class ShowListConseil extends Form{
    

    Form liste;
    public Conseil conseil;
    public Coach co;

    public ShowListConseil(Form previous) {
       

        liste = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setTitle("Tunisian Got Talent");
        setLayout(BoxLayout.y());
         ServiceConseil sc = new ServiceConseil();

        ArrayList<Conseil> listeConseil = new ArrayList<>();

        listeConseil = sc.getAllConseils();
 Container searchContainer = new Container(BoxLayout.x());
        Style s = UIManager.getInstance().getComponentStyle("Tab");
 TextField searchField = new TextField("", " Search");
            FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
            /*      searchField.addDataChangedListener((int d1, int d2) ->{
         
          //  if (!searchField.getText().equals("") ) {
              //  bS.rechercheList(searchFiled.getText());
               String d = searchField.getText();
             bS.searchIconChercherTopic(d);
            // this.show();
            //System.out.println("nnn"+liche);

             // bS.rechercheList(searchField.getText());
             
            //} else {
             //   bS.getList();

            //}
        });
        //searchContainer.add(srchText);
             */
            Button searchbt = new Button("search");
            searchbt.getAllStyles().setAlignment(Component.RIGHT);
            /* searchbt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
              
                   bS.ChercherTopic(searchField.getText());
                }
       });*/
 /*     searchbt.addActionListener((l) -> {
                bS.ChercherTopic(searchField.getText());
            searchContainer.setVisible(true);
            this.revalidate();
            //PaiementForm pf = new PaiementForm();
            //pf.getF().show();
        });*/
 
            searchContainer.add(searchbt);
            searchContainer.add(searchIcon);
            searchContainer.add(searchField);
            this.add(searchContainer);

            searchbt.addActionListener((e) -> {
                new SearchConseil( searchField.getText()).show();

            });
       

        for (Conseil con : listeConseil) {
            Container c = new Container(BoxLayout.y());
            Label mo = new Label("Description:");
            mo.getAllStyles().setBgColor(0xEE8CAE);
            Label ldescription = new Label(con.getDescriptioncons());
            Label mok = new Label("Categorie:");
            Label categori = new Label(con.getCategoricons());
         /*  EncodedImage img =  EncodedImage.createFromImage(Image.createImage(700, 700, 0xffff0000), true);
                     Image image1 = URLImage.createToStorage(img,
               "http://localhost/pidev/web/images/b3.jpg"+con.getImagecons(),
                "http://localhost/pidev/web/images/",
                URLImage.RESIZE_SCALE_TO_FILL);

        ImageViewer imageView = new ImageViewer(image1);*/
          EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),450), true);
                        URLImage imgg= URLImage.createToStorage(img,con.getImagecons(), "http://localhost/pidev-integration/web/images/"+con.getImagecons());
                        imgg.fetch();
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
                         ldescription.addPointerPressedListener((evt3) -> {
                new ConseilDetail(con).show();
            });
            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            c1.addAll(mo, ldescription);
            c2.addAll(mok, categori);
           c3.add(imgv);
              c.add(c3);
            c.add(c2);
           
            c.add(c1);
           // c.add(c6);
           
            /*ldescription.addPointerPressedListener((evt3) -> {
                new FormationDetails(f).show();
            });*/

            liste.add(c);
           
        }

        liste.show();

        // liste.show();
//});
    }

}
