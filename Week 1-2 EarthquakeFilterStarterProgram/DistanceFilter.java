public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDist;
    private String name;
    
    public DistanceFilter(String n, Location l, double d) {
        name = n;
        loc = l;
        maxDist = d;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getLocation().distanceTo(loc)/1000 < maxDist) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
}
