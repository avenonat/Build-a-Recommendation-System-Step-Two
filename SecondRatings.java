/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        ArrayList<Rating> list = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            String movie_id = m.getID();
            Rating a = new Rating(movie_id, getAverageByID(movie_id, minimalRaters));
            if (a.getValue() > -1) {
                list.add(a);
            }
        }
        return list;
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (id.equals(m.getID())) {
                return m.getTitle();
            }
        }
        return "Movie ID not found in the database";
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (title.equals(m.getTitle())) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}