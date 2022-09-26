
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany) {
        ArrayList<QuakeEntry> copyData = new ArrayList<QuakeEntry>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(int i=0; i < howMany; i++) {
            int maxIndex = indexOfLargest(copyData);
            answer.add(copyData.get(maxIndex));
            copyData.remove(maxIndex);
        }
        return answer;
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxIndex = 0;
        for(QuakeEntry qe : data) {
            if(qe.getMagnitude() > data.get(maxIndex).getMagnitude()) {
                maxIndex = data.indexOf(qe);
            }
        }
        
        return maxIndex;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 50);
        for(QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
        System.out.println("There are " + largestQuakes.size() + " largest quakes in " + source);
        
        System.out.println("Earthquake with the largest Magnitude has index " + indexOfLargest(list));
    }

}
