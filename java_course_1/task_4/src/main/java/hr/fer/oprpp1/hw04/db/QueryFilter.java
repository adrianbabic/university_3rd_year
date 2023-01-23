package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**	Class is used for filtering objects of type StudenRecord.
 *  
 * 	@author adrian
 */
public class QueryFilter implements IFilter{
	
	/** List reference for list containing objects of type ConditionalExpression. **/
	private List<ConditionalExpression> expressions;
	
	/** Default constructor which stores the given list of ConditionalExpression objects.
	 *	
	 *	@param reference to a List<ConditionalExpression>
	 */
	public QueryFilter(List<ConditionalExpression> expressions) {
		this.expressions = expressions;
	}
	
	/** Method accepts an object of type StudentRecord if it satisifies all the conditional
	 * 	expressions.
	 *	
	 *	@param object of type StudentRecord
	 *	@return true if the given object satisifies all the conditional expressions
	 *	@return false if the given object does not satisify all the conditional expressions
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		for(ConditionalExpression one: expressions) {
			if(!one.getComparisonOperator().satisfied(
					one.getFieldGetter().get(record), one.getStringLiteral() ))
				return false;
		}
		return true;
	}

}
