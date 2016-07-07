/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import regioeditor.DataManager;
import regioeditor.Subregion;
import test_bed.TestLoad;
import test_bed.TestSave;

/**
 *
 * @author samuelchen
 */
public class AndorraTest {
   static DataManager save;
   static DataManager load;
    public AndorraTest() {
    }
    
   
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class TestSave.
     */
   

    /**
     * Test of save method, of class TestSave.
     */
//   @Test
//   public void testSave(){
//       System.out.println("Creating Andorra");
//       TestSave.createAndorra();
//       save = TestSave.data;
//       System.out.println("save end");
//   }
//    @Test
//   public void testLoad() throws IOException{
//        System.out.println("loading Andorra");
//       TestLoad.load("A");
//       load = TestLoad.data;
//       
//   }
   @Test
   public void testEqual() throws IOException{
       TestSave.createAndorra();
       TestLoad.load("A");
        load = TestLoad.data;
        save = TestSave.data;
        
        String path= save.getfilePath();
        Color back = save.getBackgroundColor();
        Color border = save.getBorderColor();
        double scale = save.getScale();
        double thick = save.getThickness();
        ArrayList<Point2D> location = save.getLocation();
        ObservableList<Subregion>  region = save.getRegion();
        
      assertEquals(path, load.getfilePath());
      System.out.println("The path are equal");
      assertEquals(back, load.getBackgroundColor());
      System.out.println("The background are equal");
      assertEquals(border, load.getBorderColor());
      System.out.println("The border are equal");
      assertEquals(scale, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(thick, load.getThickness(),0);
      System.out.println("The thickness are equal");
       assertEquals(location, load.getLocation());
      System.out.println("The points are the same");
      for(int i=0;i<region.size();i++){
          Subregion sub = region.get(i);
          String rs = sub.getName();
          String cs = sub.getCapital();
          String ls = sub.getLeader();
          int reds = sub.getR();
          int greens = sub.getG();
          int blues = sub.getB();
          Subregion s = load.getRegion().get(i);
          String rl = s.getName();
          String cl = s.getCapital();
          String ll = s.getLeader();
          int redl = s.getR();
          int greenl = s.getG();
          int bluel = s.getB();
          
          assertEquals(rs,rl);
          assertEquals(cs,cl);
          assertEquals(ls,ll); 
          assertEquals(reds,redl,0); 
          assertEquals(greens,greenl,0);
          assertEquals(blues,bluel,0);
   
      }
      System.out.println("The region are the same");
      
      
      
      
      
       
   }

}