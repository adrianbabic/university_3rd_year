package hr.fer.oprpp1.custom.collections;

/**	This class represents an array collection which uses index to navigate
 * 	through it's elements. It operates with class Object. Elements can be added,
 * 	inserted and removed.
 *  
 * 	@author adrian
 *
 */
public class ArrayIndexedCollection extends Collection {
	private int size;
	private Object[] elements;

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
	public ArrayIndexedCollection(Collection other, int initialCapacity) {
		if(other == null) throw new NullPointerException();
		if(initialCapacity < other.size()) {
			this.elements = new Object[other.size()];
		} else {
			this.elements = new Object[initialCapacity];
		}
		this.size = 0;
		this.addAll(other);
	}
	
	/** Constructor which copies both elements and size of the given Collection  
	 * 	to a brand new instance of this class.
	 *	
	 *	@param instance of already existing Collection
	 */
	
	public ArrayIndexedCollection(Collection other) {
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
		if(this.size == 0) return super.toArray();
		Object[] result = new Object[this.size];
		for(int i = 0; i < this.size; i++) {
			result[i] = this.elements[i];
		}
		return result;
	}
	
	/** Method is calling another method for every element that is 
	 * 	currently in the array
	 * 	
	 * 	@param class Processor which is going to process every element
	 *
	 */
	@Override
	public void forEach(Processor processor) {
		for(Object one: this.elements) {
			if(one != null) processor.process(one);
		}
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
	public void add(Object value) {
		if(value == null) throw new NullPointerException();
		if(size >= this.elements.length) {
			ArrayIndexedCollection doubled = new ArrayIndexedCollection(this, size * 2);
			this.elements = doubled.elements;
		}
		for(int i = 0; i < this.elements.length; i++) {
			if(this.elements[i] == null) {
				this.elements[i] = value;
				this.size++;
				break;
			}
		}
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
	public Object get(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		return this.elements[index];
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
	public void insert(Object value, int position) {
		if(position < 0 || position > this.size) throw new IndexOutOfBoundsException();
		Object elementOnLastPosition = this.elements[this.elements.length - 1];
		for(int i = this.size - 1; i > position; i--) {
			this.elements[i] = this.elements[i - 1];
		}
		this.elements[position] = value;
		this.size++;
		if(elementOnLastPosition != null) this.add(elementOnLastPosition);
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
		this.elements[this.size] = null;
	}
	


}
