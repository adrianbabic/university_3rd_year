package hr.fer.oprpp1.hw04.db.output;

import java.util.ArrayList;
import java.util.List;

import hr.fer.oprpp1.hw04.db.StudentRecord;

/**	Class used for formatting an output for the user.
 * 
 * 	@author adrian
 */
public class RecordFormatter {
	
	/** Method prepares all the given objects of type StudentRecord for an appropriate output.
	 * 	Length of the output will depend on the longest first name and the longest last name 
	 * 	appering in the given list.
	 * 	
	 * 	@param reference to list containing objects of type StudentRecord
	 * 	@param integer representing the maximum possible length of last name
	 * 	@param integer representing the maximum possible length of first name
	 * 	@return reference to list containing strings which are ready for output
	 */
	public static List<String> format(List<StudentRecord> records, int maxLastName, int maxFirstName) {
		List<String> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("+============+");
		for(int i = 0; i < maxLastName + 2; i++) 
			sb.append("=");
		sb.append("+");
		for(int i = 0; i < maxFirstName + 2; i++) 
			sb.append("=");
		sb.append("+===+");
		result.add(sb.toString());
		
		for(StudentRecord one: records) {
			StringBuilder sb1 = new StringBuilder();
			sb1.append("| " + one.getJmbag() + " | ");
			sb1.append(one.getLastName());
			for(int i = 0; i < maxLastName - one.getLastName().length(); i++) {
				sb1.append(" ");
			}
			sb1.append(" | ");
			sb1.append(one.getFirstName());
			for(int i = 0; i < maxFirstName - one.getFirstName().length(); i++) {
				sb1.append(" ");
			}
			sb1.append(" | " + one.getFinalGrade() + " |");
			result.add(sb1.toString());
		}
		result.add(sb.toString());
		result.add("Records selected: " + (result.size() - 2));
		return result;
} 

}
