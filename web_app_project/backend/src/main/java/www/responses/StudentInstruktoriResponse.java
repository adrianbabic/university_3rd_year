package www.responses;

import java.util.ArrayList;
import java.util.List;

import www.models.Instruktor;

public class StudentInstruktoriResponse {

	List<Instruktor> instructors = new ArrayList<>();
	
	List<Instruktor> favourites = new ArrayList<>();
		
	public StudentInstruktoriResponse(List<Instruktor> instructors, List<Instruktor> favourites) {
		this.instructors = instructors;
		this.favourites = favourites;
	}
	
	public List<Instruktor> getAllInstructors() {
		return instructors;
	}
	
	public List<Instruktor> getFavourites() {
		return favourites;
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
