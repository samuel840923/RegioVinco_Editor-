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
        
        
      String[] countryList = {"Ordino","Canillo","Encamp","Escaldes-Engordany","La Massana",
          "Andorra la Vella","Sant Julia de Loria"};
      assertEquals("Andorra",load.getName());
      assertEquals("./HW5SampleData/raw_map_data/Andorra.json", load.getfilePath());
      System.out.println("The path are equal");
      assertEquals("0xffa500ff", load.getBackgroundColor().toString());
      System.out.println("The background are equal");
      assertEquals("0x000000ff", load.getBorderColor().toString());
      System.out.println("The border are equal");
      assertEquals(600, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(0.001, load.getThickness(),0);
      System.out.println("The thickness are equal");
       assertEquals(580, load.getLocation().get(0).getX(),0);
      System.out.println("The points x are the same");
       assertEquals(390, load.getLocation().get(0).getY(),0);
      System.out.println("The points y are the same");
      assertEquals(802, load.getDimensionW(),0);
       System.out.println("The dimension x are the same");
       assertEquals(536, load.getDimensionH(),0);
       System.out.println("The dimension y  are the same");
      for(int i=0;i<countryList.length;i++){
           assertEquals(countryList[i], load.getRegion().get(i).getName());
      }
        
   
      
      System.out.println("The region are the same");
      
 
   }
   @Test
   public void testSanMarino() throws IOException{
       TestSave.createSanMarino();
       TestSave.save("SM");
       TestLoad.load("SM");
        load = TestLoad.data;
        String[] countryList = {"Acquaviva","Borgo Maggiore","Chiesanuova","Domagnano","Faetano"
                ,"Fiorentino","Montegiardino","City of San Marino","Serravalle"};
        
      assertEquals("San Marino",load.getName());
      assertEquals("./HW5SampleData/raw_map_data/San Marino.json", load.getfilePath());
      System.out.println("The path are equal");
      assertEquals("0xffa500ff", load.getBackgroundColor().toString());
      System.out.println("The background are equal");
      assertEquals("0x000000ff", load.getBorderColor().toString());
      System.out.println("The border are equal");
      assertEquals(1000, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(0.001, load.getThickness(),0);
      System.out.println("The thickness are equal");
      System.out.println("The points y are the same");
       assertEquals(802, load.getDimensionW(),0);
       System.out.println("The dimension x are the same");
       assertEquals(536, load.getDimensionH(),0);
       System.out.println("The dimension y  are the same");
      for(int i=0;i<countryList.length;i++){
           assertEquals(countryList[i], load.getRegion().get(i).getName());
      }
   }
   @Test
public void testSlovakia() throws IOException{
     TestSave.createSlovakia();
       TestSave.save("S");
       TestLoad.load("S");
        load = TestLoad.data;
        String[] countryList = {"Bratislava","Trnava","Trencin","Nitra","Zilina","Banska Bystrica",
            "Presov","Kosice"
        };
      assertEquals("Slovakia",load.getName()); 
      assertEquals("./HW5SampleData/raw_map_data/Slovakia.json", load.getfilePath());
      System.out.println("The path are equal");
      assertEquals("0xffa500ff", load.getBackgroundColor().toString());
      System.out.println("The background are equal");
      assertEquals("0x000000ff", load.getBorderColor().toString());
      System.out.println("The border are equal");
      assertEquals(50, load.getScale(),0);
      System.out.println("The scale are equal");
      assertEquals(0.01, load.getThickness(),0);
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
          
       for(int i=0;i<countryList.length;i++){
           assertEquals(countryList[i], load.getRegion().get(i).getName());
      }
    
}
}