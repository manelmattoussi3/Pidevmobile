/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;


import Kais.mycompany.myapp.Service.ServiceConseil;
import Kais.mycompany.myapp.entities.Conseil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author DevelopAndroid
 */
public class SearchConseil extends Form{
 
    Form hi;
    Conseil c;
    ServiceConseil bS = new ServiceConseil();
    Container listeContainer = new Container(BoxLayout.y());
//String description;
    public SearchConseil(Form previous) {}
    

   public SearchConseil(String d) {
        hi = this;
        setTitle("Tunisian Got Talent");
        setLayout(BoxLayout.y());
        Button back = $(new Button("Back")).addActionListener(e2 -> {

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            new ShowListConseil(hi).showBack();
        }).asComponent(Button.class);
        // Form F2 = new Form(BoxLayout.y());

        ArrayList<Conseil> liche = bS.ChercherTopic(d);
        for (Conseil con : liche) {
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
           
            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            c1.addAll(mo, ldescription);
            c2.addAll(mok, categori);
           c3.add(imgv);
              listeContainer.add(c3);
            listeContainer.add(c2);
           
            listeContainer.add(c1);
           // c.add(c6);
           
            /*ldescription.addPointerPressedListener((evt3) -> {
                new FormationDetails(f).show();
            });*/

            
          

        }
          listeContainer.add(back);
        hi.add(listeContainer);
        hi.show();

    }   
}
