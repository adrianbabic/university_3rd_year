package hr.fer.oprpp1.custom.collections;


/** This interface is used for the assurance that specific classes 
 * 	implement methods which are mentioned within this interface.
 * 
 * 	@author adrian
 *
 */
public interface Collection<E> {
	
	
	/** This default method offers default implementation if a certain
	 * 	derived class does not need to override this method.
	 * 	Used to determine wether this collection is empty.
	 * 
	 * 	@returns true if {@link #size() Size} method returns 0, in every
	 * 	other case returns false
	 *  
	 */
	default boolean isEmpty() {
		if(this.size() == 0) return true;
		return false;
	}
	
	
	/** This default method offers default implementation if a certain
	 * 	derived class does not need to override this method.
	 * 	It is used for adding all elements of another collection to this
	 * 	one.
	 * 
	 * 	@param another Collection whose elements will be copied to this one
	 *  
	 */
	default void addAll(Collection<? extends E> other) {
		class LocalProcessor implements Processor<E>{
			
			
			@Override
			public void process(E value) {
				add(value);
			}
		}
		
		LocalProcessor local = new LocalProcessor();
		other.forEach(local);
	}
	
	
	/** This method is used to determine size of a collection.
	 * 
	 * 	@returns integer value which represents size of a collection
	 *  
	 */
	int size();
	
	
	/** Used to add a single Object to a collection.
	 * 
	 * 	@param reference to an Object that needs to be added
	 * 	to the collection
	 *  
	 */
	void add(E value);
	
	
	/** Used to determine wether a collection contains specific element
	 * 
	 * 	@param reference to an Object that is being searched for in
	 * 	a collection
	 * 	
	 * 	@returns information in form of boolean expression. Should be true
	 * 	if a collection contains given element, otherwise false
	 *  
	 */
	boolean contains(Object value);
	
	
	/** Used to remove specific element from a collection
	 * 
	 * 	@param reference to an Object that should be removed
	 * 	
	 * 	@returns information in form of boolean expression. Should be true
	 * 	if operation was success, otherwise false.
	 *  
	 */
	boolean remove(Object value);
	
	
	/** Used for generating a field of elements which are in collection
	 * 	
	 * 	@returns field of Objects
	 *  
	 */
	Object[] toArray();
	
	
	/** Used for activating a certain action for every object in a collection
	 * 
	 * 	@param Processor class which will activate that certain action
	 * 	  
	 */
	default void forEach(Processor<? super E> processor) {
		ElementsGetter<E> collectionElements = this.createElementsGetter();
		while(collectionElements.hasNextElement()) {
			processor.process(collectionElements.getNextElement());
		}
	}
	
	
	/** Used to empty a collection.
	 * 	Warning: could be deleting elements from a collection.
	 *  
	 */
	void clear();
	
	default void addAllSatisfying(Collection<? extends E> col, Tester<? super E> tester) {
		ElementsGetter<? extends E> collectionElements = col.createElementsGetter();
		E currentObject;
		while(collectionElements.hasNextElement()) {
			currentObject = collectionElements.getNextElement();
			if(tester.test(currentObject)){
				this.add(currentObject);
			}
		}
	}
	
	
	public ElementsGetter<E> createElementsGetter();
	
	public interface ElementsGetter<E> {
		
		public E getNextElement();
		
		public boolean hasNextElement();
		
		public default void processRemaining(Processor<E> p) {
			while(this.hasNextElement()) {
				p.process(this.getNextElement());
			}
		}
	}
	
	
}
