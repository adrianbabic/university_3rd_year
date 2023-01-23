package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;


/**	This class represents a linked list collection which uses index to navigate
 * 	through it's elements. It operates with class Object. Elements can be added,
 * 	inserted and removed.
 *  
 * 	@author adrian
 *
 */
public class LinkedListIndexedCollection<E> implements List<E> {
	
	/**	Private class used for easier manipulating through the list.
	 * 	An instance of a class has an element that's currently behind
	 * 	the given instance and also an element that's in front of given instance.
	 * 	It also contains the stored value.
	 *  
	 *
	 */
	private static class ListNode {
		private ListNode previous;
		private ListNode next;
		private Object value;
		
		public ListNode(ListNode previous, ListNode next, Object value) {
			this.previous = previous;
			this.next = next;
			this.value = value;
			
		}
		
	}
	
	private int size;
	private ListNode first;
	private ListNode last;
	private long modificationCount;
	
	/** Default constructor which sets initial values of an empty list. 
	 * 	
	 *
	 */
	public LinkedListIndexedCollection() {
		this.first = null;
		this.last = null;
		this.size = 0;
		this.modificationCount = 0L;
	}
	
	/** Constructor which copies both elements and size of the given Collection  
	 * 	to a brand new instance of this class.
	 *	
	 *	@param instance of already existing Collection
	 */
	
	public LinkedListIndexedCollection(Collection<? extends E> other) {
		this();
		this.addAll(other);
	}
	
	/** Size method
	 * 	
	 * 	@returns current number of elements in the list
	 *
	 */
	@Override
	public int size() {
		return this.size;
	}

	/** Method is giving an information is a certain Object part of this list
	 * 	
	 * 	@param Object which is sought after
	 * 	@returns true if the array contains given Object, else false
	 *
	 */
	@Override
	public boolean contains(Object value) {
		ListNode tmp = first;
		if(first == null) return false;
		for(int i = 1; i < this.size; i++) {
			if(tmp.value.equals(value)) return true;
			tmp = tmp.next;
		}
		return false;
	}

	/** Method is removing the given Object if the Object is in the list
	 * 	
	 * 	@param Object which is sought after
	 * 	@returns true if the list contains given Object and deletes it, else false
	 *
	 */
	@Override
	public boolean remove(Object value) {
		if(this.contains(value)) {
			ListNode tmp = first;
			for(int i = 0; i < this.size; i++) {
				if(tmp.value.equals(value)) {
					remove(i);
					return true;
				}
				tmp = tmp.next;
			}
		}
		
		return false;
	}

	/** Method is returning an Object field of current elements in the list
	 * 	
	 * 	@returns Objects fields if there are any in the list at the time
	 * 	of calling the method
	 *
	 */
	@Override
	public Object[] toArray() {
		if(this.size == 0) throw new UnsupportedOperationException();
		Object[] result = new Object[this.size];
		ListNode current = first;
		for(int i = 0; i < this.size; i++) {
			result[i] = current.value;
			current = current.next;
		}
		return result;
	}


	/** Adding a certain Object in the list
	 * 	
	 * 	@param Object which is looking to be inserted in the list
	 * 	@throws NullPointerException if the given reference to Object 
	 * 	is <code>null</code>
	 *
	 */
	@Override
	public void add(E value) {
		if(value == null) throw new NullPointerException();
		if(this.size == 0) {
			ListNode created = new ListNode(null, null, value);
			this.first = created;
			this.last = created;
			this.size = 1;
		} else {
			ListNode created = new ListNode(this.last, null, value);
			this.last.next = created;
			this.last = created;
			this.size++;
		}
		this.modificationCount++;
		
	}
	
	/** Method is getting an Object at a certain position within
	 * 	the list
	 * 	
	 * 	@param integer that represents index of an Object that is needed
	 * 	@returns Object that is sought after
	 *  @throws IndexOutOfBoundsException if the given index is not between
	 * 	zero and the highest index currently in the list
	 *
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if(index < 0 || index > this.size - 1) throw new IndexOutOfBoundsException();
		return (E)this.atIndex(index).value;
	}
	
	/** Method that is being used to help determine whether it is better
	 * 	to go get the reference to a certain ListNode instance from the 
	 * 	beggining of the list or from the back of the list. Resulting in 
	 * 	faster return.
	 * 	
	 * 	@param integer that represents index of an Object that is needed
	 * 	@returns ListNode instance that is sought after
	 *
	 */
	
	public ListNode atIndex(int index) {
		ListNode tmp;
		if(index <= this.size - index ) {
			tmp = this.first;
			for(int i = 0; i < index; i++) {
				tmp = tmp.next;
			}
		} else {
			tmp = this.last;
			for(int i = this.size - 1; i > index; i--) {
				tmp = tmp.previous;
			}
		}
		return tmp;
	}
	
	/** Method that resets the list to being empty. It is important to
	 * 	know that the method does not delete elements from memory.
	 * 	
	 *
	 */
	@Override
	public void clear() {
		this.first = null;
		this.last = null;
		this.size = 0;
		this.modificationCount++;
	}
	
	
	/** Method inserts the given Object to a given position without
	 * 	losing any other elements of the list
	 * 	
	 * 	@param Object that is being inserted and integer of an index where
	 * 	Object needs to be placed
	 *  @throws IndexOutOfBoundsException if the given index is not between
	 * 	zero and the number of elements currently in the list
	 *
	 */
	public void insert(E value, int position) {
		if(position < 0 || position > this.size - 1) throw new IndexOutOfBoundsException();
		ListNode onThatPosition = this.atIndex(position);
		ListNode oldPrevious = onThatPosition.previous;
		ListNode inserted = new ListNode(oldPrevious, onThatPosition, value);
		oldPrevious.next = inserted;
		onThatPosition.previous = inserted;
		this.size++;
		this.modificationCount++;
	}
	
	
	/** Method is getting a position of the given Object
	 * 	
	 * 	@param Object which is sought after
	 * 	@returns index of the given Object if it is found
	 * 	within the list, else -1
	 * 
	 *
	 */
	public int indexOf(Object value) {
		ListNode tmp = first;
		for(int i = 0; i < this.size - 1; i++) {
			if(tmp.value.equals(value)) return i;
			tmp = tmp.next;
		}
		return -1;
	}
	
	/** Method is removing the elements from the given position
	 * 	
	 * 	@param integer that represents index of an Object that is being
	 * 	removed
	 * 
	 *  @throws IndexOutOfBoundsException if the given index is not between
	 * 	zero and the number of elements currently in the list
	 *
	 */
	public void remove (int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		ListNode toBeRemoved = this.atIndex(index);
		ListNode previousOne = toBeRemoved.previous;
		ListNode nextOne = toBeRemoved.next;
		previousOne.next = nextOne;
		nextOne.previous = previousOne;
		toBeRemoved.next = null;
		toBeRemoved.previous = null;
		toBeRemoved.value = null;
		this.size--;
		this.modificationCount++;
	}

	@Override
	public ElementsGetter<E> createElementsGetter() {
		return new LinkedListElementsGetter<>(this);
	}
	
	private static class LinkedListElementsGetter<E> implements ElementsGetter<E> {
		private ListNode currentNode;
		private long savedModificationCount;
		private LinkedListIndexedCollection<E> listInstance;
		
		public LinkedListElementsGetter(LinkedListIndexedCollection<E> listInstance) {
			this.currentNode = listInstance.first;
			this.savedModificationCount = listInstance.modificationCount;
			this.listInstance = listInstance;
		}
		@Override
		public E getNextElement() {
			if(this.savedModificationCount != this.listInstance.modificationCount) throw new ConcurrentModificationException();
			if(this.currentNode == null) throw new NoSuchElementException("There is no elements left!");
			@SuppressWarnings("unchecked")
			E toReturn = (E)currentNode.value;
			this.currentNode = this.currentNode.next;
			return toReturn;
			
		}

		@Override
		public boolean hasNextElement() {
			if(this.savedModificationCount != this.listInstance.modificationCount) throw new ConcurrentModificationException();
			if(this.currentNode != null) return true;
			return false;
		}
		
	}
	
	
}
