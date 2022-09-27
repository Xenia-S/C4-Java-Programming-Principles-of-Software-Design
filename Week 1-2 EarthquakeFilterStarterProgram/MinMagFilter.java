public class MinMagFilter implements Filter
{
    private double magMin; 
    private String name;
    
    public MinMagFilter(String n, double min) { 
        name = n;
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    public String getName() {
        return name;
    }

}
