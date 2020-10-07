
/**
 * Write a description of class MovieRunnerSimilarRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings
{
    public void printAverageRatings() {
        FourthRatings sr = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> rat = sr.getAverageRatings(1);
        Collections.sort(rat);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " " + movieTitle);
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        YearAfterFilter filterYear = new YearAfterFilter(1990);
        GenreFilter genFilter = new GenreFilter("Drama");
        FourthRatings sr = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        AllFilters af = new AllFilters();
        af.addFilter(filterYear);
        af.addFilter(genFilter);
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        ArrayList<Rating> rat = sr.getAverageRatingsByFilter(8, af);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println(r.getValue() + " Year: " + MovieDatabase.getYear(item) + " " + movieTitle);
            System.out.println(MovieDatabase.getGenres(item));
        }
        System.out.println("Movies returned = " + rat.size());
    }
    
    
    public void printSimilarRatings() {
       FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "71";
        int numTopRaters = 20;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatings = MoviesAndRatings.getSimilarRatings(rater_id,numTopRaters,minimalRate);
        for(Rating r : getSimilarRatings){
            //MovieDatabase class gets superhandy:
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
            break;
        }
    }
    
    public void printSimilarRatingsByGenre() {
        GenreFilter filterGenre = new GenreFilter("Mystery");
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "964";
        int numTopRaters = 20;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatings = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate, filterGenre);
        for(Rating r : getSimilarRatings){
            //MovieDatabase class gets superhandy:
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
            break;
        }
    }
    
    public void printSimilarRatingsByDirector() {
        DirectorsFilter filterDirector = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "120";
        int numTopRaters = 10;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatings = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate, filterDirector);
        for(Rating r : getSimilarRatings){
            //MovieDatabase class gets superhandy:
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
            break;
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        AllFilters GenreAndMinutes = new AllFilters();
        GenreFilter genre = new GenreFilter("Drama");
        MinutesFilter minutes = new MinutesFilter(80,160);
        GenreAndMinutes.addFilter(genre);
        GenreAndMinutes.addFilter(minutes);
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "168";
        int numTopRaters = 10;
        int minimalRate = 3;
        ArrayList<Rating> getSimilarRatings = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate, GenreAndMinutes);
        for(Rating r : getSimilarRatings){
            //MovieDatabase class gets superhandy:
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
            break;
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        AllFilters YearAfterAndMinutes = new AllFilters();
        YearAfterFilter year = new YearAfterFilter(1975);
        MinutesFilter minutes = new MinutesFilter(70,200);
        YearAfterAndMinutes.addFilter(year);
        YearAfterAndMinutes.addFilter(minutes);
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "314";
        int numTopRaters = 10;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatingsByFilter = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate,YearAfterAndMinutes);
        for(Rating r : getSimilarRatingsByFilter){
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
            break;
        }
    }
}
