/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static saf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static saf.settings.AppPropertyType.WORK_FILE_EXT;
import static saf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static saf.settings.AppStartupConstants.PATH_WORK;

/**
 *
 * @author samuelchen
 */
public class NewMapDialog extends Stage{
    static NewMapDialog singleton = null;
    Label fileName;
    Label parentDirectory;
    Label load;
    
    Button browseDirectory;
    Button loadfile;
    
    GridPane dialog;
    TextField file;
    
    Scene scene;
    Button ok;
    
    Label chose;
    Label loaded;
    
    static final String NEWMAP_DIALOG = "new_map_dialog";
    
      public static NewMapDialog getSingleton() {
	if (singleton == null)
	    singleton = new NewMapDialog();
	return singleton;
    }
     private NewMapDialog() {}
      public void init(Stage owner) {
            
            dialog = new GridPane();
            scene = new Scene(dialog);
            scene.getStylesheets().add("/regioeditor/css/tdlm_style.css");
            PropertiesManager props = PropertiesManager.getPropertiesManager();
             fileName =new Label(props.getProperty(PropertyType.FILE_NAME));
             parentDirectory = new Label(props.getProperty(PropertyType.PARENT));
             load = new Label(props.getProperty(PropertyType.LOAD));
             
             browseDirectory = new Button(props.getProperty(PropertyType.BROWSE));
             loadfile = new Button(props.getProperty(PropertyType.BROWSE));
             
             file = new TextField();
             ok = new Button("Ok");
             chose = new Label("");
             loaded = new Label("");
             
             dialog.add(fileName, 0, 0);
             dialog.add(file, 1, 0);
             dialog.add(parentDirectory, 0, 1);
             dialog.add(browseDirectory, 1, 1);
             dialog.add(chose, 2, 1);
             dialog.add(load, 0, 2);
             dialog.add(loadfile, 1, 2);
             dialog.add(loaded, 2, 2);
             dialog.add(ok, 0, 3);
             dialog.getStyleClass().add(NEWMAP_DIALOG);
             browseDirectory.setOnAction(e -> { 
                 chooseDirectory();
             });
             loadfile.setOnAction(e -> { 
                 loadFile();
             });
             this.setScene(scene);
             show();
             
              
      }

    public  void chooseDirectory() {
          PropertiesManager props = PropertiesManager.getPropertiesManager();
                DirectoryChooser fc = new DirectoryChooser();
		
		fc.setTitle(props.getProperty(SAVE_WORK_TITLE));
//		fc.getExtensionFilters().addAll(
//		new FileChooser.ExtensionFilter(props.getProperty(WORK_FILE_EXT_DESC), props.getProperty(WORK_FILE_EXT)));
                File selectedFile = fc.showDialog(singleton);
                
    }

    private void loadFile() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
                 FileChooser fc = new FileChooser();
	
		fc.setTitle(props.getProperty(SAVE_WORK_TITLE));
		
		

		File selectedFile = fc.showSaveDialog(singleton);
                System.out.println(selectedFile.getPath());
                
    }
     
}
