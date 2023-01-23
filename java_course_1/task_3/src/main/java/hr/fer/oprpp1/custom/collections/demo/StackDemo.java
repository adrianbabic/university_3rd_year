package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ObjectStack;


/**This class is used to show a simple example how an ObjectStack class can be used.
 * Only works with a signle command-line argument which is an expression which
 * must be written in postfix representation, otherwise throws various errors.
 * 
 * @author adrian
 *
 */

public class StackDemo extends ObjectStack<Integer> {
	
	public StackDemo() {
		super();
	}
	
	/** Very restricted method which is checking is a certain String a number.
	 * 	Works well only if a number is an integer.
	 * 	@params one String
	 * 	@return true if String is an integer, otherwise false
	 * 	
	 *
	 */
	public static boolean isNumber(String part) {
		if(part == null) return false;
		try {
			Double.parseDouble(part);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/** Main method which manipulates the given postfix expression. 
	 * 	
	 * 	@params single String which has to be written in postfix representation
	 * 	@return Evalutaion of given expression
	 * 	@throws Will throw IllegalArgumentException if there is something wrong with arguments
	 *
	 */
	public static void main(String[] args) {
		StackDemo stack = new StackDemo();		
		String expression = args[0];
		String[] parts = expression.trim().split(" ");
		for(String part: parts) {
			if(part.trim().equals("")) continue;
			if(isNumber(part)) stack.push(Integer.valueOf(part));
			else {
				int num2 = (Integer) stack.pop();
				int num1 = (Integer) stack.pop();
				if(part.equals("+")) stack.push(num1 + num2);
				else if(part.equals("-")) stack.push(num1 - num2);
				else if(part.equals("/") && num2 != 0) stack.push(num1 / num2);
				else if(part.equals("*")) stack.push(num1 * num2);
				else if(part.equals("%") && num2 != 0) stack.push(num1 % num2);
				else throw new IllegalArgumentException("Something is wrong with arguments!");
			}
		}
		if(stack.size() != 1) throw new IllegalArgumentException("Number of arguments was wrong!");
		else System.out.println("Expression evaluates to: " + stack.pop().toString());
	}

}
