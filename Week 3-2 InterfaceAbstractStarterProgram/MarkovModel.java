
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int keyLength;
    
    public MarkovModel(int k) {
        myRandom = new Random();
        keyLength = k;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim(); // удаляются начальные и конечные пробелы
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        if (myText.length() < keyLength){
            return ("The number of characters to predict the next character (" + keyLength + ") is too big for the training text ("+ myText.length() + ")");
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - keyLength);
        String key = myText.substring(index, index + keyLength);
        sb.append(key);
        for(int k=0; k < numChars - keyLength; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                key = sb.substring(0, keyLength);
                follows = getFollows(key);
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order " + keyLength + ".";
    }
}


