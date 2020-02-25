package A1;

import static A1.main.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initialized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }
    /**
     * Implements the hash function h(k)
     */
    public int chain(int key) {
    	int temp = (this.A * key) % power2(this.w);
    	
    	if (temp < 0) temp += power2(this.w);
    	
        return temp >> (this.w - this.r);
    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
        return (chain(key) + i) % this.m;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
    	int numCollisions, hashValue, i;

    	/* Searching for empty slot in table */
        for (
        	i = 0, numCollisions = 0, hashValue = probe(key, 0);
        	i < this.m && !isSlotEmpty(hashValue);
        	i++, numCollisions++, hashValue = probe(key, i));
        
        /* If i did not reach the size of the table (m),
         * then successfully found an empty slot.
         */
        if (i != this.m)
        	this.Table[hashValue] = key;

        return numCollisions;
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        int numCollisions, hashValue;
        
        numCollisions = 0;
        
        for (int i = 0; i < this.m; i++, hashValue = probe(key, i)) {
        	hashValue = probe(key, i);
        	
        	/* If Table[hashValue] == -1, then we know the key isn't in the table. Stop the function*/
        	if (this.Table[hashValue] != key && isSlotEmpty(hashValue))
        		break;
        	
        	/* If Table[hashValue] isn't empty and isn't the key, then it's a collision. */
        	else if (this.Table[hashValue] != key && !isSlotEmpty(hashValue))
        		numCollisions++;
  
        	/* Key is found.
        	 * Setting slot to a different number from -1: -2.
        	 * 
        	 * During next lookups:
    		 * 	If Table[hashValue] = -1 then we know the key isn't in the table, and stop the function.
    		 * 	If Table[hashValue] = -2 then we know a key was deleted there.
    		 **/
        	else {
        		this.Table[hashValue] = -2;
        		break;
        	}
        }
        
        return numCollisions;
    }

}
