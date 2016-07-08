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
import static regioeditor.FileManager.done;
import static regioeditor.FileManager.getDataAsInt;

import regioeditor.Subregion;
import saf.components.AppDataComponent;

/**
 *
 * @author samuelchen
 */
public class TestLoad {
     static final String JSON_COUNTRY_NAME = "country name";
    static final String JSON_FILEPATH = "geometricFilePath";
    static final String JSON_BACKGROUND_COLOR = "backgroundcolor";
    static final String JSON_BORDER_COLOR = "bordercolor";
    static final String JSON_SCALE = "paneScale";
    static final String JSON_THICKNESS = "thickness";
    static final String JSON_DIMENSION_W = "dimensionW";
    static final String JSON_DIMENSION_H= "dimensionH";
    static final String JSON_REGION = "name";
     static final String JSON_CAPITAL = "capital";
      static final String JSON_LEADER = "leader";
      static final String JSON_COLOR_RED = "red";
      static final String JSON_COLOR_GREEN = "green";
      static final String JSON_COLOR_BLUE = "blue";
      static final String JSON_SUBREGIONS = "Subregion";
      static final String JSON_IMAGE_LOCATE = "image location";
      static final String JSON_IMAAGE_X = "x";
      static final String JSON_IMAAGE_Y = "y";
       static final String JSON_IMAGE_URL = "image url";
      static final String JSON_IMAAGE = "image";
    public static DataManager data;
    
    public static void main(String[] args) throws IOException{
        load("S");
        
        
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
 loadData(data, filePath);
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
public static void loadData(AppDataComponent datas, String filePath) throws IOException {
        done = false;
         DataManager data = (DataManager)datas;
        data.reset();
       
        JsonObject json = loadJSONFile(filePath);
    String countryName = json.getString(JSON_COUNTRY_NAME);
    String geoFile = json.getString(JSON_FILEPATH);
  
    String backgroundcolor = json.getString(JSON_BACKGROUND_COLOR);
    String borderColor = json.getString(JSON_BORDER_COLOR);
    double  scale = getDataAsDouble(json,JSON_SCALE);
    double thick = getDataAsDouble(json,JSON_THICKNESS);
    double dW = getDataAsDouble(json,JSON_DIMENSION_W);
    double dH = getDataAsDouble(json,JSON_DIMENSION_H);
    data.setfilePath(geoFile);
    data.setName(countryName);
    Color background = Color.valueOf(backgroundcolor);
    Color border = Color.valueOf(borderColor);
    data.setBackgroundColor(background);
    data.setBorderColor(border);
    data.setScale(scale);
    data.setThickness(thick);
    data.setDimensionH(dH);
    data.setDimensionW(dW);

JsonArray jsonItemArray = json.getJsonArray(JSON_SUBREGIONS);
for(int i =0;i<jsonItemArray.size();i++){
    JsonObject jsonItem = jsonItemArray.getJsonObject(i);
	    Subregion item = loadItem(jsonItem);
            int red = getDataAsInt(jsonItem,JSON_COLOR_RED);
            int green = getDataAsInt(jsonItem,JSON_COLOR_GREEN);
            int blue = getDataAsInt(jsonItem,JSON_COLOR_BLUE);
            item.setR(red);item.setG(green);item.setB(blue);
	    data.addRegion(item);
}
JsonArray imageArray = json.getJsonArray(JSON_IMAGE_LOCATE);
for(int i=0;i<imageArray.size();i++){
    JsonObject jsonItem = imageArray.getJsonObject(i);
   double x  = getDataAsDouble(jsonItem,JSON_IMAAGE_X);
   double y  = getDataAsDouble(jsonItem,JSON_IMAAGE_Y);
    Point2D point = new Point2D(x,y);
    data.addLocation(point);
}
JsonArray imageUrlArray = json.getJsonArray(JSON_IMAGE_URL);
for(int i=0;i<imageUrlArray.size();i++){
    JsonObject jsonItem = imageUrlArray.getJsonObject(i);
    String url = jsonItem.getString(JSON_IMAAGE);
    data.addImages(url);
}
done = true;
}
public static JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
public  static double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
 public  static Subregion loadItem(JsonObject jsonItem) {
	// GET THE DATA
	String region = jsonItem.getString(JSON_REGION);
	String capital = jsonItem.getString(JSON_CAPITAL);
        String leader = jsonItem.getString(JSON_LEADER);
  
	// ALL DONE, RETURN IT
        Subregion sub = new Subregion(region,capital,leader);
        return sub;
	
    }
 public static int getDataAsInt(JsonObject json, String dataName) {
        JsonValue value = json.get(dataName);
        JsonNumber number = (JsonNumber)value;
        return number.bigIntegerValue().intValue();
    }
}