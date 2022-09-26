import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if(qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if (from.distanceTo(qe.getLocation())/1000 < distMax) { //переводим метры  в км
                answer.add(qe);
            } 
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) { // String where can be “start”, ”end”, or “any” 
        System.out.println("Find quakes with phrase " + phrase + " in the " + where + " of the title");
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            String name = qe.getInfo();
            if(where == "start") {
                if(name.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            if(where == "end") {
                if(name.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
            if(where == "any") {
                if(name.contains(phrase)) {
                    answer.add(qe);
                }
            }
            if(where != "start" && where != "end" && where != "any") {
                System.out.println("String that indicates where to search in the title is not correct. Please, type “start”, ”end”, or “any”");
                break;
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> minMag5 = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : minMag5) {
            System.out.println(qe);
        }
        System.out.println("Found " + minMag5.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city2 =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeTo = filterByDistanceFrom(list, 1000, city2);
        for(QuakeEntry qe : closeTo) {
            System.out.println(city.distanceTo(qe.getLocation()) + " " + qe.getInfo());
        }
        System.out.println("Found " + closeTo.size() + " quakes that match that criteria");
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> definiteDepth = filterByDepth(list, -4000.0, -2000.0);
        for(QuakeEntry qe : definiteDepth) {
            System.out.println(qe);
        }
        System.out.println("Found " + definiteDepth.size() + " quakes that match that criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        //ArrayList<QuakeEntry> phraseInTitle = filterByPhrase(list, "end", "Alaska");
        ArrayList<QuakeEntry> phraseInTitle = filterByPhrase(list, "any", "Can");
        //ArrayList<QuakeEntry> phraseInTitle = filterByPhrase(list, "start", "Quarry Blast");
        
        for(QuakeEntry qe : phraseInTitle) {
            System.out.println(qe);
        }
        System.out.println("Found " + phraseInTitle.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        
    }
    
}






























