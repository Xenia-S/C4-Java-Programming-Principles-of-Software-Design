
import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
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
        while (pos < myText.length()-4) {
            //System.out.println("myText.length() is: "+ myText.length());
            //System.out.println("Pos is: "+ pos);
            int index = myText.indexOf(key, pos);
            //System.out.println("Index is: "+ index);
            if(index == -1 || index == myText.length()-4) {  
                break;
            }
            String next = myText.substring(index+4, index+5);
            //System.out.println("Next is: "+ next);
            follows.add(next);
            //System.out.println(follows.toString());
            pos = index+1;
        }
        //System.out.println("- getFollows complete -");
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        //System.out.println("Index:"+ index);
        String key = myText.substring(index, index+4);
        //System.out.println("key:"+ key);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(follows.toString());
            if(follows.size() == 0) { // если в конце текста myText стоят уникальные символы, метод getRandomText не сможет сгенерировать текст нужной длины.
                key = sb.substring(0,4);
                follows = getFollows(key);
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            //System.out.println("next index:"+ index + ", next:"+ next);
            sb.append(next);
            //System.out.println("sb:"+ sb.toString());
            key = key.substring(1) + next;
            //System.out.println("next key:"+ key);
        }
        
        return sb.toString();
    }
}


