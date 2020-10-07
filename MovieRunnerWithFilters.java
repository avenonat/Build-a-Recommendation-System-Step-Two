
/**
 * Write a description of class MovieRunnerWithFilters here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters
{

    public void printAverageRatings() {
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatings(35);
        Collections.sort(rat);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " " + movieTitle);
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void printAverageRatingsByYear() {
        YearAfterFilter filterYear = new YearAfterFilter(2000);
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(20,filterYear);
        Collections.sort(rat);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " " + movieTitle);
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void MovieRunnerWithFilters() {
        GenreFilter genFilter = new GenreFilter("Comedy");
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(20,genFilter);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " " + movieTitle);
            System.out.println(MovieDatabase.getGenres(item));
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void printAverageRatingsByMinutes() {
        MinutesFilter minFilter = new MinutesFilter(105, 135);
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(5, minFilter);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(item) + " " + movieTitle);
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void printAverageRatingsByDirectors() {
        DirectorsFilter dir = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(4, dir);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " " + movieTitle);
            System.out.println(MovieDatabase.getDirector(item));
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        YearAfterFilter filterYear = new YearAfterFilter(1990);
        GenreFilter genFilter = new GenreFilter("Drama");
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters af = new AllFilters();
        af.addFilter(filterYear);
        af.addFilter(genFilter);
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(8, af);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " Year: " + MovieDatabase.getYear(item) + " " + movieTitle);
            System.out.println(MovieDatabase.getGenres(item));
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        DirectorsFilter dir = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter minFilter = new MinutesFilter(90, 180);
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters af = new AllFilters();
        af.addFilter(dir);
        af.addFilter(minFilter);
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(3, af);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(item) + " " + movieTitle);
            System.out.println(MovieDatabase.getGenres(item));
        }
        System.out.println("Movies returned = " + rat.size());
    }
}
