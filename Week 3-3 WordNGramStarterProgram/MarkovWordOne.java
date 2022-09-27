import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, String target, int start) {
        for(int i = start; i < words.length; i++) {
            if(words[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String test = "this is just a test yes this is a simple test";
        String[] testArray = test.split("\\s+");
        
        int test1 = indexOf(testArray, "this", 0);
        System.out.println("test - find “this” starting at 0: " + test1);
        
        int test2 = indexOf(testArray, "this", 3);
        System.out.println("test - find “this” starting at 3: " + test2);
        
        int test3 = indexOf(testArray, "frog", 0);
        System.out.println("test - find “frog” starting at 0: " + test3);
        
        int test4 = indexOf(testArray, "frog", 5);
        System.out.println("test - find “frog” starting at 5: " + test4);
        
        int test5 = indexOf(testArray, "simple", 2);
        System.out.println("test - find “simple” starting at 2: " + test5);
        
        int test6 = indexOf(testArray, "test", 5);
        System.out.println("test - find “test” starting at 5: " + test6);
    } 
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 1) {
            int index = indexOf(myText, key, pos);
            if(index == -1 || index >= myText.length - 1) {  
                break;
            }
            String next = myText[index + 1];
            follows.add(next);
            pos = index+1;
        }
        return follows;
    }

}
