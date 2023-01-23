package www.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Data
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "instruktor")
public class Instruktor extends User {
	private static final long serialVersionUID = 5535915308368219428L;

	@Column(nullable = true)
	private Integer maxRazred = 0;

	@Column(nullable = true)
	private String mobileNum = null;

	@Column(nullable = true)
	private String adresa = null;

	@Column(nullable = true, length = 255)
	private String opis;

	@Column(nullable = true)
	@ManyToMany
	private List<Predmet> predajePredmete = new ArrayList<>();


	@OneToMany(mappedBy = "instruktor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Termin> slobodniTermini = new ArrayList<>();

	public Instruktor() {
		super();
	}
	
	public Instruktor(String email, String hashedPassword, String firstName, String lastName, String brojMob,
			String adresa) {
		super(email, hashedPassword, firstName, lastName);
		this.maxRazred = null;
		this.mobileNum = brojMob;
		this.adresa = adresa;
	}

	public Integer getMaxRazred() {
		return maxRazred;
	}

	public void setMaxRazred(Integer maxRazred) {
		this.maxRazred = maxRazred;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOpis() {
		return opis;
	}

	public List<Predmet> getPredajePredmete() {
		return predajePredmete;
	}

	public void addPredmet(Predmet predmet) {
		predajePredmete.add(predmet);
	}

	public void removePredmet(Predmet predmet) {
		predajePredmete.remove(predmet);
	}

	public List<Termin> getSlobodniTermini() {
		return slobodniTermini;
	}

	public void setSlobodniTermini(List<Termin> slobodniTermini) {
		this.slobodniTermini = slobodniTermini;
	}

	public void addTermin(Termin termin) {
		slobodniTermini.add(termin);
	}

	public void removeTermin(Termin termin) {
		slobodniTermini.remove(termin);
	}

	@Override
	public String toString() {
		return "Instruktor{" +
				"maxRazred=" + maxRazred +
				", mobileNum='" + mobileNum + '\'' +
				", adresa='" + adresa + '\'' +
				", opis='" + opis + '\'' +
				", predajePredmete=" + predajePredmete +
				", slobodniTermini=" + slobodniTermini +
				'}';
	}
}
