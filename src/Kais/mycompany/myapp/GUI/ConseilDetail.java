/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import Kais.mycompany.myapp.Service.ServiceConseil;
import Kais.mycompany.myapp.entities.Conseil;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author DevelopAndroid
 */
public class ConseilDetail extends Form{
;
    public ConseilDetail(Form previous) {}
    public ConseilDetail(Conseil conseil) {
          Form current;
      current = this;
      setTitle("Détails ");
        setLayout(BoxLayout.y());
        
     //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Label descriptionDetails = new Label(conseil.getDescriptioncons());
        //  Label imageDetails = new Label(S.getImage());
Button back = $(new Button("Back")).addActionListener(e2 -> {
       
      //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      new Home(current).showBack();}).asComponent(Button.class);
        
        Button btnSupprimer = new Button("supprimer conseil");
        
        btnSupprimer.addActionListener((evt) -> {
            ServiceConseil.getInstance().deleteConseil(conseil);
            new ShowListConseil(this).showBack();
        });
         add(descriptionDetails);
       add(btnSupprimer);
       
       add(back);
        show();
    }
    
    
}
