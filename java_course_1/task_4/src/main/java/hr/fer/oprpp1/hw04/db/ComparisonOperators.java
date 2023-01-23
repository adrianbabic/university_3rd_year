package hr.fer.oprpp1.hw04.db;

/**	Class consists of public variables which represent and object implementing
 * 	IComparisonOperator interface and overriding the appropriate method using
 * 	lambda expression.
 *  
 * 	@author adrian
 */
public class ComparisonOperators {
	
	/** Public variable which is an implementation of IComparisonOperator interface. Returns 
	 * 	an information wether the first given string is less than the second given string. 
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is less than the second
	 * 	@return false if the first string is not less than the second	
	 */
	public static final IComparisonOperator LESS = (v1, v2) -> {
		if(v1.compareTo(v2) < 0) 
			return true;
		return false;
	};
	
	/** Public variable which is an implementation of IComparisonOperator interface. Returns 
	 * 	an information wether the first given string is less or rqual to the second given string. 
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is less or equal to the second
	 * 	@return false if the first string is not less or equal to the second	
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = (v1, v2) -> {
		if(v1.compareTo(v2) <= 0) 
			return true;
		return false;
	};
	
	/** Public variable which is an implementation of IComparisonOperator interface. Returns 
	 * 	an information wether the first given string is greater than the second given string. 
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is greater than the second
	 * 	@return false if the first string is not greater than the second	
	 */
	public static final IComparisonOperator GREATER = (v1, v2) -> {
		if(v1.compareTo(v2) > 0) 
			return true;
		return false;
	};
	
	/** Public variable which is an implementation of IComparisonOperator interface. Returns 
	 * 	an information wether the first given string is less than the second given string. 
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is greater or equal the second
	 * 	@return false if the first string is not greater or equal to the second	
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (v1, v2) -> {
		if(v1.compareTo(v2) >= 0) 
			return true;
		return false;
	};
	
	/** Public variable which is an implementation of IComparisonOperator interface. Returns 
	 * 	an information wether the first given string is equal the second given string. 
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is equal to the second
	 * 	@return false if the first string is not equal to the second	
	 */
	public static final IComparisonOperator EQUALS = (v1, v2) -> {
		if(v1.compareTo(v2) == 0) 
			return true;
		return false;
	};
	
	/** Public variable which is an implementation of IComparisonOperator interface. Returns 
	 * 	an information wether the first given string is equal to the second given string. 
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is not equal to the second
	 * 	@return false if the first string is equal to the second	
	 */
	public static final IComparisonOperator NOT_EQUALS = (v1, v2) -> {
		if(v1.compareTo(v2) != 0) 
			return true;
		return false;
	};
	
	/** Public variable which represents an implementation of ICompariosonInterface. Returns
	 * 	an information wether the first given string is like the second given string.
	 * 	Symbol '*' in the second string is a wildcard character whose place can be replaced
	 * 	by any given string, meaning that first string has to be identical to the second string
	 * 	before and after the wildcard character, but can have any string combination in place of the
	 * 	wildcard character.
	 * 
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if the first string is like the second
	 * 	@return false if the first string not like the second
	 */
	public static final IComparisonOperator LIKE = (v1, v2) -> {
		if(v2.contains("*")) {
			int index = v2.indexOf("*");
			if(index != v2.lastIndexOf("*")) throw new IllegalArgumentException("Wildcard character * can occur at most once!");
			if(index == v2.length())
				return v1.startsWith(v2.substring(0, index));
			if(index == 0)
				return v1.endsWith(v2.substring(1));
			return v1.startsWith(v2.substring(0, index)) && v1.endsWith(v2.substring(index + 1)) && v1.length() >= v2.length() - 1;
		}
		return v1.equals(v2);
	};
	

}
