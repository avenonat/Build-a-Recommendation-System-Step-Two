
/**
 * Write a description of class FirstRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings
{
    private ArrayList<Movie> csvmethod(CSVParser parser) {
        ArrayList<Movie> MovieData = new ArrayList<Movie>();
        for (CSVRecord movie : parser) {
            Movie newMovie = new Movie(movie.get("id"), movie.get("title"), movie.get("year"),
            movie.get("country"),movie.get("genre"),movie.get("director"),
            Integer.parseInt(movie.get("minutes")),movie.get("poster"));
            MovieData.add(newMovie);
        }
        return MovieData;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> list = loadMovies("ratedmovies_short.csv");
        System.out.println("In the file: " + list.size() + " films.");
        int comedy = 0;
        int over = 0;
        HashMap<String, Integer> director = new HashMap<String, Integer>();
        for (Movie movie : list) {
            if (movie.getGenres().contains("Comedy")) {
                comedy++;
            }
            if (movie.getMinutes() > 150) {
                over++;
            }
            String s = movie.getDirector();
            if (s.contains(",")) {
                for (int i = 0; i < s.length(); i++) {
                    int index = 0;
                    if (s.substring(i).contains(",")) {
                        index = s.indexOf(",", i);
                    }
                    else {
                        index = s.length();
                    }
                    if (director.containsKey(s.substring(i, index))) {
                        director.put(s.substring(i, index), director.get(s.substring(i, index)) +1);
                    }
                    else {
                        director.put(s.substring(i, index),1);
                    }
                    i = index;
                }
            }
            else {
                if (director.containsKey(s)) {
                    director.put(s, director.get(s) +1);
                }
                else {
                    director.put(s,1);
                }
            }
        }
        System.out.println("Films with comedy genre " + comedy + " films");
        System.out.println("There are " + over + " films over 150 minutes");
        int max = 0;
        String dir = "";
        for (Map.Entry<String, Integer> set : director.entrySet()) {
            if (set.getValue() > max) {
                max = set.getValue();
                dir = set.getKey();
            }
        }
        System.out.println(dir + " " + max);
    }
    
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> list = csvmethod(parser);
        return list;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> list = loadRaters("ratings_short.csv");
        int max = 0;
        int count = 0;
        String max_id = "";
        ArrayList<String> num_films = new ArrayList<String>();
        for (Rater red : list) {
            System.out.println(red.getID() + " " + red.getaRatings());
        }
        System.out.println("In the file: " + list.size() + " raters.");
        for (Rater temp : list) {
            if (temp.getID().equals("2")) {
                System.out.println("Was rated: " + temp.getaRatings().size());
            }
            if (temp.getaRatings().size() > max) {
                max = temp.getaRatings().size();
                max_id = temp.getID();
            }
            HashMap<String,Rating> test = temp.getaRatings();
            for (Map.Entry<String, Rating> s : test.entrySet()) {
                if (s.getValue().getItem().equals("1798709")) {
                    count += 1;
                }
                String film = s.getKey();
                if (!num_films.contains(film)) {
                    num_films.add(film);
                }
            }
        }
        System.out.println("The maximum ratings is: " + max + " by " + max_id);
        System.out.println("1798709 rated: " + count);
        System.out.println("Here is: " + num_films.size() + " films.");
    }
    
    public ArrayList<Rater> csvMethod2(CSVParser parser){
        ArrayList<Rater> loadRaters = new ArrayList<Rater>();
        HashMap<String, ArrayList<Rating>> step = new HashMap<String, ArrayList<Rating>>();
        for (CSVRecord rater : parser) {
            String name = rater.get("rater_id");
            Rating create = new Rating(rater.get("movie_id"), Double.parseDouble(rater.get("rating")));
            if(!step.containsKey(name)){
               ArrayList<Rating> cl = new ArrayList<Rating>();
               cl.add(create);
               step.put(name, cl);
            }
            else{
               ArrayList<Rating> inc = step.get(name);
               inc.add(create);
               step.put(name, inc);
            }
        }
        for (Map.Entry<String, ArrayList<Rating>> set : step.entrySet()) {
            Rater fin = new EfficientRater(set.getKey());
            ArrayList<Rating> cl = set.getValue();
            for (Rating rt : cl) {
                fin.addRating(rt.getItem(), rt.getValue());
            }
            loadRaters.add(fin);
        }
        return loadRaters;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> loadRaters = csvMethod2(parser);
        return loadRaters;
    }
}
