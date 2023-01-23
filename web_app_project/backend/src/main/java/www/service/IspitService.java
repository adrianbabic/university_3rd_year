package www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import www.exception.exceptions.NoSuchIspitException;
import www.exception.exceptions.NoSuchPredmetException;
import www.exception.exceptions.NoSuchRazredException;
import www.models.Instruktor;
import www.models.Ispit;
import www.models.Pitanje;
import www.models.Predmet;
import www.models.Razred;
import www.repository.IspitRepository;
import www.repository.PredmetRepository;
import www.repository.RazredRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class IspitService {
	
	@Autowired
	PredmetRepository predmetRepository;
	@Autowired
	RazredRepository razredRepository;
	@Autowired
	IspitRepository ispitRepository;

	public List<Pitanje> getIspitForInstruktor(Instruktor instruktor, String pred, String raz) throws NoSuchIspitException, NoSuchPredmetException, NoSuchRazredException {

		Predmet predmet = predmetRepository.findByName(pred).orElseThrow(() -> new NoSuchPredmetException());
		System.out.println(predmet);
		
		Razred razred = razredRepository.findByRazina(raz).orElseThrow(() -> new NoSuchRazredException());
		System.out.println(razred);
		
		Ispit ispit = ispitRepository.findByPredmetAndRazred(predmet, razred).get();
		System.out.println(ispit);
		
		List<Pitanje> pitanja = ispitRepository.findByPredmetAndRazred(predmet, razred).orElseThrow(() -> new NoSuchIspitException()).getPitanja();
//		System.out.println(pitanja);
		
		return pitanja;
	}

}
