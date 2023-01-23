package hr.fer.oprpp1.hw04.db;

/**	Interface for implementations of different filters for the object of 
 * 	type StudentRecord.
 *  
 * 	@author adrian
 */
public interface IFilter {
	
	/** Method returns boolean information if an object of type StudentRecord passes
	 * 	through a specific filter. 
	 * 	
	 * 	@param object of type StudenRecord
	 * 	@return true if the given object passes through a specific filter
	 * 	@return false if the given object does not pass through a specific filter
	 */
	public boolean accepts(StudentRecord record);
}
