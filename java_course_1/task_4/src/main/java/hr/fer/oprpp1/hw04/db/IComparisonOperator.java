package hr.fer.oprpp1.hw04.db;

/**	Interface for comparison operators which are required to override a method
 * 	which tests two strings and returns an information about them.
 *  
 * 	@author adrian
 */
public interface IComparisonOperator {
	
	/** Method returns an information about a specific correlation between two strings.
	 * 	
	 * 	@param object of type String for the first value
	 * 	@param object of type String for the second value
	 * 	@return true if certain query is satisifed
	 * 	@return false if certain query is not satisfied
	 */
	public boolean satisfied(String value1, String value2);
}
