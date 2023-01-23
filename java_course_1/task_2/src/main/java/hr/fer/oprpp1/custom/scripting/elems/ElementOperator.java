package hr.fer.oprpp1.custom.scripting.elems;

import java.util.Objects;

/**	Class inherits Element class and it is used for creating elements that represent an operator.
 *  
 * 	@author adrian
 *
 */

public class ElementOperator extends Element {
	@Override
	public int hashCode() {
		return Objects.hash(symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementOperator other = (ElementOperator) obj;
		return Objects.equals(symbol, other.symbol);
	}

	private String symbol;
	
	/** Constructor for this class.
	 * 	
	 * @param String which represents a symbol of an operator
	 */
	public ElementOperator(String symbol) {
		this.symbol = symbol;
	}
	
	/** Getter for symbol.
	 * 	
	 * @return String which represents symbol of an operator
	 * 
	 */
	@Override
	public String asText() {
		return this.symbol;
	}

}
