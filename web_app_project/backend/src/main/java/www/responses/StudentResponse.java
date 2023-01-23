package www.responses;

import java.util.ArrayList;
import java.util.List;

import www.models.Student;

public class StudentResponse {
	
	List<Student> students = new ArrayList<>();
	
	public StudentResponse(List<Student> students) {
		this.students = students;
	}
	
	public List<Student> getAllStudents() {
		return students;
	}
	
	
	/**
	 * @return returns positive int if sucessfully added to list, otherwise -1
	 */
	public int addStudent(Student student) {
		if(student == null) 
			throw new NullPointerException("student ne moze biti null");
		
		if(students.contains(student)) return -1;
		
		students.add(student);
		return 1;
	}

}
