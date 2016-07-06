/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author samuelchen
 */
public class Subregion {

   
 public static final String DEFAULT_CAPITAL = "defalut";
    public static final String DEFAULT_LEADER= "defalut";
    public static final String DEFAULT_REGION_NAME = "defalut";

    
    final StringProperty capital;
    final StringProperty leader;
    final StringProperty name;
    int red;
    int green;
    int blue;
 

       
    public Subregion() 
    {
        capital = new SimpleStringProperty(DEFAULT_CAPITAL);
        leader = new SimpleStringProperty(DEFAULT_LEADER);
        name = new SimpleStringProperty(DEFAULT_REGION_NAME);
    }
    public Subregion(String initR, String initC,String initL){
        this();
        name.set(initR);
        capital.set(initC);
       leader.set(initL);
       
       }

    public String getCapital() {
        return capital.get();
    }
    public String getName(){
        return name.get();
    }
    public void setName(String value){
        name.set(value);
    }
    public StringProperty regionProperty(){
        return name;
    }
    public void setCapital(String value) {
        capital.set(value);
    }

    public StringProperty capitalProperty() {
        return capital;
    }

    public String getLeader() {
        return leader.get();
    }

    public void setLeader(String value) {
        leader.set(value);
    }

    public StringProperty leaderProperty() {
        return leader;
    }

    
    
    public void reset() {
        setCapital(DEFAULT_CAPITAL);
        setLeader(DEFAULT_LEADER);
        setName(DEFAULT_REGION_NAME);
      
    }
    public void setR(int r){
        red = r;
    }
      public void setG(int g){
        green = g;
    }
        public void setB(int b){
        blue = b;
    }
        public int getR(){
            return red;
        }
         public int getG(){
            return green;
        }
          public int getB(){
            return blue;
        }
}

