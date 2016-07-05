package regioeditor;


import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import saf.AppTemplate;
import saf.components.AppDataComponent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samuelchen
 */

    public class DataManager implements AppDataComponent {
    // FIRST THE THINGS THAT HAVE TO BE SAVED TO FILES
         Color backgroundColor;
    Color borderColor;
    Double borderThickness;
    Double paneScale;
    
    // NAME OF THE TODO LIST
    StringProperty name;
    
    // LIST OWNER
    StringProperty owner;
    
    // THESE ARE THE ITEMS IN THE TODO LIST
    //ObservableList<> items = FXCollections.observableArrayList();
    
    // THIS IS A SHARED REFERENCE TO THE APPLICATION
    AppTemplate app;
    
    /**
     * THis constructor creates the data manager and sets up the
     *
     *
     * @param initApp The application within which this data manager is serving.
     */
    ArrayList<ArrayList<Point2D>> polygonXY;
    ObservableList<Subregion> region ; 
    ArrayList<Image> images;
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
        FXCollections.observableArrayList();
    }

    public DataManager() {
        images = new ArrayList<Image>();
        FXCollections.observableArrayList();
    }
    public void addRegion(Subregion item){
        region.add(item);
    }
    public void addImages(Image i){
        images.add(i);
    }
    public void setBorderColor(Color c){
        borderColor = c;
    }
     public void setBackgroundColor(Color c){
        backgroundColor = c;
    }
      public Color getBorderColor(Color c){
       return  borderColor;
    }
     public Color getBackgroundColor(Color c){
       return  backgroundColor; 
    }
     public void setThickness(double v){
         borderThickness = v;
     }
     public double getThickness(){
         return borderThickness;
     }
      public void setScale(double s){
         paneScale = s;
     }
       public double getScale(){
         return paneScale;
     }

    @Override
    public void reset() {
       
    }
    public ObservableList<Subregion> getRegion(){
        return region;
    }
    public ArrayList<Image> getImages(){
        return images;
    }
    }
    
  