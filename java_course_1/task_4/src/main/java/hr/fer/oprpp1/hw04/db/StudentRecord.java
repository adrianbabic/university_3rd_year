package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**	Class used for storing values as a student record.
 *  
 * 	@author adrian
 */
public class StudentRecord {
	
	/** Object of type String representing student's jmbag. **/
	private String jmbag;
	/** Object of type String representing student's last name. **/
	private String lastName;
	/** Object of type String representing student's first name. **/
	private String firstName;
	/** Object of type integer representing student's grade. **/
	private int finalGrade;
	
	/** Default constructor which stores the given values	
	 *
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jmbag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		return Objects.equals(jmbag, other.jmbag);
	}

	public String getJmbag() {
		return jmbag;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getFinalGrade() {
		return finalGrade;
	}

	@Override
	public String toString() {
		return "StudentRecord [jmbag=" + jmbag + ", lastName=" + lastName + ", firstName=" + firstName + ", finalGrade="
				+ finalGrade + "]";
	}
	
	
}
