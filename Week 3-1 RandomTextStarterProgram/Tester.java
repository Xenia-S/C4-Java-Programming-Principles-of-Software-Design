import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
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
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("o");
        System.out.println("Follows size " + follows.size());
        System.out.println(follows.toString());
    }
    
    public void testGetFollowsWithFile2() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(2);
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println("Follows size " + follows.size());
        System.out.println(follows.toString());
    }
}
