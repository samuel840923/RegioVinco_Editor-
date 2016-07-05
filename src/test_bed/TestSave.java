/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import javafx.scene.paint.Color;
import regioeditor.DataManager;
import regioeditor.Subregion;

/**
 *
 * @author samuelchen
 */
public class TestSave {
    static DataManager data ;
    public static void main(String[] args){
        
    }
    public  static void createAndorra(){
        data = new DataManager();
      
        Subregion a = new Subregion("Orgino","Ordino (town)","Ventura Espot");
        Subregion b = new Subregion("Canillo","Canillo (town)","Enric Casadevall Medrano");
        Subregion c = new Subregion("Encamp","Encamp (town)","Miquel Alís Font");
        Subregion d = new Subregion("Escaldes-Engordany","Escaldes-Engordany (town)","Montserrat Capdevila Pallarés");
        Subregion e = new Subregion("La Massana","La Massana (town)","Josep Areny");
        Subregion f = new Subregion("Andorra la Vella","Andorra la Vella (city)","Maria Rosa Ferrer Obiols");
        Subregion g = new Subregion("Sant Julia de Loria","Sant Julia de Loria (town)","Josep Pintat Forné");
        a.setR(200);a.setG(200);a.setB(200);
        b.setR(198);b.setG(198);b.setB(198);
        c.setR(196);c.setG(196);c.setB(196);
        d.setR(194);d.setG(194);d.setB(194);
        e.setR(192);e.setG(192);e.setB(192);
        f.setR(190);f.setG(190);f.setB(190);
        g.setR(188);g.setG(188);g.setB(188);
        data.addRegion(a);data.addRegion(b);data.addRegion(c);data.addRegion(d);data.addRegion(e);data.addRegion(f);data.addRegion(g);
        data.setBackgroundColor(Color.AQUA);
        data.setBorderColor(Color.BLACK);
        data.setScale(1.5);
        data.setThickness(0.75);
        
        
    }
}
