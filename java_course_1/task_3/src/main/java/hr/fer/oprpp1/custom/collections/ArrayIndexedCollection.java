package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**	This class represents an array collection which uses index to navigate
 * 	through it's elements. It operates with class Object. Elements can be added,
 * 	inserted and removed.
 *  
 * 	@author adrian
 *
 */
public class ArrayIndexedCollection<E> implements List<E> {
	private int size;
	private Object[] elements;
	private long modificationCount;

	/** Constructor for creating an instance of a class.
	 * 	
	 * 	@param int which represents the maximum size of an array
	 * 	@throws IllegalArgumentException if given size is less than 1
	 *
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		if(initialCapacity < 1) throw new IllegalArgumentException();
		this.size = 0;
		this.elements = new Object[initialCapacity];
		this.modificationCount = 0L;
	}
	
	/** Default constructor which uses delegated constructor. 
	 * 	
	 *
	 */
	public ArrayIndexedCollection() {
		this(16);
	}
	
	/** Constructor that copies elements of the given instance of Collection into 
	 * 	brand new instance of this class with given maximum size of an array.
	 * 
	 * 	@params instance of class Collection and integer that represents maximum capacity
	 * 	@throws NullPointerException is given reference to Collection is <code>null</code>
	 *
	 */
	public ArrayIndexedCollection(Collection<? extends E> other, int initialCapacity) {
		if(other == null) throw new NullPointerException();
		if(initialCapacity < other.size()) {
			this.elements = new Object[other.size()];
		} else {
			this.elements = new Object[initialCapacity];
		}
		this.size = 0;
		this.modificationCount = 0L;
		this.addAll(other);
	}
	
	/** Constructor which copies both elements and size of the given Collection  
	 * 	to a brand new instance of this class.
	 *	
	 *	@param instance of already existing Collection
	 */
	
	public ArrayIndexedCollection(Collection<? extends E> other) {
		this(other, other.size());
	}

	/** Size method
	 * 	
	 * 	@returns current number of elements in the array
	 *
	 */
	@Override
	public int size() {
		return size;
	}

	/** Method is giving an information is a certain Object part of this array
	 * 	
	 * 	@param Object which is sought after
	 * 	@returns true if the array contains given Object, else false
	 *
	 */
	@Override
	public boolean contains(Object value) {
		for(Object one: this.elements) {
			if(value.equals(one)) return true;
		}
		return false;
	}
	
	/** Method is returning an Object field of current elements in the array
	 * 	
	 * 	@returns Objects fields if there are any in the array at the time
	 * 	of calling the method
	 *
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[this.elements.length];
		for(int i = 0; i < this.elements.length; i++) {
			result[i] = this.elements[i];
		}
		return result;
	}

	/** Method is removing the given Object if the Object is in the array
	 * 	
	 * 	@param Object which is sought after
	 * 	@returns true if the array contains given Object and deletes it, else false
	 *
	 */
	@Override
	public boolean remove(Object value) {
		if(this.contains(value)) {
			for(int i = 0; i < this.size; i++) {
				if(this.elements[i].equals(value)) {
					remove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/** Adding a certain Object in the array
	 * 	
	 * 	@param Object which is looking to be inserted in the array
	 * 	@throws NullPointerException if the given reference to Object 
	 * 	is <code>null</code>
	 *
	 */
	@Override
	public void add(E value) {
		if(value == null) throw new NullPointerException();
		if(size >= this.elements.length) {
			ArrayIndexedCollection<E> doubled = new ArrayIndexedCollection<>(this, size * 2);
			this.elements = doubled.elements;
		}
		for(int i = 0; i < this.elements.length; i++) {
			if(this.elements[i] == null) {
				this.elements[i] = value;
				this.size++;
				break;
			}
		}
		this.modificationCount++;
	}
	
	/** Method is getting an Object at a certain position within
	 * 	the array
	 * 	
	 * 	@param integer that represents index of an Object that is needed
	 * 	@returns Object that is sought after
	 *  @throws IndexOutOfBoundsException if the given index is not between
	 * 	zero and the highest index currently in the array
	 *
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		return (E)this.elements[index];
	}
	
	/** Method deletes all the elements from the array and resets it's size to zero
	 * 	
	 *
	 */
	@Override
	public void clear() {
		this.size = 0;
		for(int i = 0; i < this.elements.length; i++) {
			if(this.elements[i] != null) this.elements[i] = null;
		}
		this.modificationCount++;
	}
	
	/** Method inserts the given Object to a given position without
	 * 	losing any other elements of the array
	 * 	
	 * 	@param Object that is being inserted and integer of an index where
	 * 	Object needs to be placed
	 *  @throws IndexOutOfBoundsException if the given index is not between
	 * 	zero and the number of elements currently in the array
	 *
	 */
	@SuppressWarnings("unchecked")
	public void insert(E value, int position) {
		if(position < 0 || position > this.size) throw new IndexOutOfBoundsException();
		E elementOnLastPosition = (E)this.elements[this.elements.length - 1];
		for(int i = this.size - 1; i > position; i--) {
			this.elements[i] = this.elements[i - 1];
		}
		this.elements[position] = value;
		if(elementOnLastPosition != null) {
			this.add(elementOnLastPosition);
		} else {
			this.modificationCount++;
			this.size++;
		}
	}
	
	/** Method is getting a position of the given Object
	 * 	
	 * 	@param Object which is sought after
	 * 	@returns index of the given Object if it is found
	 * 	within the array, else -1
	 * 
	 *
	 */
	public int indexOf(Object value) {
		for(int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(value)) return i;
		}
		return -1;
	}
	
	/** Method is removing the elements from the given position
	 * 	
	 * 	@param integer that represents index of an Object that is being
	 * 	removed
	 * 
	 *  @throws IndexOutOfBoundsException if the given index is not between
	 * 	zero and the highest index currently in the array
	 *
	 */
	public void remove(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		for(int i = index; i < this.size - 1; i++) {
			this.elements[i] = this.elements[i + 1];
		}
		this.size--;
		this.modificationCount++;
		this.elements[this.size] = null;
	}

	@Override
	public ElementsGetter<E> createElementsGetter() {
		return new ArrayElementsGetter<>(this);
	}
	
	private static class ArrayElementsGetter<E> implements ElementsGetter<E> {
		private ArrayIndexedCollection<E> arrayInstance;
		private int currentIndex;
		private long savedModificationCount;
		
		public ArrayElementsGetter(ArrayIndexedCollection<E> arrayInstance) {
			this.arrayInstance = arrayInstance;
			this.currentIndex = 0;
			this.savedModificationCount = arrayInstance.modificationCount;
		}
		@SuppressWarnings("unchecked")
		@Override
		public E getNextElement() {
			if(this.savedModificationCount != this.arrayInstance.modificationCount) throw new ConcurrentModificationException();
			if(this.currentIndex >= this.arrayInstance.size) throw new NoSuchElementException("There is no elements left!");
			return (E)this.arrayInstance.elements[this.currentIndex++];
			
		}

		@Override
		public boolean hasNextElement() {
			if(this.savedModificationCount != this.arrayInstance.modificationCount) throw new ConcurrentModificationException();
			if(this.currentIndex <= this.arrayInstance.size() - 1) return true;
			return false;
		}
		
	}


}
