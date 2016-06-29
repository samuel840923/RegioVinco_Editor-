/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;
  
   
    
    // THIS IS OUR WORKSPACE HEADING
    Label mapLabel;
    VBox mapPane;
    VBox TablePane;
    HBox editToolBar;
    ColorPicker colorBackground;
    ColorPicker colorBorder;
    Slider zooming ;
    Slider thickness;
    Button addImage;
   
    Button removeImage;
   
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
    
 public Workspace(AppTemplate initApp) throws IOException {
	// KEEP THIS FOR LATER
	app = initApp;

	// KEEP THE GUI FOR LATER
	gui = app.getGUI();
      
        
        // INIT ALL WORKSPACE COMPONENTS
	
        layoutGUI();
        // AND SETUP EVENT HANDLING
	setUpHandler();
    }
  private void layoutGUI(){
       PropertiesManager props = PropertiesManager.getPropertiesManager();
      TablePane = new VBox();
      editToolBar = new HBox();
      mapPane = new VBox();
      addImage = gui.initChildButton(editToolBar, PropertyType.ADD_ICON.toString(), PropertyType.ADD_ITEM_TOOLTIP.toString(), false);
      removeImage = gui.initChildButton(editToolBar, PropertyType.REMOVE_ICON.toString(), PropertyType.REMOVE_ITEM_TOOLTIP.toString(), true);
      zoom = new Label(props.getProperty(PropertyType.ZOOM));
      backgroundColor = new Label(props.getProperty(PropertyType.BACKGROUND_COLOR));
      borderColor = new Label(props.getProperty(PropertyType.BORDER_COLOR));
      borderThick = new Label(props.getProperty(PropertyType.BORDER_THICKNESS));
      zooming = new Slider(1,6,1);
      thickness = new Slider(1,10,1);
      colorBackground = new ColorPicker();
      colorBorder = new ColorPicker();
      editToolBar.getChildren().addAll(backgroundColor,colorBackground,borderColor,colorBorder,borderThick,thickness
      ,zoom,zooming);
      TablePane.getChildren().add(editToolBar);
      regionTable = new TableView();   
       // NOW SETUP THE TABLE COLUMNS
       
        regionColumn = new TableColumn(props.getProperty(PropertyType.SUBREGION_NAME));
        leaderColumn = new TableColumn(props.getProperty(PropertyType.LEADER_NAME));
        capitalColumn = new TableColumn(props.getProperty(PropertyType.CAPITAL));
        regionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("subregion"));
        capitalColumn.setCellValueFactory(new PropertyValueFactory<String, String>("capital"));
        leaderColumn.setCellValueFactory(new PropertyValueFactory<LocalDate, String>("leader"));
       //
        regionTable.getColumns().add(regionColumn);
        regionTable.getColumns().add(capitalColumn);
        regionTable.getColumns().add(leaderColumn);
        
       DataManager dataManager = (DataManager)app.getDataComponent();
       regionTable.setItems(dataManager.getRegion());
       TablePane.getChildren().add(regionTable);
       
 
      
  }
    @Override
    public void reloadWorkspace() {
       
    }

    @Override
    public void initStyle() {
   workspace = new SplitPane();
   workspace.getItems().addAll(mapPane,TablePane);
   app.getGUI().getAppPane().setCenter(workspace);
//    SubregionDialog messageDialog = SubregionDialog.getSingleton();
//    messageDialog.init(messageDialog);
   
   
    }

    private void setUpHandler() {
      
    }
    
}
