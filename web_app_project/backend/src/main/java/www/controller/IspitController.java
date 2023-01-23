package www.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import www.exception.exceptions.NoSuchIspitException;
import www.exception.exceptions.NoSuchPredmetException;
import www.exception.exceptions.NoSuchRazredException;
import www.models.Instruktor;
import www.models.Pitanje;
import www.requests.IspitRequest;
import www.responses.IspitResponse;
import www.security.config.JWTToken.JwtToken;
import www.service.InstruktorService;
import www.service.IspitService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ispit")
public class IspitController {

	@Autowired
	IspitService ispitService;
	@Autowired
	InstruktorService instruktorService;
	@Autowired
	JwtToken jwtToken;
	
	@PostMapping ("/dohvati-ispit")
	@PreAuthorize("hasRole('ROLE_INSTRUKTOR')")
	public ResponseEntity<?> getIspitForInstruktor(@RequestHeader("authorization") String token, @Valid @RequestBody IspitRequest ispitRequest) throws NoSuchIspitException, NoSuchPredmetException, NoSuchRazredException {

		String email = jwtToken.getUserEmailFromJwtToken(token.substring(7,token.length()));
		
		Instruktor instruktor = instruktorService.getInstructorWithEmail(email);
		
		try {
			List<Pitanje> pitanja = ispitService.getIspitForInstruktor(instruktor, ispitRequest.getPredmet(), ispitRequest.getRazred());
			return ResponseEntity.ok(new IspitResponse(pitanja));
		} catch (NoSuchRazredException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nije pronaden razred");
		} catch (NoSuchPredmetException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nije pronaden predmet");
		} catch (NoSuchIspitException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nije ispit za taj predmet i razred");
		}
	}
}
