package www.requests;

import javax.validation.constraints.NotBlank;

public class SignUpRequest {
	
	@NotBlank
	public String email;
	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;
	@NotBlank
	public String password;
	@NotBlank
	public String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignUpRequest [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", type=" + type + "]";
	}
	
}
