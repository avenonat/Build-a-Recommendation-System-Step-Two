
/**
 * Write a description of class GenreFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter
{
    // instance variables - replace the example below with your own
    private String gen;

    /**
     * Constructor for objects of class GenreFilter
     */
    public GenreFilter(String genre)
    {
        gen = genre;
    }
    
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(gen);
    }
}
