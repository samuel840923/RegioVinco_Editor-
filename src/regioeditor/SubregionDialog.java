/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author samuelchen
 */
public class SubregionDialog extends Stage{
    static SubregionDialog singleton = null;
    Scene scene;
    Label leader;
    Label capital;
    Label region;
    Label flagP;
    Label leaderP;
    
    TextField regionText;
    TextField capText;
    TextField ledText;
    
    ImageView leaderPic;
    ImageView flagPic;
    
    Button saved;
    Button next;
    Button prev;
    
    GridPane dialog;
     private SubregionDialog() {}
     
     public static SubregionDialog getSingleton() {
	if (singleton == null)
	    singleton = new SubregionDialog();
	return singleton;
    }
    public void init(Stage primaryStage) {
          PropertiesManager props = PropertiesManager.getPropertiesManager();
         initModality(Modality.WINDOW_MODAL);
         initOwner(primaryStage);
         dialog = new GridPane();
         region = new Label(props.getProperty(PropertyType.SUBREGION_NAME));
         capital = new Label(props.getProperty(PropertyType.CAPITAL));
         leader = new Label(props.getProperty(PropertyType.LEADER_NAME));
         flagP = new Label(props.getProperty(PropertyType.FLAG_PIC));
         leaderP = new Label(props.getProperty(PropertyType.LEADER_PIC));
         regionText = new TextField();
         capText = new TextField();
         ledText = new TextField();
         
         dialog.add(region, 0, 0);
         dialog.add(regionText, 1, 0);
         dialog.add(capital, 0, 1);
         dialog.add(capText, 1, 1); 
         dialog.add(leader, 0, 2);
         dialog.add(ledText, 1, 2);
         dialog.add(flagP, 0, 3);
         dialog.add(leaderP, 0, 4);
            
        scene = new Scene(dialog); 
        this.setScene(scene);
        show();
    
         
        }
    }
