import java.util.*;

public class EfficientMarkovWord2 implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovWord2(int order) {
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
    
    private HashMap<String, ArrayList<String>> buildMap() {
        HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
        for (int i=0; i <= myText.length - myOrder; i++) {
            WordGram kGram = new WordGram(myText, i, myOrder);
            String next = "";
            if (i != myText.length - myOrder) {
                next = myText[i + myOrder];
            }
            ArrayList<String> follows = new ArrayList<String>();
            if (myMap.containsKey(kGram.toString())) {
                follows = myMap.get(kGram.toString());
            }
            follows.add(next);
            //System.out.println("kGram: " + kGram.toString() + ", next: " + next);
            //System.out.println(follows);
            myMap.put(kGram.toString(), follows);
        }
        return myMap;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        return myMap.get(kGram.toString());
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
            //System.out.println("key: " + kGram.toString() + ", follows: " + follows + "\n" + "next: " + next);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public void printHashMapInfo() {
        System.out.println("The text has " + myMap.size() + " keys");
        int maxFollows = 0;
        for (String s: myMap.keySet()) {
            //System.out.println("key: " + s + "; follows:" + myMap.get(s));
            if (myMap.get(s).size() > maxFollows) {
                maxFollows = myMap.get(s).size();
            }
        }
        ArrayList<String> maxFollowsKeys = new ArrayList<String>();
        for (String s: myMap.keySet()) {
            if (myMap.get(s).size() == maxFollows) {
                maxFollowsKeys.add(s);
            }
        }
        System.out.println("------------------------------");
        System.out.println("The key: " + maxFollowsKeys.toString() + " has max follows: " + maxFollows);
        for (String s: maxFollowsKeys) {
            System.out.println("- key: " + s + ", follows: " + myMap.get(s).toString());
        }
        System.out.println("------------------------------");
    }
}

