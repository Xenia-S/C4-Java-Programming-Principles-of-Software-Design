import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
         
        System.out.println("read data for "+list.size()+" quakes");

        /*
        Filter f1 = new MagnitudeFilter(4.0, 5.0); 
        ArrayList<QuakeEntry> filtered  = filter(list, f1); 
        Filter f2 = new DepthFilter(-35000.00, -12000.00);
        filtered  = filter(filtered, f2); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        System.out.println("Printed " + filtered.size() + " items");
        */
       
        /*
        Filter f1 = new DistanceFilter("", new Location(35.42, 139.43), 10000); 
        ArrayList<QuakeEntry> filtered  = filter(list, f1); 
        Filter f2 = new PhraseFilter("", "end", "Japan");
        filtered  = filter(filtered, f2); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        System.out.println("Printed " + filtered.size() + " items");
        */
        
        /*
        Filter f1 = new DistanceFilter(" less than 10,000,000 meters (10,000 km) from Tokyo, Japan", new Location(35.42, 139.43), 10000); 
        ArrayList<QuakeEntry> filtered  = filter(list, f1); 
        Filter f2 = new PhraseFilter("are in Japan", "end", "Japan");
        filtered  = filter(filtered, f2); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        System.out.println("Printed " + filtered.size() + " items");
        */
       
        /*
        Filter f1 = new MagnitudeFilter("magnitude between 4.0 and 5.0", 4.0, 5.0); 
        ArrayList<QuakeEntry> filtered  = filter(list, f1); 
        Filter f2 = new DepthFilter("depth between -35,000.0 and -12,000.0 inclusive", -35000.00, -12000.00);
        filtered  = filter(filtered, f2); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        System.out.println("Printed " + filtered.size() + " items");
        */
        
        /*
        Filter f1 = new DistanceFilter("1,000,000 meters (1,000 km) from Denver, Colorado", new Location(39.7392, -104.9903), 1000); 
        ArrayList<QuakeEntry> filtered  = filter(list, f1); 
        Filter f2 = new PhraseFilter("end with an ‘a’ in their title", "end", "a");
        filtered  = filter(filtered, f2); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        System.out.println("Printed " + filtered.size() + " items");
        */
        
        Filter f1 = new MagnitudeFilter("magnitude between 3.5 and 4.5", 3.5, 4.5); 
        ArrayList<QuakeEntry> filtered  = filter(list, f1); 
        Filter f2 = new DepthFilter("depth between -55,000.0 and -20,000.0 inclusive", -55000.00, -20000.00);
        filtered  = filter(filtered, f2); 
        for (QuakeEntry qe: filtered) { 
            System.out.println(qe);
        } 
        System.out.println("Printed " + filtered.size() + " items");
        
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        /* ONLY for small test files:
        for(QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
       
        System.out.println("read " + list.size() + " entries");
        MatchAllFilter maf = new MatchAllFilter();
        
        /*
        maf.addFilter(new MagnitudeFilter("magnitude 0.0-3.0", 0.0, 3.0));
        maf.addFilter(new DistanceFilter("no further than 10,000 km from Tulsa, Oklahoma", new Location(36.1314, -95.9372), 10000));
        maf.addFilter(new PhraseFilter("string \"Ca\" at any position", "any", "Ca"));
        */
       
        /*
        maf.addFilter(new MagnitudeFilter("magnitude between 0.0 and 2.0", 0.0, 2.0));
        maf.addFilter(new DepthFilter("depth between -100,000.0 and -10,000.0", -100000, -10000));
        maf.addFilter(new PhraseFilter("the letter “a” is in the title", "any", "a"));
        */
        
        /*
        maf.addFilter(new MagnitudeFilter("magnitude between 0.0 and 3.0", 0.0, 3.0));
        maf.addFilter(new DistanceFilter("the distance from Tulsa, Oklahoma is less than 10,000,000 meters (or 10,000 km)", new Location(36.1314, -95.9372), 10000));
        maf.addFilter(new PhraseFilter("string \"Ca\" at any position", "any", "Ca"));
        */
        
        /*
        maf.addFilter(new MagnitudeFilter("magnitude between 1.0 and 4.0", 1.0, 4.0));
        maf.addFilter(new DepthFilter("depth between -180,000.0 and -30,000.0 inclusive", -180000, -30000));
        maf.addFilter(new PhraseFilter("if the letter “o” is in the title", "any", "o"));
        */
        
        maf.addFilter(new MagnitudeFilter("magnitude between 0.0 and 5.0", 0.0, 5.0));
        maf.addFilter(new DistanceFilter("the distance from Billund, Denmark is less than 3,000,000 meters", new Location(55.7308, 9.1153), 3000));
        maf.addFilter(new PhraseFilter("if the letter “e” is in the title", "any", "e"));
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for(QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
        System.out.println(filtered.size() + " entries meet that criteria");
        System.out.println(maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
