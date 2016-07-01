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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;

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
    
    HBox buttonPane;
    
    static final String defalutImage = "/images/default.png";
    static final String CLASS_EDITTOOL = "bordered_mine";
    
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
          String imagePath = FILE_PROTOCOL + PATH_IMAGES + "Default.png";
          Image buttonImage = new Image(imagePath);
          leaderPic = new ImageView();
          leaderPic.setImage(buttonImage);
          leaderPic.setFitWidth(100);
          leaderPic.setFitHeight(100);
         
         flagPic = new ImageView();
         dialog.add(region, 0, 0);
         dialog.add(regionText, 1, 0);
         dialog.add(capital, 0, 1);
         dialog.add(capText, 1, 1); 
         dialog.add(leader, 0, 2);
         dialog.add(ledText, 1, 2);
         dialog.add(flagP, 0, 3);
         dialog.add(flagPic, 1, 3);
         dialog.add(leaderP, 0, 4);
         dialog.add(leaderPic, 1, 4);
         
         buttonPane = new HBox();
         saved = new Button(props.getProperty(PropertyType.SAVE));
         next = new Button(props.getProperty(PropertyType.NEXT));
         prev = new Button(props.getProperty(PropertyType.PREV));
         buttonPane.getChildren().addAll(prev,saved,next);
         dialog.add(buttonPane, 0, 5);
        scene = new Scene(dialog); 
        scene.getStylesheets().add("/regioeditor/css/tdlm_style.css");
        dialog.getStyleClass().add(CLASS_EDITTOOL);
        this.setScene(scene);
        show();
    
         
        }
    }
