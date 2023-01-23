package hr.fer.oprpp1.custom.scripting.elems;

import java.util.Objects;

/**	Class inherits Element class and it is used for creating constants of type integer
 *  
 * 	@author adrian
 *
 */

public class ElementConstantInteger extends Element {
	private int value;
	
	/** Constructor for this class.
	 * 	
	 * @param integer which represents a value of a constant
	 */
	public ElementConstantInteger(int value) {
		this.value = value;
	}
	
	/** Getter for value.
	 * 	
	 * @return integer which represents value of a constant
	 * 
	 */
	@Override
	public String asText() {
		return Integer.valueOf(value).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementConstantInteger other = (ElementConstantInteger) obj;
		return value == other.value;
	}
	
	

}
