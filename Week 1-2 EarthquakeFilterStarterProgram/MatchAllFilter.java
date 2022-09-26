
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filterList;
    private ArrayList<String> namesList;
    
    public MatchAllFilter() {
        filterList = new ArrayList<Filter>();
        namesList = new ArrayList<String>();
    }
    
    public void addFilter(Filter f) {
        filterList.add(f);
        namesList.add(f.getName());
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for(Filter filter : filterList) {
            if(!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        return namesList.toString();
    }
}
