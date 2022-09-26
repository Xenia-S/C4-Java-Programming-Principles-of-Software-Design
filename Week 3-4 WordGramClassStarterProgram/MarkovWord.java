import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
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
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - myOrder) {
            int index = indexOf(myText, kGram, pos);
            if(index == -1) {  
                break;
            }
            String next = "";
            if(index != myText.length - myOrder) {
                next = myText[index + myOrder];
            }
            follows.add(next);
            pos = index + myOrder;
        }
        return follows;
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
            //System.out.println("key: " + key + ", follows: " + follows + "\n" + "next: " + next);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }


}
