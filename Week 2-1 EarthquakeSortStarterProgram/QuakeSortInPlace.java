
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxDepth = from;
        for(int i=from+1; i < quakes.size(); i++) {
            if(quakes.get(i).getDepth() < quakes.get(maxDepth).getDepth()) {
                maxDepth = i;
            }
        }
        return maxDepth;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            System.out.println("Finished " + (i+1) + " pass");
            if(checkInSortedOrder(in)) {
                break;
            }
        }
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for(int i=0; i<in.size(); i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    public void sortByLargestDepthFixedPasses(ArrayList<QuakeEntry> in, int count) {
        for(int i=0; i<in.size(); i++) {
            if(i >= count){ // если вставить это условие в петлю for, то можно выйти за пределы ArrayList
                break;
            }
            System.out.println("Pass "+ (i+1));
            
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }

    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for(int i=0; i < quakeData.size() - numSorted - 1; i++) {
            QuakeEntry q1 = quakeData.get(i);
            QuakeEntry q2 = quakeData.get(i+1);
            if(q1.getMagnitude() > q2.getMagnitude()) {
                quakeData.set(i, q2);
                quakeData.set(i+1, q1);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i=0; i < quakes.size()-1; i++) {
            if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }  
        
        for(int i=0; i < in.size(); i++) {
             onePassBubbleSort(in, i);
             System.out.println("Printing Quakes after pass " + i);
             for (QuakeEntry qe: in) { 
                 System.out.println(qe);
             }
         }
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        for(int i=0; i < in.size(); i++) {
             onePassBubbleSort(in, i);
             System.out.println("Finished pass " + (i+1));
             
             if(checkInSortedOrder(in)) {
                break;
             }
         }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        //sortByLargestDepthFixedPasses(list, 50);
        
        
        System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
