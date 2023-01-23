package www.security.config.JWTToken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import www.exception.exceptions.JWTTokenException;

@Component("jwtTokenFilter")
public class JwtTokenFilter extends OncePerRequestFilter{

	@Autowired
	JwtToken jwtToken;
	
	@Autowired
    UserDetailsServiceImpl authService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String jwt = parseJwt(request);
			System.err.println(jwt);
			if (jwt != null && jwtToken.validateJwtToken(jwt)) {
				String email = jwtToken.getUserEmailFromJwtToken(jwt);
				System.err.println(email);
				
				UserDetails userDetails = authService.loadUserByUsername(email);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				System.err.println(authentication);

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (UsernameNotFoundException | JWTTokenException e) {
			e.printStackTrace();
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String parseJwt(HttpServletRequest request) {
		
		String authHeader= request.getHeader("Authorization");

		//auth header počinje s: Bearer <token>
		if (authHeader != null && !authHeader.isEmpty() && authHeader.startsWith("Bearer ")) {
			if(!authHeader.substring(7, authHeader.length()).equals("null")) {
				return authHeader.substring(7, authHeader.length()) ;				
			}
		}

		return null;
	}

}
