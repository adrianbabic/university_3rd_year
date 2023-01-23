package www.responses;

import java.util.ArrayList;
import java.util.List;

import www.models.Pitanje;

public class IspitResponse {

	public class PitanjeBezOdgovora {
		String pitanje;
		List<String> odgovori;

		public String getPitanje() {
			return pitanje;
		}

		public void setPitanje(String pitanje) {
			this.pitanje = pitanje;
		}

		public List<String> getOdgovori() {
			return odgovori;
		}

		public void setOdgovori(List<String> odgovori) {
			this.odgovori = odgovori;
		}

	}

	List<PitanjeBezOdgovora> pitanja = new ArrayList<>();
	
	public IspitResponse(List<Pitanje> pitanjaIOdgovodi) {
		for (Pitanje pitanje: pitanjaIOdgovodi) {
			addPitanje(pitanje);
		}
	}

	public void addPitanje(Pitanje pitanje) {
		PitanjeBezOdgovora p = new PitanjeBezOdgovora();
		p.setPitanje(pitanje.getTextPitanja());
		p.setOdgovori(pitanje.getPonudeniOdgovori());
		pitanja.add(p);
	}

	public List<PitanjeBezOdgovora> getPitanja() {
		return pitanja;
	}

	public void setPitanja(List<PitanjeBezOdgovora> pitanja) {
		this.pitanja = pitanja;
	}

}
