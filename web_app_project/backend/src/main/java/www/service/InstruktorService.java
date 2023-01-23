package www.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import www.models.Instruktor;
import www.models.Predmet;
import www.repository.InstruktorRepository;
import www.repository.PredmetRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class InstruktorService {

	@Autowired
	InstruktorRepository instruktorRepository;
	@Autowired
	PredmetRepository predmetRepository;

	public List<Instruktor> getAllInstructors() {
		return instruktorRepository.findAll();
	}

	public Instruktor getInstructorWithId(String id) {
		return instruktorRepository.findById(Long.parseLong(id)).get();
	}

	public Instruktor getInstructorWithEmail(String email) {
		return instruktorRepository.findByEmail(email).get();
	}

	public boolean deleteInstructorById(String id) {
		try {
			Long numberId = Long.parseLong(id);
			instruktorRepository.deleteById(numberId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean updateInstruktor(String id, String firstName, String lastName,
									String adress, String mobileNum, String opis, List<String> predmeti) {

		Instruktor instruktor = instruktorRepository.findById(Long.parseLong(id)).get();

		try {
			if (!instruktor.getFirstName().equals(firstName)) instruktor.setFirstName(firstName);
			if (!instruktor.getLastName().equals(lastName)) instruktor.setLastName(lastName);
			if (instruktor.getAdresa() == null || !instruktor.getAdresa().equals(adress)) instruktor.setAdresa(adress);
			if (instruktor.getMobileNum() == null || !instruktor.getMobileNum().equals(mobileNum)) instruktor.setMobileNum(mobileNum);
			if (instruktor.getOpis() == null || !instruktor.getOpis().equals(opis)) instruktor.setOpis(opis);

			List<Predmet> listaPredmeta = predmetRepository.findAll();


			System.out.println("svi predmeti = "+listaPredmeta.toString());
			System.out.println("predmeti = "+predmeti.toString());
			for (Predmet predmet : listaPredmeta) {
				String name = predmet.getName().toLowerCase();
				if(predmeti.contains(name) && !instruktor.getPredajePredmete().contains(predmet)) {
					instruktor.addPredmet(predmet);
				} else if(!predmeti.contains(name) && instruktor.getPredajePredmete().contains(predmet)) {
					instruktor.removePredmet(predmet);
				}
			}
			System.out.println(instruktor);
			instruktorRepository.save(instruktor);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
