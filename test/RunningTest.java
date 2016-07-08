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
public class RunningTest {
   static DataManager save;
   static DataManager load;
    public RunningTest() {
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
   public void testAndorra() throws IOException{
       TestSave.createAndorra();
       TestSave.save("A");
       TestLoad.load("A");
        load = TestLoad.data;
        
        
       
        
      assertEquals("./HW5SampleData/raw_map_data/Andorra.json", load.getfilePath());
      System.out.println("The path are equal");
      assertEquals("0xffa500ff", load.getBackgroundColor().toString());
      System.out.println("The background are equal");
      assertEquals("0x000000ff", load.getBorderColor().toString());
      System.out.println("The border are equal");
      assertEquals(1.5, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(0.75, load.getThickness(),0);
      System.out.println("The thickness are equal");
       assertEquals(580, load.getLocation().get(0).getX(),0);
      System.out.println("The points x are the same");
       assertEquals(390, load.getLocation().get(0).getY(),0);
      System.out.println("The points y are the same");
      assertEquals(802, load.getDimensionW(),0);
       System.out.println("The dimension x are the same");
       assertEquals(536, load.getDimensionH(),0);
       System.out.println("The dimension y  are the same");
      
     
          
          Subregion s = load.getRegion().get(0);
          String rl = s.getName();
          String cl = s.getCapital();
          String ll = s.getLeader();
          int redl = s.getR();
          int greenl = s.getG();
          int bluel = s.getB();
          
          assertEquals("Orgino",rl);
          assertEquals("Ordino (town)",cl);
          assertEquals("Ventura Espot",ll); 
          assertEquals(200,redl,0); 
          assertEquals(200,greenl,0);
          assertEquals(200,bluel,0);
   
      
      System.out.println("The region are the same");
      
 
   }
   @Test
   public void testSanMarino() throws IOException{
       TestSave.createSanMarino();
       TestSave.save("SM");
       TestLoad.load("SM");
        load = TestLoad.data;
        
      assertEquals("./HW5SampleData/raw_map_data/San Marino.json", load.getfilePath());
      System.out.println("The path are equal");
      assertEquals("0xffa500ff", load.getBackgroundColor().toString());
      System.out.println("The background are equal");
      assertEquals("0x000000ff", load.getBorderColor().toString());
      System.out.println("The border are equal");
      assertEquals(1.5, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(0.75, load.getThickness(),0);
      System.out.println("The thickness are equal");
      System.out.println("The points y are the same");
       assertEquals(802, load.getDimensionW(),0);
       System.out.println("The dimension x are the same");
       assertEquals(536, load.getDimensionH(),0);
       System.out.println("The dimension y  are the same");
     
          Subregion s = load.getRegion().get(0);
          String rl = s.getName();
          String cl = s.getCapital();
          String ll = s.getLeader();
          int redl = s.getR();
          int greenl = s.getG();
          int bluel = s.getB();
     
          assertEquals("Acquaviva",rl);
          assertEquals("null",cl);
          assertEquals("Lucia Tamagnini",ll); 
          assertEquals(225,redl,0); 
          assertEquals(225,greenl,0);
          assertEquals(225,bluel,0);
   
      System.out.println("The region are the same");
}
   @Test
public void testSlovakia() throws IOException{
     TestSave.createSlovakia();
       TestSave.save("S");
       TestLoad.load("S");
        load = TestLoad.data;
        
        
      assertEquals("./HW5SampleData/raw_map_data/Slovakia.json", load.getfilePath());
      System.out.println("The path are equal");
      assertEquals("0xffa500ff", load.getBackgroundColor().toString());
      System.out.println("The background are equal");
      assertEquals("0x000000ff", load.getBorderColor().toString());
      System.out.println("The border are equal");
      assertEquals(1.5, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(0.75, load.getThickness(),0);
      System.out.println("The thickness are equal");
       assertEquals(540, load.getLocation().get(0).getX(),0);
      System.out.println("The points x are the same");
       assertEquals(365, load.getLocation().get(0).getY(),0);
      System.out.println("The points y are the same");
        assertEquals(25, load.getLocation().get(1).getX(),0);
      System.out.println("The points 2 x are the same");
       assertEquals(25, load.getLocation().get(1).getY(),0);
      System.out.println("The points 2 y are the same");
      assertEquals(802, load.getDimensionW(),0);
       System.out.println("The dimension x are the same");
       assertEquals(536, load.getDimensionH(),0);
       System.out.println("The dimension y  are the same");
          
          Subregion s = load.getRegion().get(0);
          String rl = s.getName();
          String cl = s.getCapital();
          String ll = s.getLeader();
          int redl = s.getR();
          int greenl = s.getG();
          int bluel = s.getB();
          
          assertEquals("Bratislava",rl);
          assertEquals("null",cl);
          assertEquals("null",ll); 
          assertEquals(250,redl,0); 
          assertEquals(250,greenl,0);
          assertEquals(250,bluel,0);
   
      
      System.out.println("The region are the same");
      
    
}
}