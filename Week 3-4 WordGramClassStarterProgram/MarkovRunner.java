
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 200, 844); 
    } 
    
    public void testHashMap() {
        String st = "this is a test yes this is really a test";
        printOut(st);
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 50, 42); 
    }
    
    public void testHashMap2() {
        //String st = "this is a test yes this is really a test yes a test this is wow";
        //printOut(st);
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        EfficientMarkovWord2 markovWord = new EfficientMarkovWord2(2); 
        runModel(markovWord, st, 100, 65); 
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        long startTime1 = System.nanoTime(); 
        MarkovWord markovWord = new MarkovWord(3); 
        runModel(markovWord, st, 100, 42); 
        long estimatedTime1 = System.nanoTime() - startTime1;
        
        long startTime2 = System.nanoTime(); 
        EfficientMarkovWord2 eMarkovWord = new EfficientMarkovWord2(2);
        runModel(eMarkovWord, st, 100, 42);
        long estimatedTime2 = System.nanoTime() - startTime2;
        
        System.out.println("MarkovWord timer: " + estimatedTime1);
        System.out.println("EfficientMarkovWord timer: " + estimatedTime2);
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
