package www.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "ispit")
public class Ispit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ispitId;
	@ManyToOne
	private Predmet predmet;
	@OneToMany(mappedBy = "ispit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pitanje> pitanja = new ArrayList<>();
	@ManyToOne
	private Razred razred;

	public Ispit() {
	}

	public Ispit(Razred razred, Predmet predmet, List<Pitanje> pitanja) {
		this.razred = razred;
		this.predmet = predmet;
		this.pitanja = pitanja;
	}

	public Long getIdIspit() {
		return ispitId;
	}

	public void setId(Long ispitId) {
		this.ispitId = ispitId;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public List<Pitanje> getPitanja() {
		return pitanja;
	}

	public void setPitanja(List<Pitanje> pitanja) {
		this.pitanja = pitanja;
	}

	public void addPitanje(Pitanje pitanje) {
		pitanja.add(pitanje);
	}

	public Razred getRazred() {
		return razred;
	}

	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	@Override
	public String toString() {
		return "Ispit [idIspit=" + ispitId + ", predmet=" + predmet + ", pitanja=" + pitanja + ", razred=" + razred
				+ "]";
	}
}
