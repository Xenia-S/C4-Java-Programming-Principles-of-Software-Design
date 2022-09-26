
import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList follows = new ArrayList<String>();
        int pos = 0;
        int keyLength = key.length();
        while (pos < myText.length() - keyLength) {
            int index = myText.indexOf(key, pos);
            if(index == -1 || index == myText.length()-keyLength) {  
                break;
            }
            String next = myText.substring(index+keyLength, index+keyLength+1);
            follows.add(next);
            pos = index+1;
        }
        return follows;
    }
          
    abstract public String getRandomText(int numChars);

}
