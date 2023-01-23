package hr.fer.oprpp1.custom.scripting.parser.collections;

/**	Class represents Adapter pattern which can be useful.
 * 	Class implements almost all the same methods as the ArrayIndexedCollection
 * 	and uses that class to create a better pattern for it's users.
 * 	Here stack is being used for different purposes. Size of the
 * 	stack has to be changed manually.	
 *  
 * 	@author adrian
 *
 */
public class ObjectStack {
	private ArrayIndexedCollection stack;
	
	/** Default constructor which sets initial values of the stack
	 * 	
	 *
	 */
	public ObjectStack() {
		this.stack = new ArrayIndexedCollection();
	}
	
	/** Method checks are there any elements currently on the stack.
	 * 	
	 * 	@return false if there are no elements on stack, else false
	 *
	 */
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}
	
	/** Method returns the current number of elements on the stack
	 * 	
	 *
	 */
	public int size() {
		return this.stack.size();
	}
	
	/** Method is putting an Object on top of the stack
	 * 	
	 * 	@param Object that is being pushed
	 * 	@throws IllegalArgumentException if the reference to Object is <code>null</code>
	 */
	public void push(Object value) {
		if(value == null) throw new IllegalArgumentException();
		this.stack.add(value);
	}
	
	/** Method is removing an Object from the top of the stack
	 * 	
	 * 	@throws EmptyStackException if the stack is empty
	 * 	@returns Object that has been removed from the stack
	 */
	public Object pop() {
		if(stack.size() == 0) throw new EmptyStackException("Stack is empty!", null);
		Object result = this.stack.get(this.stack.size() - 1);
		this.stack.remove(this.stack.size() - 1);
		return result;
	}
	
	/** Method is doing showing an Object from the top of the stack, but
	 * 	it is not removing it from the stack
	 * 
	 * 	@throws EmptyStackException if the stack is empty
	 * 	@returns Object that has been removed from the stack
	 * 	
	 */
	public Object peek() {
		if(stack.size() == 0) throw new EmptyStackException("Stack is empty!", null);
		return this.stack.get(this.stack.size() - 1);
	}
	
	/** Method deletes all the elements from the array and resets it's size to zero
	 * 	
	 *
	 */
	public void clear() {
		this.stack.clear();
	}
}
