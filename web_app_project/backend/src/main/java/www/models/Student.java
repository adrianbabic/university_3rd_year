package www.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Data
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

	private int razred = 0;
	
	@ManyToMany
	private List<Instruktor> favourites = new ArrayList<>();
	
	public Student() {
		super();
	}

	/**
	 * @param email
	 * @param hashedPassword
	 * @param firstName
	 * @param lastName
	 */
	public Student(String email, String hashedPassword, String firstName, String lastName) {
		super(email, hashedPassword, firstName, lastName);
	}
	
	public int getRazred() {
		return razred;
	}

	public void setRazred(int razred) {
		this.razred = razred;
	}

	public List<Instruktor> getFavourites() {
		return favourites;
	}

	public void setFavourites(ArrayList<Instruktor> favourites) {
		this.favourites = favourites;
	}
	
	public void addFavouriteInstruktor(Instruktor instruktor) {
		favourites.add(instruktor);
	}

	public void removeFavouriteInstruktor(Instruktor instruktor) {
		favourites.remove(instruktor);
	}
	@Override
	public String toString() {
		return "Student [razred=" + razred + ", favourites=" + favourites + "]";
	}

}
