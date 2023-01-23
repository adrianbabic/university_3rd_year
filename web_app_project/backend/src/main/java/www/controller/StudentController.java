package www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwt;
import www.exception.NoSuchStudentException;
import www.models.Instruktor;
import www.models.Student;
import www.responses.StudentInstruktoriResponse;
import www.responses.TerminiResponse;
import www.security.config.JWTToken.JwtToken;
import www.service.InstruktorService;
import www.service.StudentService;
import www.service.TerminiService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/s")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	@Autowired
	InstruktorService instruktorService;
	@Autowired
	TerminiService terminiService;
	@Autowired
	JwtToken jwtToken;	
	
	@GetMapping("/instruktori")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public ResponseEntity<?> getAllFavouriteInstructors(@RequestHeader("authorization") String token) {
		
		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));
		
		return ResponseEntity.ok(new StudentInstruktoriResponse(instruktorService.getAllInstructors(), studentService.getFavouriteInstructors(email)));
	}

	@GetMapping("/kalendar/id={id}")
	@PreAuthorize("hasRole('ROLE_STUDENT') or  hasRole('ROLE_INSTRUKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTerminiByInstructorId(@PathVariable String id) {
		return ResponseEntity.ok(new TerminiResponse(terminiService.getInstructorsTerminiById(id)));
	}
	
	@GetMapping("/instruktor/id={id}")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public ResponseEntity<Instruktor> getInstructorById(@PathVariable String id) {
		return ResponseEntity.ok(instruktorService.getInstructorWithId(id));
	}

	@PostMapping("/favourites/add/id={id}")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public ResponseEntity<?> addFavouriteInstructor(@RequestHeader("authorization") String token, @PathVariable String id) {

		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));

		studentService.addInstructorToFavourite(email, id);

		return ResponseEntity.ok("");
	}

	@PostMapping("/favourites/remove/id={id}")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public ResponseEntity<?> removeFavouriteInstructor(@RequestHeader("authorization") String token, @PathVariable String id) {

		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));
		System.out.println(email + "          "+ id);
		studentService.removeInstructorToFavourite(email, id);

		return ResponseEntity.ok("");
	}

	@GetMapping("/currentStudent")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public  ResponseEntity<Student> getCurrentStudent(@RequestHeader("authorization") String token) throws NoSuchStudentException {
		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));

		System.out.println(email);
		return ResponseEntity.ok(studentService.getStudentWithEmail(email));
	}
	
}
