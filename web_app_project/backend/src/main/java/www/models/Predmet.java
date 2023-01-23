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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "predmeti")
public class Predmet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;
	@Column(nullable = false)
	private String name;
	@OneToMany(mappedBy = "predmet")
	private List<Ispit> ispiti;

	public Predmet() {}
	
	public Predmet(String name) {
		this.name = name;
		this.ispiti = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Predmet [name= " + name;
	}

}
