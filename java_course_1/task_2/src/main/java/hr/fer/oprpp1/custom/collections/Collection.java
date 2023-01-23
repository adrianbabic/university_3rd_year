package hr.fer.oprpp1.custom.collections;


/** This interface is used for the assurance that specific classes 
 * 	implement methods which are mentioned within this interface.
 * 
 * 	@author adrian
 *
 */
public interface Collection {
	
	
	/** This default method offers default implementation if a certain
	 * 	derived class does not need to override this method.
	 * 	Used to determine wether this collection is empty.
	 * 
	 * 	@returns true if {@link #size() Size} method returns 0, in every
	 * 	other case returns false
	 *  
	 */
	public default boolean isEmpty() {
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
	public default void addAll(Collection other) {
		class LocalProcessor implements Processor{
			
			
			@Override
			public void process(Object value) {
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
	public void add(Object value);
	
	
	/** Used to determine wether a collection contains specific element
	 * 
	 * 	@param reference to an Object that is being searched for in
	 * 	a collection
	 * 	
	 * 	@returns information in form of boolean expression. Should be true
	 * 	if a collection contains given element, otherwise false
	 *  
	 */
	public boolean contains(Object value);
	
	
	/** Used to remove specific element from a collection
	 * 
	 * 	@param reference to an Object that should be removed
	 * 	
	 * 	@returns information in form of boolean expression. Should be true
	 * 	if operation was success, otherwise false.
	 *  
	 */
	public boolean remove(Object value);
	
	
	/** Used for generating a field of elements which are in collection
	 * 	
	 * 	@returns field of Objects
	 *  
	 */
	public Object[] toArray();
	
	
	/** Used for activating a certain action for every object in a collection
	 * 
	 * 	@param Processor class which will activate that certain action
	 * 	  
	 */
	public default void forEach(Processor processor) {
		ElementsGetter collectionElements = this.createElementsGetter();
		while(collectionElements.hasNextElement()) {
			processor.process(collectionElements.getNextElement());
		}
	}
	
	
	/** Used to empty a collection.
	 * 	Warning: could be deleting elements from a collection.
	 *  
	 */
	public void clear();
	
	public default void addAllSatisfying(Collection col, Tester tester) {
		ElementsGetter collectionElements = col.createElementsGetter();
		Object currentObject;
		while(collectionElements.hasNextElement()) {
			currentObject = collectionElements.getNextElement();
			if(tester.test(currentObject)){
				this.add(currentObject);
			}
		}
	}
	
	
	public ElementsGetter createElementsGetter();
	
	public interface ElementsGetter {
		
		public Object getNextElement();
		
		public boolean hasNextElement();
		
		public default void processRemaining(Processor p) {
			while(this.hasNextElement()) {
				p.process(this.getNextElement());
			}
		}
	}
	
	
}
