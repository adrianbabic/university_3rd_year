package www.responses;

import java.util.ArrayList;
import java.util.List;

import www.models.Instruktor;

public class InstruktorInstruktoriResponse {

	List<Instruktor> instructors = new ArrayList<>();
	
	public InstruktorInstruktoriResponse(List<Instruktor> instructors) {
		this.instructors = instructors;
	}
	
	public List<Instruktor> getAllInstructors() {
		return instructors;
	}
	
	
	/**
	 * @return returns positive int if sucessfully added to list, otherwise -1
	 */
	public int addInsturctor(Instruktor instructor) {
		if(instructor == null) 
			throw new NullPointerException("instruktor ne moze biti null");
		
		if(instructors.contains(instructor)) return -1;
		
		instructors.add(instructor);
		return 1;
	}
	
}
