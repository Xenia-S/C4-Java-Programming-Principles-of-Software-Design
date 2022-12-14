
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String name;
    
    public MagnitudeFilter(String n, double min, double max) {
        name = n;
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
}
