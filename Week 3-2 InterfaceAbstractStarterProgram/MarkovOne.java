
import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
        
    public MarkovOne() {
        myRandom = new Random();
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
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        //System.out.println("Index is "+ index);
        String key = myText.substring(index, index + 1);
        //System.out.println("key is "+ key);
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(follows.toString());
            if(follows.size() == 0) { // если в конце текста myText стоит один уникальный символ, метод getRandomText не сможет сгенерировать текст нужной длины.
                key = sb.substring(0,1);
                follows = getFollows(key);
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            //System.out.println("sb is "+ sb.toString());
            key = next;
            //System.out.println("next key is "+ key);
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1.";
    }
}
