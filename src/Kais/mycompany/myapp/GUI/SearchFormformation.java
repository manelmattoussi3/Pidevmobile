/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.GUI;

import Kais.mycompany.myapp.Service.ServiceFormation;
import Kais.mycompany.myapp.entities.Formation;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author DevelopAndroid
 */
public class SearchFormformation extends Form {

    Form hi;
    Formation f;
    ServiceFormation bS = new ServiceFormation();
    Container listeContainer = new Container(BoxLayout.y());
//String description;
    public SearchFormformation(Form previous) {}
    

   public SearchFormformation(String d) {
        hi = this;
        setTitle("Tunisian Got Talent");
        setLayout(BoxLayout.y());
        Button back = $(new Button("Back")).addActionListener(e2 -> {

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            new showListFormation(hi).showBack();
        }).asComponent(Button.class);
        // Form F2 = new Form(BoxLayout.y());

        ArrayList<Formation> liche = bS.ChercherTopic(d);
        for (Formation formation : liche) {
             Label mo = new Label("Description:");
            Label descriptio = new Label(formation.getDescription());
            //titre.getAllStyles().setFgColor(0x0c42c0);
            Label mok = new Label("Date_Debut:");
            Label datedebut = new Label(formation.getDate_Debut());
            Label mom = new Label("Date_Fin:");
            Label datefin = new Label(formation.getDate_Fin());
            Label moll = new Label("Nbr_Place:");
            Label nbrplace = new Label(formation.getNbr_Place());
            Label categori = new Label("Categorie:");
            Label cate = new Label(formation.getCategorie());

            //   searchContainer.add(srchText);
            //  searchContainer.add(searchFiled);
            // wholeContainer.add(searchContainer);
            //    add(wholeContainer);
            //wholeContainer.add(listeContainer);
            //wholeContainer.add(listeContainer);
//             add(wholeContainer);
            //Service testing
            Button ev = new Button("Details");

            ev.addActionListener((evt) -> {
                //new GuideDetailsForm(prev ).show();
                // bS.AfficherDetails(b);
                // new GuideDetailsForm(prev, b, CENTER).show();
                new FormationDetails(formation).show();
            });

            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            Container c4 = new Container(BoxLayout.x());
            Container c5 = new Container(BoxLayout.x());
             c1.addAll(mo, descriptio);
            c2.addAll(mok, datedebut);
            c3.addAll(mom, datefin);
            c4.addAll(moll,nbrplace);
            c5.addAll(categori,cate);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
            Label informations = new Label("Crée le" + (formation.getDate_Debut()));
            informations.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            System.out.println(formation);
           // Container InfoContainer = new Container(BoxLayout.y());
            listeContainer.add(c1);
            listeContainer.add(c2);
            listeContainer.add(c3);
            listeContainer.add(c4);
            listeContainer.add(c5);
            // listeContainer.add(informations);
            listeContainer.addComponent(ev);
             //listeContainer.add(searchContainer);
           // Container info = new Container(BoxLayout.x());
            
           // listeContainer.add(info);
          

        }
          listeContainer.add(back);
        hi.add(listeContainer);
        hi.show();

    }
}
