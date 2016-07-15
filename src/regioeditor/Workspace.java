/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import properties_manager.PropertiesManager;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;
import saf.ui.AppMessageDialogSingleton;
import saf.ui.AppYesNoCancelDialogSingleton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import static saf.settings.AppPropertyType.EXPORT_ICON;
import static saf.settings.AppPropertyType.EXPORT_TOOLTIP;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;
/**
 *
 * @author samuelchen
 */
public class Workspace extends AppWorkspaceComponent{
     AppTemplate app;
     static boolean done = false;
    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;
     static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String CLASS_EDITTOOL = "bordered_mine";
    static final String CLASS_BUTTON = "button_label";
    static final String CLASS_BORDER_COLOR_LABEL = "border_color";
    static final String CLASS_DIMENSION = "dimension_button";
    static final String CLASS_MAP_PANE = "map_pane";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;
    static final String DEFAULT_STRING = "promp";
  
   
    
    // THIS IS OUR WORKSPACE HEADING
    Polygon poly;
    Pane imagePane;
  Pane polygonPane ;
   Pane p ;
    Label mapLabel;
    Pane clipPane;
    Pane mapPane;
    VBox TablePane;
    HBox editToolBar1;
    HBox editToolBar2;
    ColorPicker colorBackground;
    ColorPicker colorBorder;
    Slider zooming ;
    Slider thickness;
    Button addImage;
    Button rename;
    Button dimension;
    Button removeImage;
    Button export;
    Button reassign;
   
    Button play;
    Button pause;
    //all the label     
    Label labelRemove;
    Label zoom; 
    Label labelAdd;
    Label backgroundColor;
    Label borderColor;
    Label mapName;
    Label borderThick;
    
   
    TableView<Subregion> regionTable;
    TableColumn regionColumn;
    TableColumn capitalColumn;
    TableColumn leaderColumn;
    
    // HERE ARE OUR DIALOGS
    AppMessageDialogSingleton messageDialog;
    AppYesNoCancelDialogSingleton yesNoCancelDialog;
    SplitPane editor;
    
    // FOR DISPLAYING DEBUG STUFF
    Text debugText;
    
    Rectangle clip;
     Rectangle back;
    double xAvg;
    double yAvg;
    
    ArrayList<Polygon> polygon;
    ArrayList<Integer> randomC;
    ArrayList<ImageView> imageview;
    
    double dimX;
    double dimY;
    double orginX;
    double orginY;
    double stopY;
    double stopX;
    int now;
    int prev=0;
    int prevPoly;
    int nowPoly;
    Image nowImage;
    ImageView imageV;
    
 public Workspace(AppTemplate initApp) throws IOException {
	// KEEP THIS FOR LATER
        
	app = initApp;

	// KEEP THE GUI FOR LATER
	gui = app.getGUI();
        
        workspace = new SplitPane();
        
        // INIT ALL WORKSPACE COMPONENTS
	
        layoutGUI();
        // AND SETUP EVENT HANDLING
	setUpHandler();
        
        
    }
  private void layoutGUI(){
    
       clipPane = new Pane();
       PropertiesManager props = PropertiesManager.getPropertiesManager();
      TablePane = new VBox();
      editToolBar1 = new HBox();
      editToolBar2 = new HBox();
      mapPane = new Pane();
      export = gui.initChildButton(app.getGUI().getToolBar(),EXPORT_ICON.toString(), EXPORT_TOOLTIP.toString(),	true);     
      addImage = gui.initChildButton(editToolBar1, PropertyType.ADD_ICON.toString(), PropertyType.ADD_ITEM_TOOLTIP.toString(), false);     
      removeImage = gui.initChildButton(editToolBar1, PropertyType.REMOVE_ICON.toString(), PropertyType.REMOVE_ITEM_TOOLTIP.toString(), true);    
      play = gui.initChildButton(editToolBar1, PropertyType.PLAY_ICON.toString(), PropertyType.PLAY_TOOLTIP.toString(), false);    
      pause = gui.initChildButton(editToolBar1, PropertyType.PAUSE_ICON.toString(), PropertyType.PAUSE_TOOLTIP.toString(), false);
      zoom = new Label(props.getProperty(PropertyType.ZOOM));
      backgroundColor = new Label(props.getProperty(PropertyType.BACKGROUND_COLOR));
      borderColor = new Label(props.getProperty(PropertyType.BORDER_COLOR));
      borderThick = new Label(props.getProperty(PropertyType.BORDER_THICKNESS));
      zooming = new Slider(1,1000,600);
      thickness = new Slider(0.001,1,0.01);
      colorBackground = new ColorPicker();
      colorBorder = new ColorPicker();
      zoom.setMinWidth(100);
      borderThick.setMinWidth(120);
      rename = new Button(props.getProperty(PropertyType.RENAME));
      editToolBar1.getChildren().addAll(borderThick,thickness,zoom,zooming);
      
      TablePane.getChildren().add(editToolBar1);
      TablePane.getChildren().add(editToolBar2);
      regionTable = new TableView();  
      mapLabel = new Label(DEFAULT_STRING);
     
      dimension = gui.initChildButton(editToolBar2, PropertyType.DIMENSION_ICON.toString(), PropertyType.DIMENSION_TOOLTIP.toString(), false);
      reassign = gui.initChildButton(editToolBar2, PropertyType.REDO_ICON.toString(), PropertyType.REDO_TOOLTIP.toString(), false);

       // NOW SETUP THE TABLE COLUMN
        
        regionColumn = new TableColumn(props.getProperty(PropertyType.SUBREGION_NAME));
        capitalColumn = new TableColumn(props.getProperty(PropertyType.CAPITAL));
        leaderColumn = new TableColumn(props.getProperty(PropertyType.LEADER_NAME));
        regionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        capitalColumn.setCellValueFactory(new PropertyValueFactory<String, String>("Capital"));
        leaderColumn.setCellValueFactory(new PropertyValueFactory<String, String>("leader"));
       
        regionTable.getColumns().add(regionColumn);
        regionTable.getColumns().add(capitalColumn);
        regionTable.getColumns().add(leaderColumn);
        regionTable.setMinHeight((app.getGUI().getPrimaryScene().getHeight()));
       DataManager dataManager = (DataManager)app.getDataComponent();
       regionTable.setItems(dataManager.getRegion());
       TablePane.getChildren().add(regionTable);
       
       
       
 
      
  }
    @Override
    public void reloadWorkspace() {
        now=0;prev=0;prevPoly=0;nowPoly=0;
        export.setDisable(false);
       workspace.getItems().clear();
       clipPane.getChildren().clear();
       mapPane.getChildren().clear();
       polygon = new ArrayList<Polygon>();
       imageview = new ArrayList<ImageView>();
       DataManager data = (DataManager)app.getDataComponent();
       if(data.getRegion().size()==0){
           startNew();
       }
       dimX = data.getDimensionW();
       dimY  = data.getDimensionH();
       clip = new Rectangle(0,0,dimX,dimY);
       clipPane.setClip(clip);
       back = new Rectangle(0,0,dimX,dimY);
       clipPane.getChildren().add(back);
       Color background = data.getBackgroundColor();
       mapPane.getChildren().add(clipPane);
       back.setFill(background);
       polygonPane = drawPolygon();
       imagePane = redrawImage();
   
       setScale();
       clipPane.getChildren().addAll(polygonPane,imagePane);
       workspace.getItems().addAll(mapPane,TablePane);
       app.getGUI().getAppPane().setCenter(workspace);
       imagePane.setPickOnBounds(false);
       polygonPane.setFocusTraversable(true);
       
       done = true;
    
    
               }

    @Override
    public void initStyle() {
//   workspace = new SplitPane();
//   workspace.getItems().addAll(mapPane,TablePane);
//   app.getGUI().getAppPane().setCenter(workspace);
//    SubregionDialog messageDialog = SubregionDialog.getSingleton();
//    messageDialog.init(messageDialog);
//   workspace.getStyleClass().add(CLASS_BORDERED_PANE);
        
        // THEN THE HEADING
	workspace.getStyleClass().add(CLASS_BORDERED_PANE);
        
        // THEN THE DETAILS PANE AND ITS COMPONENTS
        editToolBar1.getStyleClass().add(CLASS_EDITTOOL);
        editToolBar2.getStyleClass().add(CLASS_EDITTOOL);
        zoom.getStyleClass().add(CLASS_PROMPT_LABEL);
        backgroundColor.getStyleClass().add(CLASS_PROMPT_LABEL);
        borderColor.getStyleClass().add(CLASS_BORDER_COLOR_LABEL);
        borderThick.getStyleClass().add(CLASS_PROMPT_LABEL);
        mapLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        dimension.getStyleClass().add(CLASS_DIMENSION);
        rename.getStyleClass().add(CLASS_DIMENSION);
        reassign.getStyleClass().add(CLASS_BUTTON);
        mapPane.getStyleClass().add(CLASS_MAP_PANE);
        
       
//        
   
    }

    private void setUpHandler() {
      Controller control = new Controller(app);
      addImage.setOnAction(e-> {
          String url = control.processAddImage();
          addimage(url);
          app.getGUI().updateToolbarControls(false);
      }); 
      removeImage.setOnAction(e-> {
          control.processRemoveImage();
         deletImage();
         app.getGUI().updateToolbarControls(false);
          
      });
      play.setOnAction(e-> {
          control.playMusic();
      });
      pause.setOnAction(e->{
          control.pauseMusic();
      });
      regionTable.setOnMousePressed(e-> {
        if(e.getClickCount()==2){
            int i=  regionTable.getSelectionModel().getFocusedIndex();
             control.processSubregion(i);
             app.getGUI().updateToolbarControls(false);
           }
           else if(e.getClickCount()==1){
             nowPoly = regionTable.getSelectionModel().getFocusedIndex();
              ColorAdjust colorAdjust = new ColorAdjust();
             colorAdjust.setBrightness(1);
                  polygon.get(prevPoly).setEffect(null);
                  polygon.get(nowPoly).setEffect(colorAdjust);
                  prevPoly = nowPoly;
           }
          
      });
//      regionTable.setRowFactory(e -> {
//            TableRow<Subregion> row = new TableRow<>();
//            row.setOnMouseClicked( event -> {
//          
//            });
//            return row;
//        });
      dimension.setOnAction(e ->{
           DataManager data = (DataManager)app.getDataComponent();
          control.processDimension();
          clip.setWidth(data.getDimensionW());
          clip.setHeight(data.getDimensionH());
           back.setWidth(data.getDimensionW());
          back.setHeight(data.getDimensionH());
          clipPane.setPrefSize(data.getDimensionW(), data.getDimensionH());
          app.getGUI().updateToolbarControls(false);
      });
      rename.setOnAction(e -> {
          control.processRename();
          app.getGUI().updateToolbarControls(false);
      });
      
      export.setOnAction(e -> {
          control.processExport();
         
      });
      
      reassign.setOnAction(e->{
          randomColor();
          app.getGUI().updateToolbarControls(false);
      });
        
    }

   
    public Pane redrawImage() {
     Pane    imagePane = new Pane();
         DataManager data = (DataManager)app.getDataComponent();
           for(int i=0;i<data.getImages().size();i++){
               String url = data.getImages().get(i);
               
               double locX = data.getLocation().get(i).getX();
               double locY = data.getLocation().get(i).getY();
               
               Image image = new Image(url);
               imageV = new ImageView(image);
               imageV.setX(locX);
               imageV.setY(locY);
               imageview.add(imageV);
          imagePane.getChildren().add(imageV);
          setImageControll(imageV);
          
        }
        return imagePane;
           
    }

    public Pane drawPolygon() {
        Pane   polygonPane = new Pane();
           DataManager data = (DataManager) app.getDataComponent();
          ArrayList<ArrayList<Point2D>> polygon = data.getPoly();
         
          double avgX=0; double avgY=0; double m=0;
           for(int j=0;j<polygon.size();j++){
              ArrayList<Point2D> points = polygon.get(j);
               poly = new Polygon();
           for(int i=0;i<points.size();i++){
               poly.getPoints().addAll(points.get(i).getX(),points.get(i).getY());
               m++;
               avgX+=points.get(i).getX();
               avgY += points.get(i).getY();
              }
              int red = data.getRegion().get(j).getR();
              int green = data.getRegion().get(j).getG();
              int blue = data.getRegion().get(j).getB();
              Color c =  Color.rgb(red,green,blue);
              double thick = data.getThickness();
              poly.setStroke(data.getBorderColor());
              poly.setFill(c);    
              poly.setStrokeWidth(thick);
              poly.setId(j+"");
              this.polygon.add(poly);
              polygonPane.getChildren().add(poly);  
              polyControll(poly);
      } 
           xAvg = avgX/m;
           yAvg = avgY/m;
           double scale = data.getScale();
               polygonPane.setLayoutX((polygonPane.getLayoutX()+(802/2-xAvg))*scale);
               polygonPane.setLayoutY((polygonPane.getLayoutY()+(536/2-yAvg))*scale);
               polygonPane.setScaleX(polygonPane.getScaleX()*scale);
               polygonPane.setScaleY(polygonPane.getScaleY()*scale);
               polygonPane.setTranslateX(data.getTranX());
               polygonPane.setTranslateY(data.getTranY());
           
           polygonPane.setPrefSize(802, 536); 
           polygonPane.requestLayout();
           return polygonPane;   
          
    }

    public void startNew() {
        DataManager data = (DataManager)app.getDataComponent();
       for(int i=0;i<data.getPoly().size();i++){
           Subregion s = new Subregion("To be fill","null","null");
           s.setR(i+1);
           s.setG(i+1);
           s.setB(i+1);
           data.addRegion(s);
       }
    }

    public void setScale() {
        DataManager data = (DataManager)app.getDataComponent();
        colorBorder = new ColorPicker(data.getBorderColor());
        colorBackground = new ColorPicker(data.getBackgroundColor());
        editToolBar2.getChildren().clear();
        editToolBar2.getChildren().addAll(dimension,reassign,backgroundColor,colorBackground,
        borderColor,colorBorder,rename);
        colorBorder.getStyleClass().add(CLASS_BUTTON);
        colorBackground.getStyleClass().add(CLASS_BUTTON);
        
        zooming.setValue(data.getScale());
        thickness.setValue(data.getThickness());
        
        
        zooming.valueProperty().addListener(e->{
          polygonPane.setScaleX(zooming.getValue());
           polygonPane.setScaleY(zooming.getValue());
           polygonPane.setLayoutX(((802/2-xAvg))*zooming.getValue());
           polygonPane.setLayoutY(((536/2-yAvg))*zooming.getValue());
           data.setScale(zooming.getValue());
           app.getGUI().updateToolbarControls(false);
           
        });
        
       thickness.valueProperty().addListener(e->{
        for(int i=0;i<polygon.size();i++){
            polygon.get(i).setStrokeWidth(thickness.getValue());
            data.setThickness(thickness.getValue());
            app.getGUI().updateToolbarControls(false);
        }
     
        });
       
       
        colorBorder.setOnAction(e->{
            Color c = colorBorder.getValue();
            for(int i=0;i<polygon.size();i++){
            polygon.get(i).setStroke(c);
          }
            data.setBorderColor(c);
            app.getGUI().updateToolbarControls(false);
        });
        
        
        colorBackground.setOnAction(e->{
             Color c = colorBackground.getValue();
             back.setFill(c);
             data.setBackgroundColor(c);
             app.getGUI().updateToolbarControls(false);
        });
        polygonPane.setOnMouseClicked(e->{
            polygonPane.requestFocus();
        });
        polygonPane.setOnKeyPressed(e->{
            
           if(e.getCode()==KeyCode.RIGHT){
               moving(-10,0);
              
           }
           if(e.getCode()==KeyCode.LEFT){
               moving(10,0);
               
           }
           if(e.getCode()==KeyCode.UP){
              moving(0,10);
               
           }
           if(e.getCode()==KeyCode.DOWN){
               moving(0,-10);
             
           }
        });

    }

    private void randomColor() {
         DataManager data = (DataManager)app.getDataComponent();
         randomC = new ArrayList<Integer>();
         for(int i=0;i<polygon.size();i++){
              int random =(int) (254* Math.random())+1;
             for(int j =0;j<randomC.size();j++){
                 if(random==randomC.get(j)){
                     while( random==randomC.get(j))
                           random =(int) (254* Math.random())+1;
                 }
                 
             }
             
             randomC.add(random);
         }
      
       for(int i=0;i<polygon.size();i++){
           int color = randomC.get(i);
           Color c = Color.rgb(color, color, color);
           polygon.get(i).setFill(c);
           data.getRegion().get(i).setR(color);
           data.getRegion().get(i).setG(color);
           data.getRegion().get(i).setB(color);
       }
       
    }

    public void addimage(String url) {
         DataManager data = (DataManager)app.getDataComponent();
        Image image= new Image(url);
         imageV = new ImageView(image);
        imageV.setX(802/2);
        imageV.setY(536/2);
        imageview.add(imageV);
        imagePane.getChildren().add(imageV);
        setImageControll(imageV);
        data.addImages(url);
        data.addLocation(new Point2D(802/2,536/2));
    }

    public void setImageControll(ImageView view) {
       DataManager data = (DataManager)app.getDataComponent();
       view.setOnMouseDragged(e->{
           double dX = e.getX() - orginX ;
           double dY =e.getY() - orginY ;
           view.setX(stopX+dX);
           view.setY(stopY+dY);
       });
      

       view.setOnMouseClicked(e->{  
          
           for(int i=0;i<imageview.size();i++){
               if(imageview.get(i)==view)
                   now = i;          
           }
           imageview.get( prev).setEffect(null);
           
            DropShadow borderGlow= new DropShadow();
           borderGlow.setColor(Color.RED);
          imageview.get(now).setEffect(borderGlow);
          nowImage = view.getImage();
          prev = now;
          removeImage.setDisable(false);
          stopX = imageview.get(now).getX();
          stopY = imageview.get(now).getY();       
          
       });
       view.setOnMousePressed(e->{
           orginX = e.getX();
           orginY = e.getY();
       });
       view.setOnMouseReleased(e->{
           for(int i=0;i<imageview.size();i++){
               if(imageview.get(i)==view){
                   data.editLocation(i,view.getX(),view.getY());               
               }
           }
           app.getGUI().updateToolbarControls(false);
       });
       
       
      
    }

    private void deletImage() {
         DataManager data = (DataManager)app.getDataComponent();
        int in=0;
          for (int i =0;i<imageview.size();i++){
              if(imageview.get(i).getImage()==nowImage){
                  imageview.get(i).setImage(null);
                  imageview.remove(i);
                  in = i;
                  break;
              }
          }
          data.removeImage(in);
          data.removeLocation(in);
          app.getGUI().updateToolbarControls(false);
          
    }
    public Pane getSnapPane(){
        return clipPane;
    }

    private void polyControll(Polygon poly) {
         DataManager data = (DataManager)app.getDataComponent();
         Controller control = new Controller(app);
        poly.setOnMouseClicked(e->{
            if(e.getClickCount()==2){
               int i = Integer.parseInt(poly.getId());
               control.processSubregion(i);
            }
            else if(e.getClickCount()==1){
               
               nowPoly = Integer.parseInt(poly.getId());
            regionTable.getSelectionModel().select(nowPoly);
             ColorAdjust colorAdjust = new ColorAdjust();
             colorAdjust.setBrightness(1);
                  polygon.get(prevPoly).setEffect(null);
                  polygon.get(nowPoly).setEffect(colorAdjust);
                  prevPoly = nowPoly;
            
                }
            
        });
       
    }
     public void moving(double x, double y){
         DataManager data = (DataManager)app.getDataComponent();
               polygonPane.setTranslateX(polygonPane.getTranslateX()+x);
               polygonPane.setTranslateY(polygonPane.getTranslateY()+y);
               data.setTranX(polygonPane.getTranslateX());
               data.setTranY(polygonPane.getTranslateY());
               app.getGUI().updateToolbarControls(false);
               
        }
}
