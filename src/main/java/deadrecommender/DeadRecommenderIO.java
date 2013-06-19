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
    
    private final String[] recNames;;
    private final String [] influencers;   
    private final DeadRecommender deadRec;
    
    /* To do
     * turn main into a method to which you can submit a list of names
     * Methods would be (a) submit your preferences (b) submit a famous person (c) get the recommendations (d) get the neighbours
     * When you submit the names it strips out the no alpha numeric characters and looks up their item ID
     * Use the update feature of the recommender to allow us to write in the new entries
     * Produce a recommendation for the user
     * Add the full names to person_IDs look up file DONE
     * Output the recommendations
     * Also show your neighborhood
     * Create another method for dinner party recommendtations
     */
    
    public DeadRecommenderIO () throws FileNotFoundException, IOException, TasteException // Need to turn this into an object where the constructor sets up the array     
    {
    
    //Declare variables    
        
    String [] nextLine;

    //Initialise variables 
    
    influencers = new String[14000];
    recNames = new String[4];
    int i=0;
    
    //this.userID=userID;  
        
    
    //Create a new DeadRecommender object
        
    deadRec=  new DeadRecommender();
    
    
    //Load the csv look up file
        
    CSVReader reader = new CSVReader(new FileReader("/home/ubuntu/datasets/PERSON_IDS_FULL_NAME.csv"));

    
    //Load in list of influencer names

    while ((nextLine = reader.readNext()) != null) {

        //System.out.println(nextLine[0] + ", " + nextLine[1]);

        influencers[i]=nextLine[2];

        i=i+1;

    }
    
    

    //return(recNames);

    }
    
    public String[] getNames() {
        
        return(recNames);
    }
    
    
    public int getUserID(String userName) {
        int c=1;
        // Replace with a while loop
        for (String s : influencers)
        {
            if (s.equals(userName)==true) {
                //System.out.println(s);
                //System.out.println(c);
                break;
            }
            c=c+1;
        }
        return(c);
    }
    
    
    public void submitNames(String[] subNames) {
        
        
    }
    
    public void submitID(int userID) throws TasteException {
        
        
    List<RecommendedItem> recommendations = deadRec.recommend(userID, 4);
    
    System.out.println(userID);
    
    int j=0;
    
    for (RecommendedItem recommendation : recommendations) 
    {      
        recNames[j]=influencers[(int) recommendation.getItemID()-1];
        j=j+1;
    }
        
    }
    
    public String[] getNeighbours(int userID) throws TasteException {
        
        String[] listedNeighbours;
        listedNeighbours = new String[200];
        int j=0;
        
        for (long s : deadRec.getNeighbourIDs(userID)) 
        {      
        listedNeighbours[j]=influencers[(int) s-1];
        j=j+1;
        }
        
        return(listedNeighbours);
        
    }
    
}
