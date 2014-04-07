package com.bookrecommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.apache.commons.cli2.OptionException; 
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.m.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.m.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;

public class MyBookRecommender {
    
    public static void main(String... args) throws FileNotFoundException, TasteException, IOException, OptionException {
        
        // create data source (m) - from the csv file            
        File rate = new File("/home/cloudera/Books_Ratings.csv");                        
        DataModel m = new FileDataModel(rate);
        
        // create a simple recommender on our data
        CachingRecommender cr = new CachingRecommender(new SlopeOneRecommender(m));

        // for all users
        for (LongPrimitiveIterator it = m.getUserIDs(); it.hasNext();){
            long id = it.nextLong();
            
            // get the recommendations for the user
            List<RecommendedItem> recommendations = cr.recommend(id, 10);
            
            // if empty write something
            if (recommendations.size() == 0){
                System.out.print("User ");System.out.print(id);System.out.println(": no recommendations");
            }
                            
            // print the list of recommendations for each 
            for (RecommendedItem recommendedItem : recommendations) {
                System.out.print("User ");
                System.out.print(id);
                System.out.print(": ");
                System.out.println(recommendedItem);
            }
        }        
    }
}