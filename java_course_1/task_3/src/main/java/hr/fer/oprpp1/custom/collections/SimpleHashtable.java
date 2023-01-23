package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**	Class is implemented as an Adapter.
 * 	Class is used as an interpretation of hashed map for storing values of pairs.	
 *  
 * 	@author adrian
 *
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {
	/** TableEntry array for storing pair values. **/
	private TableEntry<K, V>[] table;
	/** Current number of pairs in the table. **/
	private int size;
	/** Integer value for counting modifications made to the instance of table. **/
	private int modificationCount;
	
	/** Constructor that initializes the capacity of the array. Capacity set will always be
	 * 	the number to the power of 2 which is equal or higher than the given integer.	
	 *
	 *	@param int for the wanted capacity of the array
	 *	@throws if the given argument is less than 1
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {
		if(capacity < 1) throw new IllegalArgumentException("Wanted capacity should be 1 or higher!");
		int trueCapacity;
		for(trueCapacity = 1; trueCapacity < capacity; trueCapacity = trueCapacity * 2);
		this.table = (TableEntry<K, V>[]) new TableEntry[trueCapacity];
		this.size = 0;
		this.modificationCount = 0;
	}

	/** Default constructor which sets the capacity of the storing array to 16.	
	 *
	 */
	public SimpleHashtable() {
		this(16);
	}
	
	/** Method for inputing a TableEntry in the table. If occupancy breaches 75% of the table, table capacity will 
	 * 	be doubled.
	 * 	
	 * 	@param K key which is used for storing the key of a TableEntry
	 * 	@param V value which is used for storing the value of a TableEntry
	 * 	@throws NullPointerException if the given key is null
	 * 	@return null if the given K key was not already present in the table
	 * 	@return old value of a TableEntry which now replaced by the new V value, if the given key
	 * 		was already present in the table 
	 * 	
	 */
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		if(key == null) throw new NullPointerException("Argument key must not be null!");
		TableEntry<K, V> found = findByKey(key);
		if(found != null) {
			V oldValue = found.getValue();
			found.setValue(value);
			return oldValue;
		} else {
			if((double)size / table.length >= 0.75) {
				TableEntry<K, V>[] oldElements = this.toArray();
				this.clear();
				this.table = (TableEntry<K, V>[]) new TableEntry[table.length * 2];
				for(TableEntry<K, V> one: oldElements) {
					this.addElement(one.getKey(), one.getValue());
				}
			}
			this.modificationCount++;
			this.addElement(key, value);
			return null;
		}
	}
	
	/** Method returns value of a TableEntry which is paired with the given key.
	 * 	
	 * 	@param K key whose value is being searched for
	 * 	@return null if the given K key was not present in the table or ih the value paired with key was null
	 * 	@return V value of a Pair which is paired to the given key 
	 */
	public V get(Object key) {
		TableEntry<K,V> result = findByKey(key);
		if(result != null) return result.getValue();
		else return null;
	}
	
	/** Method returns number of TableEntry instances in the table.
	 * 	
	 * 	@return int which represents the number of instances in the table
	 */
	public int size() {
		return size;
	}
	
	/** Determines if the given key is present in the table.
	 * 	
	 * 	@param key of type Object which is being searched for
	 * 	@return true if the given key is present in the table
	 * 	@return false if the given key is not present in the table
	 */
	public boolean containsKey(Object key) {
		if(findByKey(key) == null) return false;
		else return true;
	}
	
	/** Determines if the given value is present in the table.
	 * 	
	 * 	@param value of type Object which is being searched for
	 * 	@return true if the given value is present in the table
	 * 	@return false if the given value is not present in the table
	 */
	public boolean containsValue(Object value) {
		TableEntry<K, V> current;
		for(int i = 0; i < table.length; i++) {
			current = table[i];
			while(current != null) {
				if(current.getValue().equals(value)) return true;
				current = current.next;
			}
		}
		return false;
	}
	
	/** Method for removing the TableEntry from the table if the given key is present in the dictionary.
	 * 	
	 * 	@param K key which is used for searching the TableEntry 
	 * 	@return null if the given K key was not present in the table
	 * 	@return V value of a TableEntry which was paired with the given key 
	 */
	public V remove(Object key) {
		if(key == null) return null;
		int slot = Math.abs(key.hashCode()) % table.length;
		TableEntry<K, V> previous = null;
		TableEntry<K, V> current = table[slot];
		while(current != null) {
			if(current.getKey().equals(key)) {
				if(previous != null) 
					previous.next = current.next;
				else 
					table[slot] = current.next;
				this.size--;
				this.modificationCount++;
				return current.getValue();
			}
			previous = current;
			current = current.next;
		}
		return null;
	}
	
	/** Method checks are there any elements currently in the table.
	 * 	
	 * 	@return false if there are no elements in the table
	 * 	@return true if there are one or more elements in the table
	 */
	public boolean isEmpty() {
		if(size == 0) return true;
		else return false;
	}
	
	/** Method provides a readable representation of the table.
	 * 	
	 * 	@return String representation of the table 
	 * 	Example: "[Ivana=5, Marija=4, Ivan=5]"
	 */
	public String toString() {
		if(this.isEmpty()) return "[]";
		
		TableEntry<K, V>[] array = this.toArray();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(TableEntry<K, V> one: array) {
			sb.append(one.getKey().toString() + "=" + one.getValue().toString() + ", ");
		}
		int deleteIndex = sb.length();
		sb.delete(deleteIndex - 2, deleteIndex);
		sb.append("]");
		return sb.toString();
	}
	
	/** Method provides an array of all the TableEntry instances currently in the table.
	 * 	
	 * 	@return TableEntry array 
	 */
	@SuppressWarnings("unchecked")
	public TableEntry<K, V>[] toArray(){
		TableEntry<K, V>[] array = (TableEntry<K, V>[]) new TableEntry[size];
		TableEntry<K, V> current;
		int currentIndex = 0;
		for(int i = 0; i < table.length; i++) {
			current = table[i];
			while(current != null) {
				array[currentIndex++] = current;
				current = current.next;
			}
		}
		return array;
	}
	
	/** Getter for the current capacity of the table. Used for testing purposes. 
	 * 	
	 * 	@return int which represents the capacity
	 */
	public int getCapacity() {
		return table.length;
	}
	
	/** Method removes all the instances from the table.
	 * 	
	 */
	public void clear() {
		TableEntry<K, V>[] table = this.toArray();
		for(TableEntry<K, V> one: table) {
			this.remove(one.getKey());
			this.modificationCount--;
		}
	}
	
	/** Overridden method used for iterating through the table.
	 * 	
	 */
	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}
	
	/** Private class which is used for iterating through the table. Iterating is only possible if
	 * 	the table is not being changed while iterating.
	 * 	
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {
		/** Stores the current modificationCount of the table. **/
		private int storedCount;
		/** Counts the number of times the next() method was called. **/
		private int passedEntries;
		/** Stores the value of the current slot during the iteration. **/
		private int currentIndex;
		/** Stores recently used TableEntry instance.  **/
		private TableEntry<K, V> current;
		/** Stores information if the remove() method was called before the next() method was called. **/
		private boolean alreadyRemoved;
		/** Stores the size of table. **/
		private int storedSize;
		
		/** Default constructor which initializes all the required variables and collects needed information
		 * 	from the table in the current state.	
		 *
		 */
		public IteratorImpl() {
			this.storedCount = modificationCount;
			this.passedEntries = 0;
			this.currentIndex = 0;
			this.current = null;
			this.alreadyRemoved = false;
			this.storedSize = size();
		}
		
		/** Returns true if the iteration has more elements.
		 * 	
		 * 	@throws ConcurrentModificationException if the table was changed during the iteration
		 * 	@return true if there are more elements
		 * 	@return false if there are no more elements 	
		 *
		 */
		@Override
		public boolean hasNext() {
			if(this.storedCount != modificationCount) throw new ConcurrentModificationException("Table was changed externally while iterating!");
			if(passedEntries < storedSize && !isEmpty()) 
				return true;
			return false;
		}
		
		/** Returns the next element in the iteration.
		 * 	
		 * 	@throws NoSuchElementException if there are no more elements in the iteration
		 * 	@return TableEntry instance which is next in the iteration
		 */
		@Override
		public TableEntry<K, V> next() {
			if(!this.hasNext()) throw new NoSuchElementException("There are no more elements left!");
			this.alreadyRemoved = false;
			if(current == null) {
				while(current == null) {
					current = table[currentIndex++];
				}
				passedEntries++;
				return current;
			} else {
				current = current.next;
				while(current == null) {
					current = table[currentIndex++];
				}
				this.passedEntries++;
				return current;
			}
		}
		/** Returns the last TableEntry returned by this iterator from the table
		 * 	
		 * 	@throws ConcurrentModificationException if the table was changed during the iteration
		 *	@throws IllegalStateException if the method was called before the required call of @next() method
		 */
		@Override
		public void remove() {
			if(this.storedCount != modificationCount) throw new ConcurrentModificationException("Table was changed externally while iterating!");
			if(passedEntries == 0 || alreadyRemoved) throw new IllegalStateException("Can not call remove method before calling next() first!");
			SimpleHashtable.this.remove(current.getKey());
			this.storedCount++;
			this.alreadyRemoved = true;
		}
		
		
		
	}
	
	/** Private class used for storing pairs of keys and values. Class also contains the information
	 * 	of the next instance in the table slot, in case the table is overflown.
	 */
	public static class TableEntry<K, V> {
		private K key;
		private V value;
		private TableEntry<K, V> next;
		
		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public K getKey() {
			return key;
		}
	}
	
	/** Looks for the TableEntry instance which contains the given key.
	 * 	
	 * 	@param key of type Object which is being searched for
	 * 	@throws NullPointerException is the given key is null
	 * 	@return instance which contains the given key
	 * 	@return null if there is no instance which such key in the table	
	 *
	 */
	private TableEntry<K, V> findByKey(Object key){
		if(key == null) throw new NullPointerException("Argument key must not be null!");
		int slot = Math.abs(key.hashCode()) % table.length;
		TableEntry<K, V> current = table[slot];
		while(current != null) {
			if(current.getKey().equals(key)) return current;
			current = current.next;
		}
		return null;
	}
	
	/** Puts the TableEntry instance in the appropriate place in the table.
	 * 	
	 * 	@param K value for the key of new TableEntry	
	 *	@param V value for the value of new TableEntry
	 */
	private void addElement(K key, V value) {
		int slot = Math.abs(key.hashCode()) % table.length;
		TableEntry<K, V> previous = null;
		TableEntry<K, V> current = table[slot];
		while(current != null) {
			previous = current;
			current = current.next;
		}
		if(previous != null) 
			previous.next = new TableEntry<>(key, value);
		else 
			table[slot] = new TableEntry<>(key, value);
		this.size++;
	}
}
