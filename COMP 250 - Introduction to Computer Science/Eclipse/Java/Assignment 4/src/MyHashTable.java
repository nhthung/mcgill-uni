import java.util.ArrayList;
import java.util.Iterator;


class MyHashTable<K,V> {
	/*
	 *   Number of entries in the HashTable.
	 */
	private int entryCount = 0;

	/*
	 * Number of buckets. The constructor sets this variable to its initial value,
	 * which eventually can get changed by invoking the rehash() method.
	 */
	private int numBuckets;

	/**
	 * Threshold load factor for rehashing.
	 */
	private final double MAX_LOAD_FACTOR=0.75;

	/**
	 *  Buckets to store lists of key-value pairs.
	 *  Traditionally an array is used for the buckets and
	 *  a linked list is used for the entries within each bucket.
	 *  We use an Arraylist rather than an array, since the former is simpler to use in Java.
	 */

	ArrayList< HashLinkedList<K,V> >  buckets;

	/*
	 * Constructor.
	 *
	 * numBuckets is the initial number of buckets used by this hash table
	 */

	MyHashTable(int numBuckets) {

		//  ADD YOUR CODE BELOW HERE
		this.numBuckets = numBuckets;
		//HashLinkedList<K,V> empty = new HashLinkedList<K,V>();
		//HashLinkedList<K,V> empty;
		buckets = new ArrayList< HashLinkedList<K,V> >();
		for(int i = 0; i < numBuckets; i++)
			//empty = new HashLinkedList<K,V>();
			buckets.add(new HashLinkedList<K,V>());

		//  ADD YOUR CODE ABOVE HERE

	}

	/**
	 * Given a key, return the bucket position for the key.
	 */
	private int hashFunction(K key) {

		return  Math.abs( key.hashCode() ) % numBuckets ;
	}

	/**
	 * Checking if the hash table is empty.
	 */

	public boolean isEmpty()
	{
		if (entryCount == 0)
			return true;
		else
			return(false);
	}

	/**
	 *   return the number of entries in the hash table.
	 */

	public int size()
	{
		return(entryCount);
	}

	/**
	 * Adds a key-value pair to the hash table. If the load factor goes above the
	 * MAX_LOAD_FACTOR, then call the rehash() method after inserting.
	 *
	 *  If there was a previous value for the given key in this hashtable,
	 *  then overwrite it with new value and return the old value.
	 *  Otherwise return null.
	 */

	public  V  put(K key, V value) {

		//  ADD YOUR CODE BELOW HERE
		int hashValue = hashFunction(key);
		if(this.containsKey(key)){
			V oldValue = this.remove(key);
			buckets.get(hashValue).add(key, value);
			return oldValue;
		}
		else{
			buckets.get(hashValue).add(key, value);
			entryCount++;
			double loadFactor = (double) entryCount / (double) numBuckets;
			if(loadFactor > MAX_LOAD_FACTOR)
				this.rehash();
		}

		//  ADD YOUR CODE ABOVE HERE
		return null;
	}

	/**
	 * Retrieves a value associated with some given key in the hash table.
     Returns null if the key could not be found in the hash table)
	 */
	public V get(K key) {

		//  ADD YOUR CODE BELOW HERE
		if(this.containsKey(key))
			return buckets.get(hashFunction(key)).getListNode(key).getValue();

		//  ADD YOUR CODE ABOVE HERE

		return null;
	}

	/**
	 * Removes a key-value pair from the hash table.
	 * Return value associated with the provided key.   If the key is not found, return null.
	 */
	public V remove(K key) {

		//  ADD YOUR CODE BELOW HERE
		if(this.containsKey(key))
			return buckets.get(hashFunction(key)).remove(key).getValue();

		//  ADD  YOUR CODE ABOVE HERE

		return(null);
	}

	/*
	 *  This method is used for testing rehash().  Normally one would not provide such a method.
	 */

	public int getNumBuckets(){
		return numBuckets;
	}

	/*
	 * Returns an iterator for the hash table.
	 */

	public MyHashTable<K, V>.HashIterator  iterator(){
		return new HashIterator();
	}

	/*
	 * Removes all the entries from the hash table,
	 * but keeps the number of buckets intact.
	 */
	public void clear()
	{
		for (int ct = 0; ct < buckets.size(); ct++){
			buckets.get(ct).clear();
		}
		entryCount=0;
	}

	/**
	 *   Create a new hash table that has twice the number of buckets.
	 */


	public void rehash()
	{
		//   ADD YOUR CODE BELOW HERE

		/*
		MyHashTable<K,V> bigger = new MyHashTable<K,V>(this.numBuckets * 2);
		for(int i = 0; i < this.keys().size(); i++)
			bigger.put(this.keys().get(i), this.values().get(i));

		bigger = this;
		*/
		
		HashIterator entries = new HashIterator();
		
		for(int i = 0; i < numBuckets; i++)
			buckets.add(new HashLinkedList<K,V>());
		this.numBuckets *= 2;
		
		this.clear();
		
		HashNode<K,V> entry;
		while(entries.hasNext()) {
			entry = entries.next();
			this.put(entry.getKey(), entry.getValue());
		}
		
		/*
		
		HashLinkedList<K,V> empty = new HashLinkedList<K,V>();
		for(int i = 0; i < numBuckets; i++)
			buckets.add(empty);
		this.numBuckets *= 2;
		*/

		//   ADD YOUR CODE ABOVE HERE

	}


	/*
	 * Checks if the hash table contains the given key.
	 * Return true if the hash table has the specified key, and false otherwise.
	 */

	public boolean containsKey(K key)
	{
		int hashValue = hashFunction(key);
		if(buckets.get(hashValue).getListNode(key) == null){
			return false;
		}
		return true;
	}

	/*
	 * return an ArrayList of the keys in the hashtable
	 */

	public ArrayList<K>  keys()
	{

		ArrayList<K>  listKeys = new ArrayList<K>();

		//   ADD YOUR CODE BELOW HERE
		if(!this.isEmpty()){
			HashNode<K,V> t;
			for(int i = numBuckets - 1; i >= 0 && buckets.get(i) != null; i--){
				t = buckets.get(i).getHead();
				while(t != null){
					listKeys.add(t.getKey());
					t = t.next;
				}
			}
		}

		return listKeys;

		//   ADD YOUR CODE ABOVE HERE

	}

	/*
	 * return an ArrayList of the values in the hashtable
	 */
	public ArrayList <V> values()
	{
		ArrayList<V>  listValues = new ArrayList<V>();

		//   ADD YOUR CODE BELOW HERE
		if(!this.isEmpty()){
			HashNode<K,V> t;
			for(int i = numBuckets - 1; i >= 0 && buckets.get(i) != null; i--){
				t = buckets.get(i).getHead();
				while(t != null){
					listValues.add(t.getValue());
					t = t.next;
				}
			}
		}

		return listValues;

		//   ADD YOUR CODE ABOVE HERE



	}

	@Override
	public String toString() {
		/*
		 * Implemented method. You do not need to modify.
		 */
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buckets.size(); i++) {
			sb.append("Bucket ");
			sb.append(i);
			sb.append(" has ");
			sb.append(buckets.get(i).size());
			sb.append(" entries.\n");
		}
		sb.append("There are ");
		sb.append(entryCount);
		sb.append(" entries in the hash table altogether.");
		return sb.toString();
	}

	/*
	 *    Inner class:   Iterator for the Hash Table.
	 */
	public class HashIterator implements  Iterator<HashNode<K,V> > {
		HashLinkedList<K,V>  allEntries;

		/**
		 * Constructor:   make a linkedlist (HashLinkedList) 'allEntries' of all the entries in the hash table
		 */
		public  HashIterator()
		{

			//  ADD YOUR CODE BELOW HERE
			allEntries = new HashLinkedList<K,V>();
			ArrayList<K> keys = MyHashTable.this.keys();
			ArrayList<V> values = MyHashTable.this.values();
			for(int i = 0; i < keys.size(); i++)
				allEntries.add(keys.get(i), values.get(i));

			//  ADD YOUR CODE ABOVE HERE

		}

		//  Override
		@Override
		public boolean hasNext()
		{
			return !allEntries.isEmpty();
		}

		//  Override
		@Override
		public HashNode<K,V> next()
		{
			return allEntries.removeFirst();
		}

		@Override
		public void remove() {
			// not implemented,  but must be declared because it is in the Iterator interface

		}
	}

}
