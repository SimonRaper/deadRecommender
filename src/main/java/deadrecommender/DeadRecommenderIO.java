package deadrecommender;

/*
 *Handles input and output to the recommender
 */

/**
 *
 * @author Simon Raper
 */

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public class DeadRecommenderIO {
    
    private final String[] recNames;
    private final int userID;
    
    /* To do
     * turn main into a method to which you can submit a list of names
     * When you submit the names it strips out the no alpha numeric characters and looks up their item ID
     * Use the update feature of the recommender to allow us to write in the new entries
     * Produce a recommendation for the user
     * Add the full names to person_IDs look up file DONE
     * Output the recommendations
     * Also show your neighborhood
     * Create another method for dinner party recommendtations
     */
    
    public DeadRecommenderIO (int userID) throws FileNotFoundException, IOException, TasteException // Need to turn this into an object where the constructor sets up the array     
    {
        
    this.userID=userID;    
        
    DeadRecommender deadRec=  new DeadRecommender();
        
    CSVReader reader = new CSVReader(new FileReader("/home/ubuntu/datasets/PERSON_IDS_FULL_NAME.csv"));

    String [] nextLine;

    String[] influencers;
  
    influencers = new String[14000];
    
    recNames = new String[4];

    int i=0;

   

    while ((nextLine = reader.readNext()) != null) {

        //System.out.println(nextLine[0] + ", " + nextLine[1]);

        influencers[i]=nextLine[2];

        i=i+1;

    }
    
    List<RecommendedItem> recommendations = deadRec.recommend(userID, 4);
    
    int j=0;
    
    for (RecommendedItem recommendation : recommendations) 
    {      
        recNames[j]=influencers[(int) recommendation.getItemID()];
        j=j+1;
    }

    //return(recNames);

    }
    
    public String[] getNames() {
        
        return(recNames);
    }
    
}
