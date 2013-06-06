package deadrecommender;


// import packages 

import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import java.io.*;
import java.util.*;

//The Boolean recommender that will be used to recommend influences

class BooleanRecommender {

  private BooleanRecommender() {
  }

  public static void main(String[] args) throws Exception {
  
    DataModel model = new GenericBooleanPrefDataModel(
    GenericBooleanPrefDataModel.toDataMap(
       new FileDataModel(new File("/home/ubuntu/datasets/RECOMMENDER_SET_NUM.csv"))));
    
    UserSimilarity similarity = new LogLikelihoodSimilarity(model);
    UserNeighborhood neighborhood =
      new NearestNUserNeighborhood(200, similarity, model);

    Recommender recommender = new GenericBooleanPrefUserBasedRecommender(
        model, neighborhood, similarity);

    List<RecommendedItem> recommendations =
        recommender.recommend(2561, 10);

    for (RecommendedItem recommendation : recommendations) {
      System.out.println(recommendation);
    }

  }

}
