
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int keyLength;
    private HashMap<String, ArrayList<String>> keysAndFollows;
    
    public EfficientMarkovModel(int k) {
        myRandom = new Random();
        keyLength = k;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        keysAndFollows = buildMap();
        printHashMapInfo();
    }
    
    public HashMap<String, ArrayList<String>> buildMap() {
        HashMap<String, ArrayList<String>> keysAndFollows = new HashMap<String, ArrayList<String>>();
        for (int i=0; i <= myText.length() - keyLength; i++) {
            String key = myText.substring(i, i + keyLength);
            String next = "";
            if (i != myText.length() - keyLength) {
                next = myText.substring(i + keyLength, i + keyLength + 1);
            }
            ArrayList<String> follows = new ArrayList<String>();
            if (keysAndFollows.containsKey(key)) {
                follows = keysAndFollows.get(key);
            }
            follows.add(next);
            keysAndFollows.put(key, follows);
        }
        return keysAndFollows;
    }
    
    public ArrayList<String> getFollows(String key) {
        return keysAndFollows.get(key);
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "Don't have training text";
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
            while (follows == null) {
                index = myRandom.nextInt(myText.length() - keyLength);
                key = myText.substring(index, index + keyLength);
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
        return "EfficientMarkovModel of order " + keyLength + ".";
    }
    
    public void printHashMapInfo() {
        System.out.println("The text has " + keysAndFollows.size() + " keys");
        int maxFollows = 0;
        for (String s: keysAndFollows.keySet()) {
            //System.out.println("key: " + s + "; follows:" + keysAndFollows.get(s));
            if (keysAndFollows.get(s).size() > maxFollows) {
                maxFollows = keysAndFollows.get(s).size();
            }
        }
        ArrayList<String> maxFollowsKeys = new ArrayList<String>();
        for (String s: keysAndFollows.keySet()) {
            if (keysAndFollows.get(s).size() == maxFollows) {
                maxFollowsKeys.add(s);
            }
        }
        System.out.println("The key: " + maxFollowsKeys + " has max follows: " + maxFollows);
    }   
}


