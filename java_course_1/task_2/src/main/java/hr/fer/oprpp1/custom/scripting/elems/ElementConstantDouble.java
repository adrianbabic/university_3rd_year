package hr.fer.oprpp1.custom.scripting.elems;

import java.util.Objects;

/**	Class inherits Element class and it is used for creating constants of type double
 *  
 * 	@author adrian
 *
 */

public class ElementConstantDouble extends Element {
	private double value;
	
	/** Constructor for this class.
	 * 	
	 * @param double which represents a value of a constant
	 */
	public ElementConstantDouble(double value) {
		this.value = value;
	}
	
	/** Getter for value.
	 * 	
	 * @return double which represents value of a constant
	 * 
	 */
	@Override
	public String asText() {
		return Double.valueOf(value).toString();
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
		ElementConstantDouble other = (ElementConstantDouble) obj;
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	
	

}
