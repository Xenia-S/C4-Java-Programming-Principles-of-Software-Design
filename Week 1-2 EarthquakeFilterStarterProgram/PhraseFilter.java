
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;  // has one of three values: “start”, ”end”, or “any”
    private String phrase;
    private String name;
    
    public PhraseFilter(String n, String w, String p) {
        name = n;
        where = w;
        phrase = p;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if(where.equals("start")) {
            if(qe.getInfo().startsWith(phrase)) {
                return true;
            }
        }
        if(where.equals("end")) {
            if(qe.getInfo().endsWith(phrase)) {
                return true;
            }
        }
        if(where.equals("any")){
            if(qe.getInfo().contains(phrase)) {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
}
