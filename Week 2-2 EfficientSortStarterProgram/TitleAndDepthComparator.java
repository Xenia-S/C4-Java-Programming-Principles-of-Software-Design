
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int compare = q1.getInfo().compareTo(q2.getInfo());
        if(compare == 0) {
            compare = Double.compare(q1.getDepth(), q2.getDepth());
        }
        return compare;
    }
}
