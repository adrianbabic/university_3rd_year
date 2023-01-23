package www.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.models.Instruktor;
import www.requests.AddTerminRequest;
import www.requests.UpdateInstruktorRequest;
import www.responses.InstruktorInstruktoriResponse;
import www.responses.IspitResponse;
import www.responses.ResponseHandler;
import www.responses.TerminiResponse;
import www.security.config.JWTToken.JwtToken;
import www.service.InstruktorService;
import www.service.TerminiService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/i")
public class InstruktorController {
	
	@Autowired
    InstruktorService instruktorService;
	@Autowired
	TerminiService terminiService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtToken jwtToken;

	@GetMapping("/instruktori")
	@PreAuthorize("hasRole('ROLE_INSTRUKTOR')")
	public ResponseEntity<?> getAllInstructors() {
		return ResponseEntity.ok(new InstruktorInstruktoriResponse(instruktorService.getAllInstructors()));
	}
	
	@GetMapping("/instruktor/id={id}")
	@PreAuthorize("hasRole('ROLE_STUDENT') or  hasRole('ROLE_INSTRUKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Instruktor> getInstructorById(@PathVariable String id) {
		return ResponseEntity.ok(instruktorService.getInstructorWithId(id));
	}

	@GetMapping("/kalendar/id={id}")
	@PreAuthorize("hasRole('ROLE_STUDENT') or  hasRole('ROLE_INSTRUKTOR') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTerminiByInstructorId(@PathVariable String id) {
		return ResponseEntity.ok(new TerminiResponse(terminiService.getInstructorsTerminiById(id)));
	}

	@GetMapping("/kalendar")
	@PreAuthorize("hasRole('ROLE_INSTRUKTOR')")
	public ResponseEntity<?> getMyTermini(@RequestHeader("authorization") String token) {

		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));

		return ResponseEntity.ok(new TerminiResponse(terminiService.getInstructorsTerminiByEmail(email)));
	}

	@PostMapping("/kalendar")
	@PreAuthorize("hasRole('ROLE_INSTRUKTOR')")
	public ResponseEntity<?> addNoviTermin(@Valid @RequestBody AddTerminRequest addTerminRequest, @RequestHeader("authorization") String token) {
		System.err.println(addTerminRequest);
		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));

//		System.out.println("STIGLO: " + addTerminRequest);
//		System.out.println();
//		System.out.println();
//		System.out.println("STIGLO");
//		System.out.println();
		boolean retval = terminiService.addNoviTermin(
				addTerminRequest.year,
				addTerminRequest.month,
				addTerminRequest.day,
				addTerminRequest.startHour,
				addTerminRequest.startMinute,
				addTerminRequest.endHour,
				addTerminRequest.endMinute,
				email
		);
//		ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Termin nije dodan. Provjerite sintaksu.");
		if (!retval) {
			return ResponseHandler.generateResponse("Termin nije dodan. Provjerite sintaksu ili unesite termin koji se ne preklapa s postojećima.",
					HttpStatus.BAD_REQUEST, new TerminiResponse(terminiService.getInstructorsTerminiByEmail(email)));
		}
		return ResponseHandler.generateResponse("Termin je uspješno dodan.",
				HttpStatus.OK, new TerminiResponse(terminiService.getInstructorsTerminiByEmail(email)));
	}

	
	//mozda ce biti potrebno slati neki dokaz da je to taj instruktor
	//poslati jwt token i onda provjeriti mail s id-em korisnika
	
	@GetMapping("/instruktor/profil")
	@PreAuthorize("hasRole('ROLE_INSTRUKTOR')")
	public ResponseEntity<Instruktor> getDataForUpdating(@RequestHeader("authorization") String token) {
		
		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));
		
		return ResponseEntity.ok(instruktorService.getInstructorWithEmail(email));
	}
	
	@PostMapping("/instruktor/update/id={id}")
	@PreAuthorize("hasRole('ROLE_INSTRUKTOR')")
	public ResponseEntity<?> updateInstrkutor(@Valid @RequestBody UpdateInstruktorRequest updateInstruktorRequest, @PathVariable String id) {
		System.err.println(updateInstruktorRequest);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < updateInstruktorRequest.getPredmeti().length; i++) {
			list.add(updateInstruktorRequest.getPredmeti()[i]);
		}

		System.out.println(list.toString());

		boolean retval = instruktorService.updateInstruktor(id,
				updateInstruktorRequest.getFirstName(),
				updateInstruktorRequest.getLastName(),
				updateInstruktorRequest.getAdress(),
				updateInstruktorRequest.getMobileNum(),
				updateInstruktorRequest.getOpis(),
				list
				);

		 if (!retval) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("neuspjesno postavljanje novih podataka");
		 }
		 return ResponseEntity.status(HttpStatus.OK).body("podatci uspjesno promijenjeni");
	}
	
}
