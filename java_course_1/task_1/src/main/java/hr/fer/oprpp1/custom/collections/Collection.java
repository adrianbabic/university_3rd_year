/**
 * 
 */
package hr.fer.oprpp1.custom.collections;

/** Top level class created for purposes of homework1.
 * 	Many of methods in this class are not implemented properly,
 * 	so the class does not have many useful functionalities on 
 * 	it's own.
 * 
 * 	@author adrian
 *
 */
public class Collection {
	
	protected Collection() {
		
	}
	
	public boolean isEmpty() {
		if(this.size() == 0) return true;
		return false;
	}
	
	public int size() {
		return 0;
	}
	
	public void add(Object value) {
		
	}
	
	public boolean contains(Object value) {
		return false;
	}
	
	public boolean remove(Object value) {
		return false;
	}
	
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	public void forEach(Processor processor) {
		
	}
	
	public void addAll(Collection other) {
		class LocalProcessor extends Processor{
			
			
			@Override
			public void process(Object value) {
				add(value);
			}
		}
		
		LocalProcessor local = new LocalProcessor();
		other.forEach(local);
	}
	
	public void clear() {
		
	}
	
	
}
