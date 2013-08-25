package deadrecommender;

/*
 *Handles input and output to the recommender
 *When copying to the servlet re-enable the avax.servlet.ServletContext package.
 */

/**
 *
 * @author Simon Raper
 */

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

//import javax.servlet.ServletContext;

import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class DeadRecommenderIO {
    
    private final String[] recNames;;
    private final String[] influencers;  
    private final String[] nameCodes;
    private final DeadRecommender deadRec;
    private final String path;
    
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
    
    public DeadRecommenderIO (String path) throws FileNotFoundException, IOException, TasteException // Need to turn this into an object where the constructor sets up the array     
    {
    
    //Declare variables    
        
    String [] nextLine;

    //Initialise variables 
    
    influencers = new String[14000];
    nameCodes = new String[14000];
    recNames = new String[6];
    int i=0;
    this.path=path;
    
    //this.userID=userID;  
        
    
    //Create a new DeadRecommender object
        
    deadRec=  new DeadRecommender(path);
    
    
    //Load the csv look up file
    
        
    CSVReader reader = new CSVReader(new FileReader(path + "WEB-INF/classes/PERSON_IDS_FULL_NAME.csv"));

    
    //Load in list of influencer names

    while ((nextLine = reader.readNext()) != null) {

        //System.out.println(nextLine[0] + ", " + nextLine[1]);
        nameCodes[i]=nextLine[1];
        influencers[i]=nextLine[2];
        i=i+1;

    }
    
    

    //return(recNames);

    }
    
    public String[] getNames() {
        
        return(recNames);
    }
    
    
    public int getUserID(String userName) {
        int c = 1;

        for (String s : nameCodes) {

            if (s != null) {

                if (s.equals(userName.replaceAll("[^a-zA-Z0-9]", "").toUpperCase()) == true) {
                    //System.out.println(s);
                    //System.out.println(c);
                    return (c);
                }
            }
            c = c + 1;
        }
        return (0);
    }
    
    
    public String submitNames(String[] subNames) throws IOException, FileNotFoundException {
        
        int n=subNames.length;
        int [] userIDArray= new int [n];
        int i=0;
        String notMatched = ""; 
        
        for (String s : subNames){
            userIDArray[i]=getUserID(s);
            if (getUserID(s)==0) {
            	notMatched = notMatched + s;
            }
            i=i+1;
        }
        
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(
                new FileOutputStream(path + "WEB-INF/classes/RECOMMENDER_SET_NUM.Add.csv"), "UTF-8"),
                ',', CSVWriter.NO_QUOTE_CHARACTER);
        for (int j : userIDArray){
        writer.writeNext(new String[] {"1", String.valueOf(j)});
        }
        writer.close();
        deadRec.refresh(null);
        
        return(notMatched);
    }
    
    public void submitID(int userID) throws TasteException {
        
        
    List<RecommendedItem> recommendations = deadRec.recommend(userID, 6);
    
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
        listedNeighbours = new String[80];
        int j=0;
        
        for (long s : deadRec.getNeighbourIDs(userID)) 
        {      
        listedNeighbours[j]=influencers[(int) s-1];
        j=j+1;
        }
        
        return(listedNeighbours);
        
    }
    
}
