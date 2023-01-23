package www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.exception.NoSuchStudentException;
import www.models.Instruktor;
import www.models.Student;
import www.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	InstruktorService instruktorService;

	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}

	public boolean deleteStudentById(String id) {
		try {
			Long numberId = Long.parseLong(id);
			studentRepository.deleteById(numberId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public List<Instruktor> getFavouriteInstructors(String email) {
		Student student = studentRepository.findByEmail(email).get();
		return student.getFavourites();
	}

	public boolean addInstructorToFavourite(String email, String id) {
		Student student = studentRepository.findByEmail(email).get();
		student.addFavouriteInstruktor(instruktorService.getInstructorWithId(id));
		studentRepository.save(student);
		return true;
	}

	public boolean removeInstructorToFavourite(String email, String id) {
		Student student = studentRepository.findByEmail(email).get();
		student.removeFavouriteInstruktor(instruktorService.getInstructorWithId(id));
		studentRepository.save(student);
		return true;
	}
	
	public Student getStudentWithId(String id) throws NoSuchStudentException {
		return studentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new NoSuchStudentException());
	}

	public Student getStudentWithEmail(String email) throws NoSuchStudentException {
		return studentRepository.findByEmail(email).orElseThrow(() -> new NoSuchStudentException());
	}

}
