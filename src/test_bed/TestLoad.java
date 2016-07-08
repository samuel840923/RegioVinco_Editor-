/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import regioeditor.DataManager;
import regioeditor.FileManager;
import regioeditor.Subregion;

/**
 *
 * @author samuelchen
 */
public class TestLoad {
    public static DataManager data;
    
    public static void main(String[] args) throws IOException{
        load("S");
        
        FileManager file = new FileManager();
        file.exportData(data, "./export/");
    }

public static void load(String s) throws IOException{
     String filePath = null;
        if(s.equals("A")){   
            filePath = "./HW5SampleData/work/Andorra.json";
        }
        else if(s.equals("SM")){
             filePath = "./HW5SampleData/work/SanMarino.json";
        }
         else if(s.equals("S")){     
             filePath = "./HW5SampleData/work/Slovakia.json";
        }
data = new DataManager();
 FileManager file = new FileManager();
 file.loadData(data, filePath);
 System.out.println(data.getName());
System.out.println(data.getfilePath());
System.out.println(data.getBackgroundColor());
System.out.println(data.getBorderColor());
System.out.println(data.getScale());
System.out.println(data.getThickness() );
System.out.println(data.getDimensionW());
System.out.println(data.getDimensionH());
ObservableList<Subregion> region = data.getRegion();
ArrayList<Point2D> location = data.getLocation();
ArrayList<String> url = data.getImages();
for(int i=0;i<region.size();i++){
    String r = region.get(i).getName();
    String c = region.get(i).getCapital();
    String l = region.get(i).getLeader();
    int red = region.get(i).getR();
    int g = region.get(i).getG();
    int b = region.get(i).getB();
    System.out.println(r+" "+c+" "+l);
    System.out.println(red+" "+g+" "+b);
}
for(int i=0;i<location.size();i++){
    double x = location.get(i).getX();
    double y = location.get(i).getY();
    System.out.println(x+" "+y);
}
for(int i=0; i<url.size();i++){
    String f = url.get(i);
    System.out.println(f);
}

}
}