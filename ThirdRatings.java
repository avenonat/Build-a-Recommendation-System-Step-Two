
/**
 * Write a description of class ThirdRating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings
{
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        int numVotes = 0;
        double total = 0.0;
        if (minimalRaters == 0) {
            return 0.0;
        }
        for (Rater ser : myRaters) {
            HashMap<String,Rating> vote = ser.getaRatings();
            for (Map.Entry<String, Rating> rate : vote.entrySet()) {
                if (rate.getKey().equals(id)) {
                    numVotes++;
                    total += rate.getValue().getValue();
                    break;
                }
            }
        }
        if (numVotes < minimalRaters) {
            return -1;
        }
        else {
            return (total / numVotes);
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> list = new ArrayList<Rating>();
        for (String m : movies) {
            getAverageByID(m,minimalRaters);
            Rating a = new Rating(m, getAverageByID(m, minimalRaters));
            if (a.getValue() > -1) {
                list.add(a);
            }
        }
        return list;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String m : movies) {
            getAverageByID(m,minimalRaters);
            Rating a = new Rating(m, getAverageByID(m,minimalRaters));
            if (a.getValue() > -1) {
                list.add(a);
            }
        }
        return list;
    }
}
