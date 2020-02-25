package A1;

import A1.Chaining.*;
import A1.Open_Addressing.*;
import java.io.*;
import java.util.*;

public class main {

    /**
     * Calculate 2^w
     */
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }

    /**
     * Uniformly generate a random integer between min and max, excluding both
     */
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        //if the seed is equal or above 0, we use the input seed, otherwise not.
        if (seed >= 0) {
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max - min - 1);
        return i + min + 1;
    }

    /**
     * export CSV file
     */
    public static void generateCSVOutputFile(String filePathName, ArrayList<Double> alphaList, ArrayList<Double> avColListChain, ArrayList<Double> avColListProbe) {
        File file = new File(filePathName);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            int len = alphaList.size();
            fw.append("Alpha");
            for (int i = 0; i < len; i++) {
                fw.append("," + alphaList.get(i));
            }
            fw.append('\n');
            fw.append("Chain");
            for (int i = 0; i < len; i++) {
                fw.append("," + avColListChain.get(i));
            }
            fw.append('\n');
            fw.append("Open Addressing");
            for (int i = 0; i < len; i++) {
                fw.append(", " + avColListProbe.get(i));
            }
            fw.append('\n');
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * populate an array with random keys
     */
    public static void generateKeys(int[] keyList, int numKeys) {
    	int key, i;
    	ArrayList<Integer> usedKeysList;
    	
    	usedKeysList = new ArrayList<Integer>();
    	i = 0;
    	
    	while (usedKeysList.size() != numKeys) {
    		key = generateRandom(0, 55, -1);
    		
    		if (!usedKeysList.contains(key) && key > 0) {
    			keyList[i] = key;
    			usedKeysList.add(key);
    			i++;
    		}
    	}
    }
    
    public static void main(String[] args) {

        /*===========PART 1 : Experimenting with n===================*/
        //Initializing the three arraylists that will go into the output 
        ArrayList<Double> alphaList = new ArrayList<Double>();
        ArrayList<Double> avColListChain = new ArrayList<Double>();
        ArrayList<Double> avColListProbe = new ArrayList<Double>();

        //Keys to insert into both hash tables
        int[] keysToInsert = {164, 127, 481, 132, 467, 160, 205, 186, 107, 179,
            955, 533, 858, 906, 207, 810, 110, 159, 484, 62, 387, 436, 761, 507,
            832, 881, 181, 784, 84, 133, 458, 36};

        //values of n to test for in the experiment
        int[] nList = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
        //value of w to use for the experiment on n
        int w = 10;

        for (int n : nList) {

            //initializing two hash tables with a seed
            Chaining MyChainTable = new Chaining(w, 137);
            Open_Addressing MyProbeTable = new Open_Addressing(w, 137);

            /*Use the hash tables to compute the average number of 
                        collisions over keys keysToInsert, for each value of n. 
                        The format of the three arraylists to fillis as follows:
                        
                        alphaList = arraylist of all tested alphas 
                                   (corresponding to each tested n)
                        avColListChain = average number of collisions for each
                                         Chain experiment 
                                         (make sure the order matches alphaList)
                        avColListProbe =  average number of collisions for each
                                         Linear Probe experiment
                                           (make sure the order matches)
                        The CSV file will output the result which you can visualize
             */
            //ADD YOUR CODE HERE
            double numChainCollisions = 0;
            double numProbeCollisions = 0;
            int key;
            
            for (int i = 0; i < n; i++) {
            	key = keysToInsert[i];
            	
            	numChainCollisions += MyChainTable.insertKey(key);
            	numProbeCollisions += MyProbeTable.insertKey(key);
            }
            
            alphaList.add(MyChainTable.getLoadFactor(n));
            avColListChain.add(numChainCollisions / (double) n);
            avColListProbe.add(numProbeCollisions / (double) n);
        }

        generateCSVOutputFile("n_comparison.csv", alphaList, avColListChain, avColListProbe);

        /*===========    PART 2 : Test removeKey  ===================*/
 /* In this exercise, you apply your removeKey method on an example.
        Make sure you use the same seed, 137, as you did in part 1. You will
        be penalized if you don't use the same seed.
         */
        //Please not the output CSV will be slightly wrong; ignore the labels.
        ArrayList<Double> removeCollisions = new ArrayList<Double>();
        ArrayList<Double> removeIndex = new ArrayList<Double>();
        int[] keysToRemove = {6, 8, 164, 180, 127, 3, 481, 132, 4, 467, 5, 160,
            205, 186, 107, 179};

        //ADD YOUR CODE HERE
        double numCollisions;
        Open_Addressing MyProbeTable = new Open_Addressing(w, 137);
        
        /* Key insertion */
        for (int i = 0; i < 16; i++) {
        	MyProbeTable.insertKey(keysToInsert[i]);
        }
        
        /* Key removal */
        for (int i = 0; i < 16; i++) {
        	numCollisions = (double) MyProbeTable.removeKey(keysToRemove[i]);
        	removeIndex.add((double) i);
        	removeCollisions.add(numCollisions);
        }
        
        generateCSVOutputFile("remove_collisions.csv", removeIndex, removeCollisions, removeCollisions);

        /*===========PART 3 : Experimenting with w===================*/

 /*In this exercise, the hash tables are random with no seed. You choose 
                values for the constant, then vary w and observe your results.
         */
        //generating random hash tables with no seed can be done by sending -1
        //as the seed. You can read the generateRandom method for detail.
        //randomNumber = generateRandom(0,55,-1);
        //Chaining MyChainTable = new Chaining(w, -1);
        //Open_Addressing MyProbeTable = new Open_Addressing(w, -1);
        //Lists to fill for the output CSV, exactly the same as in Task 1.
        ArrayList<Double> alphaList2 = new ArrayList<Double>();
        ArrayList<Double> avColListChain2 = new ArrayList<Double>();
        ArrayList<Double> avColListProbe2 = new ArrayList<Double>();

        //ADD YOUR CODE HERE
        final int NUM_KEYS = 50;
        
        Chaining MyChainTable2 = null;
        Open_Addressing MyProbeTable2 = null;
        
        int numChainCollisions, numProbeCollisions;
        
        //int[] wList = {14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int[] wList = {14, 13, 12, 11, 10};
        int[] keyList = new int[NUM_KEYS];
        
        for (int _w: wList) {
        	numChainCollisions = 0;
        	numProbeCollisions = 0;
        	
        	/* 10 iterations for each w */
        	for (int i = 0; i < 10; i++) {
        		MyChainTable2 = new Chaining(_w, -1);
                MyProbeTable2 = new Open_Addressing(_w, -1);
        		
                /* New list of keys for each iteration */
        		generateKeys(keyList, NUM_KEYS);
        		
        		/* Key insertion */
        		for (int key: keyList) {
            		numChainCollisions += MyChainTable2.insertKey(key);
            		numProbeCollisions += MyProbeTable2.insertKey(key);
        		}
        	}
        	
        	alphaList2.add(MyChainTable2.getLoadFactor(NUM_KEYS));
        	avColListChain2.add((double) numChainCollisions / (double) (NUM_KEYS * 10));
        	avColListProbe2.add((double) numProbeCollisions / (double) (NUM_KEYS * 10));
        }
        
        generateCSVOutputFile("w_comparison.csv", alphaList2, avColListChain2, avColListProbe2);
    }

}
