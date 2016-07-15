/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.io.File;
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
import saf.AppTemplate;
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
    
    String regionName;
    String capitalName;
    String leaderName;
   static AppTemplate app;
   int currentIndex;
     private SubregionDialog() {}
     
     public static SubregionDialog getSingleton(AppTemplate a) {
	if (singleton == null)
	    singleton = new SubregionDialog();    
        app = a;
	return singleton;
    }
    public void init(Stage primaryStage) {
          PropertiesManager props = PropertiesManager.getPropertiesManager();
          DataManager data = (DataManager)app.getDataComponent();
         
         dialog = new GridPane();
         region = new Label(props.getProperty(PropertyType.SUBREGION_NAME));
         capital = new Label(props.getProperty(PropertyType.CAPITAL));
         leader = new Label(props.getProperty(PropertyType.LEADER_NAME));
         flagP = new Label(props.getProperty(PropertyType.FLAG_PIC));
         leaderP = new Label(props.getProperty(PropertyType.LEADER_PIC));
         regionText = new TextField();
         capText = new TextField();
         ledText = new TextField();
         dialog.setVgap(10);
         
         dialog.add(region, 0, 0);
         dialog.add(regionText, 1, 0);
         dialog.add(capital, 0, 1);
         dialog.add(capText, 1, 1); 
         dialog.add(leader, 0, 2);
         dialog.add(ledText, 1, 2);
         dialog.add(flagP, 0, 3);
         
         dialog.add(leaderP, 0, 4);
         flagPic = new ImageView();
         leaderPic = new ImageView();
         flagPic.setFitWidth(200);
         flagPic.setFitHeight(200);
         leaderPic.setFitHeight(200);
         leaderPic.setFitWidth(200);
         dialog.add(flagPic, 1, 3);
         dialog.add(leaderPic, 1, 4);
         buttonPane = new HBox();
         saved = new Button("Ok");
         next = new Button(props.getProperty(PropertyType.NEXT));
         prev = new Button(props.getProperty(PropertyType.PREV));
         buttonPane.getChildren().addAll(prev,saved,next);
         buttonPane.setSpacing(20);
         dialog.add(buttonPane, 0, 5);
        scene = new Scene(dialog); 
        scene.getStylesheets().add("/regioeditor/css/tdlm_style.css");
        dialog.getStyleClass().add(CLASS_EDITTOOL);
        int size = data.getRegion().size();
        next.setOnAction(e->{
            getInfo((currentIndex+1)%size);
        });
        prev.setOnAction(e->{
            if(currentIndex==0)
                currentIndex = size;
            getInfo(currentIndex-1);
        });
        saved.setOnAction(e->{
            saveInfo();
            close();
        });
        this.setScene(scene);
        
         
         
        }
    public void getInfo(int i){
        currentIndex = i;
        DataManager data = (DataManager)app.getDataComponent();
     regionText.setText( data.getRegion().get(i).getName());
     capText.setText( data.getRegion().get(i).getCapital());
     ledText.setText( data.getRegion().get(i).getLeader());
     String parent = data.getParentDir();
       String flagPath = FILE_PROTOCOL + parent +"/"+ data.getRegion().get(i).getCapital()+" Flag.png";
       String ledPath = FILE_PROTOCOL +  parent +"/" +data.getRegion().get(i).getLeader()+".png";
       System.out.println(ledPath);
       Image flagImage = new Image(flagPath);
       Image ledImage = new Image(ledPath);
       flagPic.setImage(flagImage);
       leaderPic.setImage(ledImage);
    }
    public void diplay(){
        showAndWait();
    }
    public void saveInfo() {
       regionName = regionText.getText();
       capitalName = capText.getText();
       leaderName = ledText.getText();
        DataManager data = (DataManager)app.getDataComponent();
       Subregion s = new Subregion(regionName,capitalName,leaderName);
       s.setG(data.getRegion().get(currentIndex).getG());
       s.setR(data.getRegion().get(currentIndex).getR());
       s.setB(data.getRegion().get(currentIndex).getB());
       data.editRegion(currentIndex, s);
        
     }
    }
