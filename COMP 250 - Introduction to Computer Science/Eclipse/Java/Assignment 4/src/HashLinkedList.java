public class HashLinkedList<K,V>{
	/*
	 * Fields
	 */
	private HashNode<K,V> head;

	private Integer size;

	/*
	 * Constructor
	 */

	HashLinkedList(){
		head = null;
		size = 0;
	}


	/*
	 *Add (Hash)node at the front of the linked list
	 */

	public void add(K key, V value){
		// ADD CODE BELOW HERE
		HashNode<K,V> newNode = new HashNode<K,V>(key, value);
		newNode.next = head;
		head = newNode;
		size++;

		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key
	 * returns the node with key
	 */

	public HashNode<K,V> getListNode(K key){
		// ADD CODE BELOW HERE
		HashNode<K,V> t = head;
		while(t != null && !t.getKey().equals(key))
			t = t.next;
		return t;

		// ADD CODE ABOVE HERE
	}


	/*
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 */

	public HashNode<K,V> removeFirst(){
		// ADD CODE BELOW HERE
		HashNode<K,V> removed = head;
		if(!this.isEmpty()){
			head = removed.next;
			removed.next = null;
			size--;
		}
		return removed;

		// ADD CODE ABOVE HERE
	}

	/*
	 * Remove Node by key from linked list
	 */

	public HashNode<K,V> remove(K key){
		// ADD CODE BELOW HERE
		if(this.getListNode(key) != null){
			HashNode<K,V> removed;
			HashNode<K,V> t = head;
			if(t.getKey().equals(key)){
				removed = head;
				head = t.next;
				t.next = null;
			}
			else{
				while(!t.next.getKey().equals(key))
					t = t.next;
				removed = t.next;
				t.next = removed.next;
				removed.next = null;
			}
			size--;
			return removed;
		}

		// ADD CODE ABOVE HERE
		return null; // removing failed
	}



	/*
	 * Delete the whole linked list
	 */
	public void clear(){
		head = null;
		size = 0;
	}
	/*
	 * Check if the list is empty
	 */

	boolean isEmpty(){
		return size == 0? true:false;
	}

	int size(){
		return this.size;
	}

	//ADD YOUR HELPER  METHODS BELOW THIS
	public HashNode<K,V> getHead(){
		return head;
	}

	//ADD YOUR HELPER METHODS ABOVE THIS


}
