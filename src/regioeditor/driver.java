package regioeditor;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;
import javafx.application.Application;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static saf.settings.AppStartupConstants.PATH_DATA;
import static saf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static saf.settings.AppStartupConstants.WORKSPACE_PROPERTIES_FILE_NAME;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samuelchen
 */
public class driver extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
         PropertiesManager props = PropertiesManager.getPropertiesManager();
         props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
	 props.loadProperties(WORKSPACE_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
       SubregionDialog messageDialog = SubregionDialog.getSingleton();
	messageDialog.init(primaryStage);
    }
    public static void main(String[] args){
        launch(args);
    }
    
}
