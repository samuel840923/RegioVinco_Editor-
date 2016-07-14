package regioeditor;


import java.io.File;
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
    String geometricFile;
    String regionName;
    // NAME OF THE TODO LIST
    String filename;
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
    ObservableList<Subregion> region = FXCollections.observableArrayList();
    ArrayList<String> images ;
    ArrayList<Point2D> imageLocation;
    double dimensionW;
    double dimensionH;
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
        
         images = new ArrayList<String>();
        imageLocation = new ArrayList<Point2D>();
        polygonXY =  new ArrayList<ArrayList<Point2D>>();
    }

    public DataManager() {
        images = new ArrayList<String>();
        imageLocation = new ArrayList<Point2D>();
         polygonXY =  new ArrayList<ArrayList<Point2D>>();
        
      
        
    }
    public void setName(String s){
        regionName =s;
    }
    public String getName(){
        return regionName;
    }
    public void addGeo(ArrayList<Point2D> poly){
        polygonXY.add(poly);
    }
    public void addLocation(Point2D e){
        imageLocation.add(e);
        
    }
    public void addRegion(Subregion item){
        region.add(item);
    }
    public void addImages(String i){
        images.add(i);
    }
    public String getfilePath(){
        return geometricFile;
    }
    public void setfilePath(String path){
        geometricFile = path;
    }
    public void setBorderColor(Color c){
        borderColor = c;
    }
     public void setBackgroundColor(Color c){
        backgroundColor = c;
    }
      public Color getBorderColor(){
       return  borderColor;
    }
     public Color getBackgroundColor(){
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
      public void setDimensionW(double w){
          dimensionW = w;
      }
      public double getDimensionW(){
          return dimensionW;
      }
       public void setDimensionH(double h){
          dimensionH = h;
      }
      public double getDimensionH(){
          return dimensionH;
      }
      public Subregion getSubregion(int i){
          return region.get(i);
          
      }
      public ArrayList<Point2D> getLocation(){
          return imageLocation;
      }

    @Override
    public void reset() {
        Workspace.done = false;
         images = new ArrayList<String>();
        imageLocation = new ArrayList<Point2D>();
        polygonXY =  new ArrayList<ArrayList<Point2D>>();
        region.clear();
         FXCollections.observableArrayList();
    }
    public ObservableList<Subregion> getRegion(){
        return region;
    }
    public ArrayList<String> getImages(){
        return images;
    }
    public ArrayList<ArrayList<Point2D>> getPoly(){
        return polygonXY;
    }
    public boolean isCapitalExist(){
        for(int i =0;i<region.size();i++){
            if(region.get(i).getCapital().equals("null"))
                return false;
        }
       return true;
    }
    public boolean isFlagExist(String name){
        for(int i =0;i<region.size();i++){
            String dir = "./HW5SampleData/export/The World/Europe/"+name+"/";
            String flag = region.get(i).getName()+" Flag.png";
            File file = new File(dir+flag);
            if(!(file.exists()))
                return false;
               
        }
       return true;
    }
    public boolean isLeaderExist(String name){
        for(int i =0;i<region.size();i++){
            String dir = "./HW5SampleData/export/The World/Europe/"+name+"/";
            if(region.get(i).getLeader().equals("null"))
                return false;
            String lead = region.get(i).getLeader()+".png";
           
            File file = new File(dir+lead);
            if(!(file.exists()))
                return false;
               
        }
       return true;
    }

    @Override
    public void setStart(String file,String name, String parent) {
      backgroundColor = Color.ORANGE;
      borderColor = Color.BLACK;
      borderThickness = 0.1;
      paneScale = 1.0;
      geometricFile = file;
      regionName = name;
      dimensionW = 802;
      dimensionH = 536;
      filename = parent;
    }
    public String getParentDir(){
        return filename;
    }
    public void setParentDir(String p){
        filename = p;
    }

    @Override
    public String getdirc() {
        return filename;
    }

    @Override
    public String getN() {
        return regionName;
    }

    void removeImage(int in) {
        images.remove(in);
    }

    void editLocation(int i,double stopX, double stopY) {
      imageLocation.remove(i);
      imageLocation.add(i, new Point2D(stopX,stopY));
    }
    public void editRegion(int i,Subregion s){
        region.set(i, s);
    }
    }
    
  