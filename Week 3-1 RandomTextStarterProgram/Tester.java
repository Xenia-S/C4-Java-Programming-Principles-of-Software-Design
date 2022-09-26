
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        //           0123456789012345678901234567890123
        String st = "this is a test yes this is a test.ttt";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println("Follows size " + follows.size());
        System.out.println(follows.toString()); // [h,e, ,h,e,.]
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //System.out.println(st);
        st = st.replace('\n', ' ');
        //System.out.println(st);
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("o");
        System.out.println("Follows size " + follows.size());
        System.out.println(follows.toString());
    }
    
    public void testGetFollowsWithFile2() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //System.out.println(st);
        st = st.replace('\n', ' ');
        //System.out.println(st);
        MarkovModel markov = new MarkovModel(2);
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println("Follows size " + follows.size());
        System.out.println(follows.toString());
    }
}
