package www.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "razred")
public class Razred {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String razina;
	@OneToMany(mappedBy = "razred")
	private List<Ispit> ispiti;
	
	public Razred() {}
	
	public Razred(String razina) {
		this.razina = razina;
		ispiti = new ArrayList<Ispit>();
	}
	
	public String getRazina() {
		return razina;
	}
	
	public void setRazina(String razina) {
		this.razina = razina;
	}

	@Override
	public String toString() {
		return "razina=" + razina;
	}

}
