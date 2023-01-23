package hr.fer.oprpp1.hw04.db;

/**	Class used for storing conditional expression. 
 * 	Conditional expression consists of a single FieldValueGetter, a single CompariosonOperator and
 * 	a single String literal. Nothing more and nothing less can be stored in a single conditional expression.
 *  
 * 	@author adrian
 */
public class ConditionalExpression {
	/** Variable for storing object of type IComparisonOperator. **/
	private IComparisonOperator comparisonOperator;
	/** Variable for storing object of type IFieldValueGetter. **/
	private IFieldValueGetter fieldGetter;
	/** Variable for storing object of type String. **/
	private String stringLiteral;
	
	/** Default (and only available) constructor which stores given values in variables.	
	 * 
	 */
	public ConditionalExpression(IFieldValueGetter fieldGetter, String stringLiteral, IComparisonOperator comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
		this.stringLiteral = stringLiteral;
		this.fieldGetter = fieldGetter;
	}
	
	/** Getter for an object of type IComparisonOperator.	
	 * 
	 * 	@return object of type IComparisonOperator
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}
	
	/** Getter for an object of type IFieldValueGetter.	
	 * 
	 * 	@return object of type IFieldValueGetter
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}
	
	/** Getter for an object of type String.	
	 * 
	 * 	@return object of type String
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}
	
	

}
