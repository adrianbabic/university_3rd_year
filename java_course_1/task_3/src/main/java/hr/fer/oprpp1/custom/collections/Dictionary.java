package hr.fer.oprpp1.custom.collections;

import hr.fer.oprpp1.custom.collections.Collection.ElementsGetter;

/**	Class is implemented as an Adapter.
 * 	Class is used as a dictionary, putting pairs of keys and values together in an array.	
 *  
 * 	@author adrian
 */
public class Dictionary<K, V> {
	/** An array which is used for storing Pair instances. **/
	private ArrayIndexedCollection<Pair> array;
	
	/** Default constructor which initializes an array when an instance of this class is created.	
	 *
	 */
	public Dictionary() {
		this.array = new ArrayIndexedCollection<>();
	}
	
	/** Method checks are there any elements currently in the dictionary.
	 * 	
	 * 	@return true if there are no elements in the dictionary
	 * 	@return false if there are some elements in the dictionary
	 */
	public boolean isEmpty() {
		return this.array.isEmpty();
	}
	
	/** Method returns the current number of elements in the dictionary.
	 * 	
	 */
	public int size() {
		return this.array.size();
	}
	
	/** Method deletes all the elements from the dictionary.
	 * 	
	 */
	public void clear() {
		this.array.clear();
	}
	
	/** Method for inputing a Pair in the dictionary.
	 * 	
	 * 	@param K key which is used for storing the key of a Pair
	 * 	@param V value which is used for storing the value of a Pair
	 * 	@throws IllegalArgumentException if the given key is null
	 * 	@return null if the given K key was not present in the dictionary
	 * 	@return V value of a Pair which was an old value now replaced by the new V value if the given key
	 * 		was present in the dictionary paired with some value
	 */
	public V put(K key, V value) {
		Pair pair = getPair(key);
		V oldValue = null;
		if(pair == null)
			array.add(new Pair(key, value));
		else {
			oldValue = pair.getValue();
			pair.setValue(value);
		}
		return oldValue;
	}
	
	/** Method returns value of a Pair which is paired with the given key.
	 * 	
	 * 	@param K key whose value is being searched for
	 * 	@return null if the given K key was not present in the dictionary
	 * 	@return V value of a Pair which has the given key as a key
	 */
	public V get(Object key) {
		ElementsGetter<Pair> elements = this.array.createElementsGetter();
		Pair tmp;
		while(elements.hasNextElement()) {
			tmp = elements.getNextElement();
			if(key.equals(tmp.getKey())) {
				return tmp.getValue();
			}
		}
		return null;
	}
	
	/** Method for removing the Pair from the dictionary if the key is present in the dictionary.
	 * 	
	 * 	@param K key which is used for searching the Pair 
	 * 	@return null if the given K key was not present in the dictionary
	 * 	@return V value of a Pair which had the given key as a key
	 */
	public V remove(K key) {
		ElementsGetter<Pair> elements = this.array.createElementsGetter();
		Pair tmp;
		while(elements.hasNextElement()) {
			tmp = elements.getNextElement();
			if(key.equals(tmp.getKey())) {
				this.array.remove(tmp);
				return tmp.getValue();
			}
		}
		return null;
	}
	
	private Pair getPair(Object key) {
		ElementsGetter<Pair> elements = this.array.createElementsGetter();
		Pair tmp;
		while(elements.hasNextElement()) {
			tmp = elements.getNextElement();
			if(key.equals(tmp.getKey())) {
				return tmp;
			}
		}
		return null;
	}
	
	/**	Private nested class used for storing two parameters, key and value.
	 * 
	 */
	private class Pair {
		private K key;
		private V value;
		
		public Pair(K key, V value) {
			if(key == null) throw new IllegalArgumentException("Key must not be null!");
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
		
		
	}

}
