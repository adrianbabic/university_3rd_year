package hr.fer.oprpp1.hw04.db;

/**	Class consists of public variables which represent and object implementing
 * 	IFieldValueGetter interface and overriding the appropriate method using
 * 	lambda expression.
 *  
 * 	@author adrian
 */
public class FieldValueGetters {
	
	/** Public variable which represents an implementation of interface IFieldValueGetter returning 
	 * 	a string representing the first name of the given StudentRecord object.
	 * 
	 *	@param object of type StudentRecord
	 * 	@return object of type String	
	 */
	public static final IFieldValueGetter FIRST_NAME = sr -> {
		return sr.getFirstName();
	};
	
	/** Public variable which represents an implementation of interface IFieldValueGetter returning 
	 * 	a string representing the last name of the given StudentRecord object.
	 * 
	 * 	@param object of type StudentRecord
	 * 	@return object of type String
	 */
	public static final IFieldValueGetter LAST_NAME = sr -> {
		return sr.getLastName();
	};
	
	/** Public variable which represents an implementation of interface IFieldValueGetter returning 
	 * 	a string representing the jmbag of the given StudentRecord object.
	 * 
	 *	@param object of type StudentRecord
	 * 	@return object of type String
	 */
	public static final IFieldValueGetter JMBAG = sr -> {
		return sr.getJmbag();
	};
	

}
