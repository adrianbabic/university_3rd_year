package hr.fer.oprpp1.hw04.db;

/**	Interface for field value getters which are required to override a method
 * 	which returns a specific string which represents that specific information about
 * 	the object of type StudentRecord.
 *  
 * 	@author adrian
 */
public interface IFieldValueGetter {
	
	/** Method is a getter for a String representing a specific value from object of 
	 * 	type StudenRecord.
	 * 	
	 * 	@param object of type StudenRecord
	 * 	@return object of type String
	 */
	public String get(StudentRecord record);
}
