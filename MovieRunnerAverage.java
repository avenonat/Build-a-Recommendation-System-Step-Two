
/**
 * Write a description of class MovieRunnerAverage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class MovieRunnerAverage
     */
    public MovieRunnerAverage()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatings(12);
        Collections.sort(rat);
        for (Rating r : rat) {
            String item = r.getItem();
            String movieTitle = sr.getTitle(item);
            System.out.println(r.getValue() + " " + movieTitle);
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String movieRequest = "Vacation";
        String id = sr.getID(movieRequest);
        if (id.equals("NO SUCH TITLE")) {
            System.out.println(id);
        }
        else {
            double aveRating = sr.getAverageByID(id, 1);
            System.out.println("The average rating for the movie " + movieRequest + " is " + aveRating);
        }
    }
}
