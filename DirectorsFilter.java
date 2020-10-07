
/**
 * Write a description of class DirectorsFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter
{
    private String who;
    
    public DirectorsFilter(String director)
    {
        who = director;
    }
    
    public boolean satisfies(String id) {
        return who.contains(MovieDatabase.getDirector(id));
    }
}
