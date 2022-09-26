import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyQuakeData = new ArrayList<QuakeEntry>(quakeData);
        int minIndex = 0;
        for(int k=0; k < howMany; k++) {
            for(int i=1; i < copyQuakeData.size(); i++) {
                QuakeEntry qe = copyQuakeData.get(i);
                if(current.distanceTo(qe.getLocation()) < current.distanceTo(copyQuakeData.get(minIndex).getLocation())) {
                    minIndex = i;
                }
            }
            ret.add(copyQuakeData.get(minIndex));
            copyQuakeData.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
