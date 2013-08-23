package deadrecommender;

//Comment for testing

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import au.com.bytecode.opencsv.CSVReader;

public class CSVReturn {
	
	private final  String[] nameCodes;
	
    public CSVReturn () throws FileNotFoundException, IOException // Need to turn this into an object where the constructor sets up the array     
    {
    	
        //Declare variables    
        
        String [] nextLine;
        

        //Initialise variables 
        
        nameCodes = new String[14000];

        int i=0;
	
        
	    CSVReader reader = new CSVReader(new FileReader("/var/lib/tomcat7/webapps/hello/WEB-INF/classes/PERSON_IDS_FULL_NAME.csv"));
        
        //CSVReader reader = new CSVReader(new FileReader("PERSON_IDS_FULL_NAME.csv"));
	
	    while ((nextLine = reader.readNext()) != null) {

        //System.out.println(nextLine[0] + ", " + nextLine[1]);
        nameCodes[i]=nextLine[1];
        i=i+1;

        }
	    
	
    }
    

	public String[] getRows() {
    	
    	return(nameCodes);
    }

}
