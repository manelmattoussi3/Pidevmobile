/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kais.mycompany.myapp.Service;

import Kais.mycompany.myapp.entities.Coach;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DevelopAndroid
 */
public class ServiceCoach {

    public ArrayList<Coach> coach;

    public static ServiceCoach instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCoach() {
        req = new ConnectionRequest();
    }

    public static ServiceCoach getInstance() {
        if (instance == null) {
            instance = new ServiceCoach();
        }
        return instance;
    }

    public ArrayList<Coach> parseCoach(String jsonText) {
        try {
            coach = new ArrayList<Coach>();
            JSONParser j = new JSONParser();
            Map<String, Object> coachsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) coachsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Coach c = new Coach();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setIdcoach((int)id);
                c.setNom(obj.get("nom").toString());
                c.setPrenom(obj.get("prenom").toString());

                coach.add(c);
            }

        } catch (IOException ex) {

        }
        return coach;
    }

    public ArrayList<Coach> getCoach() {
        String url = "http://localhost/pidev-integration/web/app_dev.php/Projet/Affichecoach";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                coach = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return coach;
    }
}
