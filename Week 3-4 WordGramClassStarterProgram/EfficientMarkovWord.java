import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        myMap = buildMap();
        printHashMapInfo();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for(int i = start; i < words.length - myOrder; i++) {
            WordGram temp = new WordGram(words, i, myOrder);
            if(target.equals(temp)) {
                return i;
            }
        }
        return -1;
    }
    
    private HashMap<Integer, ArrayList<String>> buildMap() {
        HashMap<Integer, ArrayList<String>> myMap = new HashMap<Integer, ArrayList<String>>();
        for (int i=0; i <= myText.length - myOrder; i++) {
            WordGram kGram = new WordGram(myText, i, myOrder);
            String next = "";
            if (i != myText.length - myOrder) {
                next = myText[i + myOrder];
            }
            ArrayList<String> follows = new ArrayList<String>();
            if (myMap.containsKey(kGram.toString().hashCode())) {
                follows = myMap.get(kGram.toString().hashCode());
            }
            follows.add(next);
            System.out.println("kGram: " + kGram.toString().hashCode() + ", next: " + next);
            System.out.println(follows);
            myMap.put(kGram.toString().hashCode(), follows);
        }
        return myMap;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        return myMap.get(kGram.hashCode());
    }
    
    public void printHashMapInfo() {
        System.out.println("The text has " + myMap.size() + " keys");
        int maxFollows = 0;
        for (int hashCode: myMap.keySet()) {
            System.out.println("key: " + hashCode + "; follows:" + myMap.get(hashCode));
            if (myMap.get(hashCode).size() > maxFollows) {
                maxFollows = myMap.get(hashCode).size();
            }
        }
        ArrayList<Integer> maxFollowsKeys = new ArrayList<Integer>();
        for (int hashCode: myMap.keySet()) {
            if (myMap.get(hashCode).size() == maxFollows) {
                maxFollowsKeys.add(hashCode);
            }
        }
        System.out.println("The key: " + maxFollowsKeys.toString() + " has max follows: " + maxFollows);
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            while (follows == null) {
                index = myRandom.nextInt(myText.length - myOrder);
                kGram = new WordGram(myText, index, myOrder);
                follows = getFollows(kGram);
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            System.out.println("key: " + kGram.toString() + ", follows: " + follows + "\n" + "next: " + next);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }


}
