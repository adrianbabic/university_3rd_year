package www.requests;

import java.util.Arrays;


import javax.validation.constraints.NotBlank;

public class UpdateInstruktorRequest {

	@NotBlank
	public String firstName;
	public String lastName;
	public String mobileNum;
	public String adress;
	public String opis;
	public String[] predmeti;

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

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String[] getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(String[] predmeti) {
		this.predmeti = predmeti;
	}

	@Override
	public String toString() {
		return "UpdateInstruktorRequest{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", mobileNum='" + mobileNum + '\'' +
				", adress='" + adress + '\'' +
				", opis='" + opis + '\'' +
				", predmeti=" + Arrays.toString(predmeti) +
				'}';
	}

}
