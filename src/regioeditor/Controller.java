/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import saf.AppTemplate;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import static saf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static saf.settings.AppPropertyType.WORK_FILE_EXT;
import static saf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static saf.settings.AppStartupConstants.PATH_WORK;

/**
 *
 * @author samuelchen
 */
public class Controller {
    AppTemplate app;
  
      public Controller(AppTemplate initApp) {
	app = initApp;
    }
    public void processAddImage(){
         PropertiesManager props = PropertiesManager.getPropertiesManager();
                 FileChooser fc = new FileChooser();
	
		fc.setTitle(props.getProperty(SAVE_WORK_TITLE));
		fc.getExtensionFilters().addAll(
		new FileChooser.ExtensionFilter(props.getProperty(WORK_FILE_EXT_DESC), props.getProperty(WORK_FILE_EXT)));

		File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
}
    public void processRemoveImage(){
        
    }
    public void playMusic(){
        
    }
    public void pauseMusic(){
        
    }

    public void processDimension() {
       
    }

    void processRename() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
      TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(props.getProperty(PropertyType.RENAME_DIALOG));
    dialog.setHeaderText(props.getProperty(PropertyType.RENAME_HEADER));
    dialog.setContentText(props.getProperty(PropertyType.RENAME_CONTENT));
    Optional<String> result = dialog.showAndWait();
    
    }

  public  void processExport() {
      try{
        FileChooser fc = new FileChooser();
         PropertiesManager props = PropertiesManager.getPropertiesManager();
                DataManager dataManager = (DataManager)app.getDataComponent();
		AppFileComponent fileManager = app.getFileComponent();
		fc.setInitialDirectory(new File("./HW5SampleData/work/"));
                fc.setInitialFileName(dataManager.getName());
		fc.getExtensionFilters().add( new FileChooser.ExtensionFilter(props.getProperty(WORK_FILE_EXT_DESC),  props.getProperty(WORK_FILE_EXT)));
                File selectedFile = fc.showSaveDialog(app.getGUI().getWindow());
                
                if(selectedFile!=null){
                fileManager.exportData(dataManager, selectedFile.getAbsolutePath());
                }
                
                }catch(IOException e){
            System.out.println("Error occur");
        }
               
    }
}
