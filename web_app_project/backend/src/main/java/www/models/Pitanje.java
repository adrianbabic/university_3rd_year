package www.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "pitanja")
public class Pitanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pitanjeId;
	@Column(nullable = false)
	private String textPitanja;
	@Column(nullable = false)
	@ElementCollection
	private List<String> ponudeniOdgovori;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ispit_id", nullable = false)
	private Ispit ispit;
	@Column(nullable = false)
	private String tocanOdgovor;

	public Pitanje(String textPitanja, List<String> ponudeniOdgovori, String tocanOdgovor, Ispit ispit) {
		this.textPitanja = textPitanja;
		this.ponudeniOdgovori = ponudeniOdgovori;
		this.tocanOdgovor = tocanOdgovor;
		this.ispit = ispit;
	}

	public Pitanje() {
	}

	public Long getPitanjeId() {
		return pitanjeId;
	}

	public void setPitanjeId(Long pitanjeId) {
		this.pitanjeId = pitanjeId;
	}

	public String getTextPitanja() {
		return textPitanja;
	}

	public void setTextPitanja(String textPitanja) {
		this.textPitanja = textPitanja;
	}

	public List<String> getPonudeniOdgovori() {
		return ponudeniOdgovori;
	}

	public void setPonudeniOdgovori(List<String> ponudeniOdgovori) {
		this.ponudeniOdgovori = ponudeniOdgovori;
	}

	public String getTocanOdgovor() {
		return tocanOdgovor;
	}

	public void setTocanOdgovor(String tocanOdgovor) {
		this.tocanOdgovor = tocanOdgovor;
	}

	@Override
	public String toString() {
		return "Pitanje [id=" + pitanjeId + ", textPitanja=" + textPitanja + ", ponudeniOdgovori=" + ponudeniOdgovori
				+ ", tocanOdgovor=" + tocanOdgovor + "]";
	}

}
