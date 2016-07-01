/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author samuelchen
 */
public class DimensionDialog extends Stage{
    static DimensionDialog singleton = null; 
    Label X;
    Label Y;
    
    TextField forX;
    TextField forY;
    
    Button ok;
    Button cancel;
    
    GridPane dialog;
    Scene scene;
    
    static final String OK = "ok";
    static final String CANCEL  = "cancel";
    static final String CLASS_DIMENSION = "dimenstion_dialog";
    
     public static DimensionDialog getSingleton() {
	if (singleton == null)
	    singleton = new DimensionDialog();
	return singleton;
    }
         private DimensionDialog() {}
          public void init(Stage owner) {
          PropertiesManager props = PropertiesManager.getPropertiesManager();
          dialog = new GridPane();
          scene = new Scene(dialog);
          scene.getStylesheets().add("/regioeditor/css/tdlm_style.css");
          
          X = new Label(props.getProperty(PropertyType.FOR_X));
          Y = new Label(props.getProperty(PropertyType.FOR_Y));
          forX = new TextField();
          forY = new TextField();
          
          
          ok = new Button(OK);
          cancel = new Button (CANCEL);
          
          dialog.add(X, 0, 0);
          dialog.add(forX, 1, 0);
          dialog.add(Y, 0, 1);
          dialog.add(forY, 1, 1);
          dialog.add(ok, 0, 2);
          dialog.add(cancel, 1, 2);
          
          dialog.getStyleClass().add(CLASS_DIMENSION);
          this.setScene(scene);
          show();
          
      }
    
}
