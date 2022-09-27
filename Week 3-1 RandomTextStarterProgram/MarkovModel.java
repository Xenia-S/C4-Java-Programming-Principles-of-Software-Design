
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int predNum;
    
    public MarkovModel(int p) {
        myRandom = new Random();
        predNum = p;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim(); // удаляются начальные и конечные пробелы
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()-predNum) {
            int index = myText.indexOf(key, pos);
            if(index == -1 || index == myText.length()-predNum) {  
                break;
            }
            String next = myText.substring(index+predNum, index+predNum+1);
            follows.add(next);
            pos = index+1;
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        if (myText.length() < predNum){
            return ("The number of characters to predict the next character (" + predNum + ") is too big for the training text ("+ myText.length() + ")");
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-predNum);
        String key = myText.substring(index, index+predNum);
        sb.append(key);
        for(int k=0; k < numChars-predNum; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) { // если в конце текста myText стоят уникальные символы, метод getRandomText не сможет сгенерировать текст нужной длины.
                key = sb.substring(0,predNum);
                follows = getFollows(key);
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
}


