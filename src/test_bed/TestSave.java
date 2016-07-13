/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import regioeditor.DataManager;
import regioeditor.FileManager;
import regioeditor.Subregion;
import saf.components.AppDataComponent;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;


/**
 *
 * @author samuelchen
 */



public class TestSave {
    
   public static DataManager data ;
 
      
    
    public static void main(String[] args) throws IOException{
        
        save("S");
    }
    public static void save(String s) throws FileNotFoundException, IOException{
        String filePath = null;
        if(s.equals("A")){
            createAndorra();
            filePath = "./HW5SampleData/work/Andorra.json";
        }
        else if(s.equals("SM")){
             createSanMarino();
              filePath = "./HW5SampleData/work/SanMarino.json";
        }
         else if(s.equals("S")){
            createSlovakia();
             filePath = "./HW5SampleData/work/Slovakia.json";
        }
         
         FileManager file = new FileManager();
        file.saveData(data, filePath); 
    
    }
    public  static void createAndorra(){
        data = new DataManager();
        data.setName("Andorra");
        data.setfilePath("./HW5SampleData/raw_map_data/Andorra.json");
        Subregion a = new Subregion("Ordino","Ordino (town)","Ventura Espot");
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
        data.setBackgroundColor(Color.ORANGE);
        data.setBorderColor(Color.BLACK);
        data.setScale(600);
        data.setThickness(0.001);
        String flagPath = "file:" + "./HW5SampleData/export/The World/Europe/" + "Andorra Flag.png";
        String flagPath2 = "file:" + "./HW5SampleData/export/The World/Europe/" + "Andorra.png";
        data.addImages(flagPath);data.addImages(flagPath2);
         Point2D location2 = new Point2D(12,9);
        Point2D location = new Point2D(580,390);
        data.addLocation(location);
        data.addLocation(location2);
        data.setDimensionW(802);
        data.setDimensionH(536);
      }
    public  static void createSanMarino(){
        data = new DataManager();
        data.setName("San Marino");
      data.setfilePath("./HW5SampleData/raw_map_data/San Marino.json");
        Subregion a = new Subregion("Acquaviva","null","Lucia Tamagnini");
        Subregion b = new Subregion("Borgo Maggiore","null","Sergio Nanni");
        Subregion c = new Subregion("Chiesanuova","null","Franco Santi");
        Subregion d = new Subregion("Domagnano","null","Gabriel Guidi");
        Subregion e = new Subregion("Faetano","null","Pier Mario Bedetti");
        Subregion f = new Subregion("Fiorentino","null","Gerri Fabbri");
        Subregion g = new Subregion("Montegiardino","null","Marta Fabbri");
        Subregion h = new Subregion("City of San Marino","null","Maria Teresa Beccari");
        Subregion i = new Subregion("Serravalle","null","Leandro Maiani");
        a.setR(225);a.setG(225);a.setB(225);
        b.setR(200);b.setG(200);b.setB(200);
        c.setR(175);c.setG(175);c.setB(175);
        d.setR(150);d.setG(150);d.setB(150);
        e.setR(125);e.setG(125);e.setB(125);
        f.setR(100);f.setG(100);f.setB(100);
        g.setR(75);g.setG(75);g.setB(75);
        h.setR(50);h.setG(50);h.setB(50);
        i.setR(25);i.setG(25);i.setB(25);
        
        data.addRegion(a);data.addRegion(b);data.addRegion(c);data.addRegion(d);data.addRegion(e);data.addRegion(f);data.addRegion(g);data.addRegion(h);
        data.addRegion(i);
        data.setBackgroundColor(Color.ORANGE);
        data.setBorderColor(Color.BLACK);
        data.setScale(1000);
        data.setThickness(0.001);
        data.setDimensionW(802);
        data.setDimensionH(536);
      }
     public  static void createSlovakia(){
         
        data = new DataManager();
        data.setName("Slovakia");
      data.setfilePath("./HW5SampleData/raw_map_data/Slovakia.json");
       data.setParentDir("To be edit");
       System.out.println(data.getParentDir());
        Subregion a = new Subregion("Bratislava","null","null");
        Subregion b = new Subregion("Trnava","null","null");
        Subregion c = new Subregion("Trencin","null","null");
        Subregion d = new Subregion("Nitra","null","null");
        Subregion e = new Subregion("Zilina","null","null");
        Subregion f = new Subregion("Banska Bystrica","null","null");
        Subregion g = new Subregion("Presov","null","null");
        Subregion h = new Subregion("Kosice","null","null");
        
        a.setR(250);a.setG(250);a.setB(250);
        b.setR(249);b.setG(249);b.setB(249);
        c.setR(248);c.setG(248);c.setB(249);
        d.setR(247);d.setG(247);d.setB(247);
        e.setR(246);e.setG(246);e.setB(246);
        f.setR(245);f.setG(245);f.setB(245);
        g.setR(244);g.setG(244);g.setB(244);
        h.setR(243);h.setG(243);h.setB(243);
       
        
        data.addRegion(a);data.addRegion(b);data.addRegion(c);data.addRegion(d);data.addRegion(e);data.addRegion(f);data.addRegion(g);data.addRegion(h);
       Point2D location = new Point2D(540,365);
       Point2D location2 = new Point2D(25,25);
        data.addLocation(location);data.addLocation(location2);
       String flagPath = "file:" + "./HW5SampleData/export/The World/Europe/" + "Slovakia Flag.png";
       String flagPath2 = "file:" + "./HW5SampleData/export/The World/Europe/" + "Slovenian Flag.png";
       data.addImages(flagPath);data.addImages(flagPath2);
        data.setBackgroundColor(Color.ORANGE);
        data.setBorderColor(Color.BLACK);
        data.setScale(50);
        data.setThickness(0.01);
        data.setDimensionW(802);
        data.setDimensionH(536);
      }
}