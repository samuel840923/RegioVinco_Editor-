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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import properties_manager.PropertiesManager;
import saf.AppTemplate;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import saf.controller.AppFileController;
import static saf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static saf.settings.AppPropertyType.WORK_FILE_EXT;
import static saf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_WORK;
import saf.ui.AppMessageDialogSingleton;

/**
 *
 * @author samuelchen
 */
public class Controller {
    AppTemplate app;
    Audio audio;
      public Controller(AppTemplate initApp) {
	app = initApp;
        audio = new Audio();
        
    }
    public String processAddImage(){
         PropertiesManager props = PropertiesManager.getPropertiesManager();
                 FileChooser fc = new FileChooser();
	
		fc.setTitle(props.getProperty(SAVE_WORK_TITLE));
		fc.getExtensionFilters().addAll(
		new FileChooser.ExtensionFilter("PNG image", "*.png"));

		File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
            String url = FILE_PROTOCOL + selectedFile;
            return url;
}
    public void processRemoveImage(){
        
    }
    public void playMusic(){
        try {
            DataManager data = (DataManager)app.getDataComponent();
            String path = data.getParentDir()+"/"+data.getName()+" National Anthem.mid";
            audio.loadAudio(data.getName(),path);
        }catch(Exception e ){
            
        }
         DataManager data = (DataManager)app.getDataComponent();
        audio.play(data.getName(), true);
    }
    public void pauseMusic(){
         DataManager data = (DataManager)app.getDataComponent();
        audio.stop(data.getName());
    }

    public void processDimension() {
         DataManager data = (DataManager)app.getDataComponent();
      DimensionDialog di = DimensionDialog.getSingleton();
      di.init(di);
      data.setDimensionW(di.getNewX());
       data.setDimensionH(di.getNewY());
      
    }

    void processRename() {
         DataManager data = (DataManager)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
      TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(props.getProperty(PropertyType.RENAME_DIALOG));
    dialog.setHeaderText(props.getProperty(PropertyType.RENAME_HEADER));
    dialog.setContentText(props.getProperty(PropertyType.RENAME_CONTENT));
    Optional<String> result = dialog.showAndWait();
 
    
 String newParent =    AppFileController.changeName(result.get());
 data.setParentDir(newParent);
 data.setName(result.get());
 System.out.println(data.getName());
    
    }

  public  void processExport() {
      try{
          DataManager data = (DataManager)app.getDataComponent();
          String parent = data.getParentDir()+"/"+data.getName()+".rvm";
          FileManager fileManager = new FileManager();
                File selectedFile = new File(parent);
                
                if(selectedFile!=null){
                    String fileString = selectedFile.getAbsolutePath();
                    String png = fileString.replace("rvm", "png");
                     Workspace workspace = (Workspace)app.getWorkspaceComponent();
                     File export = new File(png);
                     
                    WritableImage image = workspace.clipPane.snapshot(new SnapshotParameters(), null);
                   ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", export);
                   fileManager.exportData(data, selectedFile.getAbsolutePath());
                }
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("Sucess!", "You have exported the file");
                }catch(IOException e){
            System.out.println("Error occur");
        }
               
    }

    void processSubregion(int i) {
         DataManager data = (DataManager)app.getDataComponent();
      SubregionDialog di = SubregionDialog.getSingleton(app);
      di.init(di);
      di.getInfo(i);
      di.diplay();
    }
}
