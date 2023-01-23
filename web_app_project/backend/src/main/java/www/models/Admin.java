package www.models;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{
	
	public Admin() {
		super();
	}
	
	public Admin(String email, String hashedPassword, String firstName, String lastName) {
		super(email, hashedPassword, firstName, lastName);
		// TODO Auto-generated constructor stub
	}
}
