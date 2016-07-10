/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;


/**
 *
 * @author samuelchen
 */
public class FileManager implements AppFileComponent{
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
     //GEOMETRIC X Y    
    static final String JSON_SUBREGION = "SUBREGIONS";
    static final String JSON_NUMBER_OF_REGION = "NUMBER_OF_SUBREGIONS";
    static final String JSON_NUMBER_OF_SUBREGION_POLYGONS = "NUMBER_OF_SUBREGION_POLYGONS";
    static final String JSON_SUBREGION_POLYGONS = "SUBREGION_POLYGONS";
    static final String JSON_X = "X";
    static final String JSON_Y = "Y";
    ArrayList<Point2D> points ;
    
    static final String EXPORT_JSON_NAME = "name";
    static final String EXPORT_JSON_SUBREGIONS_HAVE_CAPITALS = "subregions_have_capitals";
    static final String EXPORT_JSON_SUBREGIONS_HAVE_FLAGS = "subregions_have_flags";
    static final String EXPORT_JSON_SUBREGIONS_HAVE_LEADERS = "subregions_have_leaders";
    static final String EXPORT_JSON_SUBREGIONS = "subregions";
   public static boolean done = false;

    @Override
    public void saveData(AppDataComponent datas, String filePath) throws IOException {
        DataManager data = (DataManager)datas;
       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
	ObservableList<Subregion> items = data.getRegion();
        for (int i=0;i<items.size();i++) {
            String cap = items.get(i).getCapital();
            String led = items.get(i).getLeader();
            
	        JsonObject itemJson = Json.createObjectBuilder()
                   .add(JSON_REGION , items.get(i).getName())       
                   .add(JSON_CAPITAL , cap)
                   .add(JSON_LEADER , led)
           
                  
		    .add(JSON_COLOR_RED , items.get(i).getR())
		    .add(JSON_COLOR_GREEN , items.get(i).getG())
                    .add(JSON_COLOR_BLUE , items.get(i).getB()).build();
                    
	    arrayBuilder.add(itemJson);
	}
         JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();
        if(data.getLocation().size()!=0){
              
             ArrayList<Point2D> locate = data.getLocation();
             for(int i =0;i<locate.size();i++){
                 JsonObject itemJson = Json.createObjectBuilder()
                         .add(JSON_IMAAGE_X, locate.get(i).getX())
                         .add(JSON_IMAAGE_Y, locate.get(i).getY()).build();
                 arrayBuilder1.add(itemJson);
             }
        }
         JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();
        if(data.getImages().size()!=0){
             ArrayList<String> image = data.getImages();
             for(int i =0;i<image.size();i++){
                 JsonObject itemJson = Json.createObjectBuilder()
                         .add(JSON_IMAAGE, image.get(i)).build();
                         
                 arrayBuilder2.add(itemJson);
             }
        }
        
        JsonArray itemsArray = arrayBuilder.build();
        JsonArray locateArray = arrayBuilder1.build();
        JsonArray imageArray = arrayBuilder2.build();
       
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                 .add(JSON_COUNTRY_NAME, data.getName())
		.add(JSON_FILEPATH, data.getfilePath())
                .add(JSON_BACKGROUND_COLOR, data.getBackgroundColor().toString())
		.add(JSON_BORDER_COLOR, data.getBorderColor().toString())
                .add(JSON_SCALE, data.getScale())
		.add(JSON_THICKNESS, data.getThickness())
                .add(JSON_DIMENSION_W, data.getDimensionW())
		.add(JSON_DIMENSION_H, data.getDimensionH())
                .add(JSON_SUBREGIONS,itemsArray)
                .add(JSON_IMAGE_LOCATE,locateArray)
                .add(JSON_IMAGE_URL,imageArray)
		.build();
       
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

    @Override
    public void loadData(AppDataComponent datas, String filePath) throws IOException {
        done = false;
         DataManager data = (DataManager)datas;
        data.reset();
        
        JsonObject json = loadJSONFile(filePath);
    String countryName = json.getString(JSON_COUNTRY_NAME);
    String geoFile = json.getString(JSON_FILEPATH);
    loadGeometric(data,geoFile);
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
    

    @Override
    public void exportData(AppDataComponent datas, String filePath) throws IOException {
        DataManager data = (DataManager)datas;
        String path = filePath;
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
	ObservableList<Subregion> items = data.getRegion();
        for (int i=0;i<items.size();i++) {
            String cap = items.get(i).getCapital();
            String led = items.get(i).getLeader();
            if(items.get(i).getCapital().equals("null")&&items.get(i).getLeader().equals("null")){
                JsonObject itemJson = Json.createObjectBuilder()
                   .add(JSON_REGION , items.get(i).getName())       
		    .add(JSON_COLOR_RED , items.get(i).getR())
		    .add(JSON_COLOR_GREEN , items.get(i).getG())
                    .add(JSON_COLOR_BLUE , items.get(i).getB()).build();
                    
	    arrayBuilder.add(itemJson);
            }
            else if(items.get(i).getCapital().equals("null")){
	        JsonObject itemJson = Json.createObjectBuilder()
                   .add(JSON_REGION , items.get(i).getName())        
                   .add(JSON_LEADER , led)
		    .add(JSON_COLOR_RED , items.get(i).getR())
		    .add(JSON_COLOR_GREEN , items.get(i).getG())
                    .add(JSON_COLOR_BLUE , items.get(i).getB()).build();
	    arrayBuilder.add(itemJson);
            }
             else if(items.get(i).getLeader().equals("null")){
	        JsonObject itemJson = Json.createObjectBuilder()
                   .add(JSON_REGION , items.get(i).getName())        
                   .add(JSON_CAPITAL , cap)
		    .add(JSON_COLOR_RED , items.get(i).getR())
		    .add(JSON_COLOR_GREEN , items.get(i).getG())
                    .add(JSON_COLOR_BLUE , items.get(i).getB()).build();
	    arrayBuilder.add(itemJson);
            }
             else{
                
	        JsonObject itemJson = Json.createObjectBuilder()
                   .add(JSON_REGION , items.get(i).getName())       
                   .add(JSON_CAPITAL , cap)
                   .add(JSON_LEADER , led)
           
                  
		    .add(JSON_COLOR_RED , items.get(i).getR())
		    .add(JSON_COLOR_GREEN , items.get(i).getG())
                    .add(JSON_COLOR_BLUE , items.get(i).getB()).build();
                    
	    arrayBuilder.add(itemJson);

                 
             }
	}
        JsonArray regionArray = arrayBuilder.build();
          JsonObject dataManagerJSO = Json.createObjectBuilder()
                 .add(EXPORT_JSON_NAME, data.getName())
		.add(EXPORT_JSON_SUBREGIONS_HAVE_CAPITALS, data.isCapitalExist())
                .add(EXPORT_JSON_SUBREGIONS_HAVE_FLAGS, data.isFlagExist(data.getName()))
		.add(EXPORT_JSON_SUBREGIONS_HAVE_LEADERS, data.isLeaderExist(data.getName()))
                .add(EXPORT_JSON_SUBREGIONS, regionArray)
		.build();
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(path);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(path);
	pw.write(prettyPrinted);
	pw.close();
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void loadGeometric(AppDataComponent data, String filePath) throws IOException{
         DataManager dataManager = (DataManager)data;
        JsonObject json = loadJSONFile(filePath);
        // num of region
        JsonArray SubRegion = json.getJsonArray(JSON_SUBREGION);
        // get the size of the screen
     
             //
        for(int i =0; i<SubRegion.size();i++){
           JsonObject Sub_Region_Poly = SubRegion.getJsonObject(i);
           int numOfPoly = getDataAsInt(Sub_Region_Poly,JSON_NUMBER_OF_SUBREGION_POLYGONS);
           
           JsonArray SubPolyRegion = Sub_Region_Poly.getJsonArray(JSON_SUBREGION_POLYGONS);
          
           //# of poly
            points = new ArrayList<Point2D>();
           for (int j =0;j<SubPolyRegion.size();j++){
           JsonArray  coordinate = SubPolyRegion.getJsonArray(j); 
          
           //XY
           for (int k = 0;k<coordinate.size() ;k++){
               JsonObject coor = coordinate.getJsonObject(k);
               double X = getDataAsDouble(coor,JSON_X);
               double Y = getDataAsDouble(coor,JSON_Y);
               double W = 802/2;
               double H = 536/2;
                X+=180;
               Y=Y*-1+90;
               X=(X/360.0)*802;
               Y=(Y/180.0)*536;
             
               
               Point2D point = new Point2D(X,Y);
               points.add(point);
              }
          
          }
             dataManager.addGeo(points);
        }
        
        
    }
    private  JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
public  double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
 public  Subregion loadItem(JsonObject jsonItem) {
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
    

