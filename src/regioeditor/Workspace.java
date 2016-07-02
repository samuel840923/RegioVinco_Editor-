/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import com.sun.prism.paint.Color;
import java.io.IOException;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

/**
 *
 * @author samuelchen
 */
public class Workspace extends AppWorkspaceComponent{
     AppTemplate app;

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
    Label mapLabel;
    VBox mapPane;
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
       PropertiesManager props = PropertiesManager.getPropertiesManager();
      TablePane = new VBox();
      editToolBar1 = new HBox();
      editToolBar2 = new HBox();
      mapPane = new VBox();
       
      addImage = gui.initChildButton(editToolBar1, PropertyType.ADD_ICON.toString(), PropertyType.ADD_ITEM_TOOLTIP.toString(), false);
      removeImage = gui.initChildButton(editToolBar1, PropertyType.REMOVE_ICON.toString(), PropertyType.REMOVE_ITEM_TOOLTIP.toString(), true);
      play = gui.initChildButton(editToolBar1, PropertyType.PLAY_ICON.toString(), PropertyType.PLAY_TOOLTIP.toString(), false);
      pause = gui.initChildButton(editToolBar1, PropertyType.PAUSE_ICON.toString(), PropertyType.PAUSE_TOOLTIP.toString(), false);
      zoom = new Label(props.getProperty(PropertyType.ZOOM));
      backgroundColor = new Label(props.getProperty(PropertyType.BACKGROUND_COLOR));
      borderColor = new Label(props.getProperty(PropertyType.BORDER_COLOR));
      borderThick = new Label(props.getProperty(PropertyType.BORDER_THICKNESS));
      zooming = new Slider(1,6,1);
      thickness = new Slider(1,10,1);
      colorBackground = new ColorPicker();
      colorBorder = new ColorPicker();
      zoom.setMinWidth(100);
      borderThick.setMinWidth(120);
      rename = new Button(props.getProperty(PropertyType.RENAME));
      editToolBar1.getChildren().addAll(borderThick,thickness,zoom,zooming);
      
      
      editToolBar2.getChildren().addAll(backgroundColor,colorBackground,
              borderColor,colorBorder,rename);
      dimension = gui.initChildButton(editToolBar2, PropertyType.DIMENSION_ICON.toString(), PropertyType.DIMENSION_TOOLTIP.toString(), false);
      reassign = gui.initChildButton(editToolBar2, PropertyType.REDO_ICON.toString(), PropertyType.REDO_TOOLTIP.toString(), false);

      
      TablePane.getChildren().add(editToolBar1);
      TablePane.getChildren().add(editToolBar2);
      regionTable = new TableView();   
      mapLabel = new Label(DEFAULT_STRING);
      mapLabel.setLayoutX(app.getGUI().getPrimaryScene().getWidth()/8);
        
       // NOW SETUP THE TABLE COLUMN
        mapPane.getChildren().add(mapLabel);
        regionColumn = new TableColumn(props.getProperty(PropertyType.SUBREGION_NAME));
        capitalColumn = new TableColumn(props.getProperty(PropertyType.CAPITAL));
        leaderColumn = new TableColumn(props.getProperty(PropertyType.LEADER_NAME));
        regionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        capitalColumn.setCellValueFactory(new PropertyValueFactory<String, String>("Capital"));
        leaderColumn.setCellValueFactory(new PropertyValueFactory<String, String>("leader"));
       
        regionTable.getColumns().add(regionColumn);
        regionTable.getColumns().add(capitalColumn);
        regionTable.getColumns().add(leaderColumn);
        regionTable.setMinHeight((app.getGUI().getPrimaryScene().getHeight()-(editToolBar2.getHeight()+editToolBar1.getHeight()+250)));
       DataManager dataManager = (DataManager)app.getDataComponent();
       regionTable.setItems(dataManager.getRegion());
       TablePane.getChildren().add(regionTable);
       
       
       
 
      
  }
    @Override
    public void reloadWorkspace() {
       
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
        colorBorder.getStyleClass().add(CLASS_BUTTON);
        colorBackground.getStyleClass().add(CLASS_BUTTON);
        dimension.getStyleClass().add(CLASS_DIMENSION);
        rename.getStyleClass().add(CLASS_DIMENSION);
        reassign.getStyleClass().add(CLASS_BUTTON);
        mapPane.getStyleClass().add(CLASS_MAP_PANE);
        testing();
       
//        
   
    }

    private void setUpHandler() {
      Controller control = new Controller(app);
      addImage.setOnAction(e-> {
          control.processAddImage();
      }); 
      removeImage.setOnAction(e-> {
          control.processRemoveImage();
      });
      play.setOnAction(e-> {
          control.playMusic();
      });
      pause.setOnAction(e->{
          control.pauseMusic();
      });
      regionTable.setOnMousePressed(e-> {
          
      });
      dimension.setOnAction(e ->{
          control.processDimension();
      });
        
    }

    private void testing() {
           Pane pane = new Pane();
           Pane p = new Pane();
          
           
           Rectangle rec = new Rectangle(app.getGUI().getPrimaryScene().getWidth()/2.5,500);
           p.setClip(rec);
           Rectangle r = new Rectangle(app.getGUI().getPrimaryScene().getWidth()/2.5,500);
          
           Circle c  = new  Circle(100,50,70);
          c.setFill(Paint.valueOf("Red"));
          Circle c1  = new  Circle(100,300,70);
           mapPane.getChildren().add(p);
           pane.getChildren().add(r);
           pane.getChildren().addAll(c,c1);
           p.getChildren().add(pane);
            DataManager data = (DataManager) app.getDataComponent();
            Subregion test = new Subregion("Taiwan","Taipei","Tsai");
            data.addRegion(test);
           
            
       workspace.getItems().addAll(mapPane,TablePane);
//       
       app.getGUI().getAppPane().setCenter(workspace);
         
    }
    
}
