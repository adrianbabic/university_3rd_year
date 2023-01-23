package hr.fer.oprpp1.custom.scripting.elems;

import java.util.Objects;

/**	Class inherits Element class and it is used for creating string elements.
 *  
 * 	@author adrian
 *
 */

public class ElementString extends Element {
	private String value;
	
	/** Constructor for this class.
	 * 	
	 * @param String which represents a value of a string element
	 */
	public ElementString(String value) {
		this.value = value;
	}
	
	/** Getter for value.
	 * 	
	 * @return String which represents value of a string element
	 * 
	 */
	@Override
	public String asText() {
		return this.value;
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
		ElementString other = (ElementString) obj;
		return Objects.equals(value, other.value);
	}
	
	

}
