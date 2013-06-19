/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deadrecommender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;



/**
 *
 * @author ubuntu
 */
public class TestDeadRecommender {
    
    
     public static void main( String[] args ) throws FileNotFoundException, IOException, TasteException // Need to turn this into an object where the constructor sets up the array

    {
     
        DeadRecommenderIO dio= new DeadRecommenderIO();
        
        String[] names= new String[4];
        String[] neigh= new String[200];
        
        //System.out.println(dio.getUserID("Aristotle"));
        
        dio.submitID(dio.getUserID("Jim Bob"));
        
        neigh=dio.getNeighbours(dio.getUserID("Jim Bob"));
        
        names = dio.getNames();
        
      for (String s : names)  {
      System.out.println(s);
      }
      
      
      System.out.println("The neighbours: ");
      
      for (String s : neigh){
      System.out.println(s);
      }

    }
    
}
