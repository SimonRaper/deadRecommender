package deadrecommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class evaluateRecommender {
	
	private static DataModel model;
	
	private static int x; //counter
	
    public static void main( String[] args ) throws FileNotFoundException, IOException, TasteException 
   {
   
    	//RandomUtils.useTestSeed();
    	
    	double[] arrayPrecision;
    	arrayPrecision = new double[20];
    	
    	double[] arrayRecall;
    	arrayRecall = new double[20];
    	
    	model= new FileDataModel ( new File("/home/ubuntu/git/simpleweb/src/main/resources/RECOMMENDER_SET_NUM_3PLUS.csv"));
    	
    	int k = 0; // counter for the array to hold the results
    	
    	for (x = 20; x <= 300; x = x+20) {
    	
    	//for (x = 3; x < 20; x = x+1) {
    	
    	RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
    	
    	RecommenderBuilder builder = new RecommenderBuilder(){

			@Override
			public Recommender buildRecommender(DataModel model)
					throws TasteException {
				
				    UserSimilarity similarity = new LogLikelihoodSimilarity(model);
				    //UserSimilarity similarity = new TanimotoCoefficientSimilarity(model);
				    
			        UserNeighborhood neighborhood = new NearestNUserNeighborhood(x, similarity, model);
			        System.out.println(x);
				
				return new GenericBooleanPrefUserBasedRecommender(
				        model, neighborhood, similarity);
			}
    		
    	
    	};
    	
    	IRStatistics stats = evaluator.evaluate(builder, null, model, null, 6, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD,1.0);
    	arrayPrecision[k]=stats.getPrecision();
    	//System.out.println(arrayPrecision[k]);
    	arrayRecall[k]=stats.getRecall();
    	//System.out.println(arrayRecall[k]);
    	k=k+1;
    	}
    	
    	for (int j=0; j<20; j=j+1){
    		System.out.println(arrayPrecision[j]);;
    	}
   }
}
