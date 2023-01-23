package www.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import www.requests.LoginRequest;
import www.requests.SignUpRequest;
import www.responses.JwtResponse;
import www.security.config.JWTToken.JwtToken;
import www.security.config.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.models.Admin;
import www.models.Instruktor;
import www.models.Student;
import www.models.User;
import www.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginSignController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
    JwtToken token;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = token.generateTokenValue(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		Long userId = userDetails.getId();
		
		User user = userRepository.findById(userId).get();

		return ResponseEntity.ok(new JwtResponse(jwt, userId, userDetails.getUsername(), roles, user.getFirstName(), user.getLastName()));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("email exists");
		}
		
		System.out.println(signUpRequest);

		User user;
		switch (signUpRequest.getType()) {
		case ("instruktor"):
			user = new Instruktor();
			break;
		case ("student"):
			user = new Student();
			break;
		case ("admin"):
			user = new Admin();
			break;
		default:
			return ResponseEntity.badRequest().body("wrong type of user");
		}

		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setHashedPassword(encoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);

		return ResponseEntity.ok().body("registerd");
	}

}
