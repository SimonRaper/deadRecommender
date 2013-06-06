package dead.recommender;




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

class BooleanRecommender {

  private BooleanRecommender() {
  }

  public static void main(String[] args) throws Exception {

    int ni;
      
    /*DataModel model = new FileDataModel(new File("/home/ubuntu/repositories/MiA/src/main/java/mia/recommender/ch02/intro.csv"));*/
    
        DataModel model = new GenericBooleanPrefDataModel(
        GenericBooleanPrefDataModel.toDataMap(
          new FileDataModel(new File("/home/ubuntu/datasets/RECOMMENDER_SET_NUM.csv"))));
    
    /*DataModel model = new FileDataModel(new File("/home/ubuntu/datasets/RECOMMENDER_SET_NUM.csv"));*/
    ni=model.getNumItems();
    System.out.println(ni);
    UserSimilarity similarity = new LogLikelihoodSimilarity(model);
    UserNeighborhood neighborhood =
      new NearestNUserNeighborhood(200, similarity, model);

    Recommender recommender = new GenericBooleanPrefUserBasedRecommender(
        model, neighborhood, similarity);

    List<RecommendedItem> recommendations =
        recommender.recommend(2561, 10);
    
    
    System.out.println(recommendations.size());

    for (RecommendedItem recommendation : recommendations) {
      System.out.println(recommendation);
    }

  }

}
