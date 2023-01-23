package www.controller;

import www.exception.NoSuchStudentException;
import www.models.Instruktor;
import www.responses.InstruktorInstruktoriResponse;
import www.responses.StudentResponse;
import www.service.InstruktorService;
import www.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/a")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

	@Autowired
    InstruktorService instruktorService;
	@Autowired
    StudentService studentService;
	
	@GetMapping("/instruktori")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllInstructors() {
		return ResponseEntity.ok(new InstruktorInstruktoriResponse(instruktorService.getAllInstructors()));
	}

	@GetMapping("/instruktor/id={id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Instruktor> getInstructorById(@PathVariable String id) {
		return ResponseEntity.ok(instruktorService.getInstructorWithId(id));
	}

	@GetMapping("/studenti")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllStudents() {
		return ResponseEntity.ok(new StudentResponse(studentService.getAllStudents()));
	}
	
	@GetMapping("/student/id={id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getStudnetById(@PathVariable String id) {
		try {
			return ResponseEntity.ok(studentService.getStudentWithId(id));			
		} catch (NoSuchStudentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("takav student ne postoji");
		}
	}
	

	@DeleteMapping("/instruktori/id={id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteInstruktor(@PathVariable String id) {
		instruktorService.deleteInstructorById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/studenti/id={id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteStudent(@PathVariable String id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
}
