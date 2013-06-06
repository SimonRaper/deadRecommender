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
     
        DeadRecommenderIO dio= new DeadRecommenderIO(1037);
        
        String[] names= new String[4];
        names = dio.getNames();
        
        
      System.out.println(names[0]);
        

    }
    
}
