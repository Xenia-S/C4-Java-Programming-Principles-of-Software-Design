
import edu.duke.*; 
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 531;
        
        /*MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);*/
        
        EfficientMarkovModel mTwo = new EfficientMarkovModel(5);
        runModel(mTwo, st, size, seed);

    }
    
    public void testHashMap() {
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 11;
        int seed = 42;
        EfficientMarkovModel mTwo = new EfficientMarkovModel(2);
        runModel(mTwo, st, size, seed);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        long startTime1 = System.nanoTime();    
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        long estimatedTime1 = System.nanoTime() - startTime1;
        
        
        long startTime2 = System.nanoTime();  
        EfficientMarkovModel emTwo = new EfficientMarkovModel(2);
        runModel(emTwo, st, size, seed);
        long estimatedTime2 = System.nanoTime() - startTime2;
        
        
        System.out.println("MarkovModel timer: " + estimatedTime1);
        System.out.println("EfficientMarkovModel timer: " + estimatedTime2);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
