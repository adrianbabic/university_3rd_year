package www.requests;

import javax.validation.constraints.NotBlank;

public class IspitRequest {

	@NotBlank
	private String razred;
	@NotBlank
	private String predmet;

	public String getRazred() {
		return razred;
	}

	public void setRazred(String razred) {
		this.razred = razred;
	}

	public String getPredmet() {
		return predmet;
	}

	public void setPredmet(String predmet) {
		this.predmet = predmet;
	}

	@Override
	public String toString() {
		return "IspitRequest [razred=" + razred + ", predmet=" + predmet + "]";
	}

}
