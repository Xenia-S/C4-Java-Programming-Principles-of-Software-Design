public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    private String name;
    
    public DepthFilter(String n, double min, double max) {
        name = n;
        minDepth = min;
        maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
