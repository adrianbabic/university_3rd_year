package www;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import www.models.*;
import www.repository.*;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	PredmetRepository predmetRepository;
	@Autowired
	IspitRepository ispitRepository;
	@Autowired
	RazredRepository razredRepository;
	@Autowired
	PitanjeRepository pitanjeRepository;
	@Autowired
	InstruktorRepository instRe;

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	InstruktorRepository instruktorRepository;
	@Autowired
	TerminRepository terminRepository;


	@Override
	public void run(String... args) throws Exception {

		// dodaj admina
		adminRepository.save(new Admin("admin", encoder.encode("admin"), "admin", "admin"));

		// dodaj sve prdemete
		predmetRepository.save(new Predmet("MATEMATIKA"));
		predmetRepository.save(new Predmet("FIZIKA"));
		predmetRepository.save(new Predmet("KEMIJA"));
		predmetRepository.save(new Predmet("BIOLOGIJA"));
		predmetRepository.save(new Predmet("HRVATSKI"));
		predmetRepository.save(new Predmet("ENGLESKI"));
		
		//dodaj razrede
		razredRepository.save(new Razred("1ss"));
		razredRepository.save(new Razred("2ss"));
		razredRepository.save(new Razred("3ss"));
		razredRepository.save(new Razred("4ss"));
		
		//dodavanje ispita
		Ispit ispit1 = new Ispit();
		ispit1.setPredmet(predmetRepository.findByName("MATEMATIKA").get());
		ispit1.setRazred(razredRepository.findByRazina("1ss").get());

		Pitanje pitanje11 = new Pitanje("koliko je 2 + 2", new ArrayList<String>() {{
			add("1");
			add("2");
			add("3");
			add("4");
		}}, "4", ispit1);
		ispit1.addPitanje(pitanje11);

		//////////////marko

		Pitanje pitanje12 = new Pitanje("Koliko ima cijelih brojeva u skupu S \\sqrt{3},2,0,1, \\frac{2}{3}, \\frac{\\pi}{3}, \\sqrt{9}", new ArrayList<String>() {{
			add("2");
			add("3");
			add("4");
			add("5");
		}}, "4", ispit1);
		ispit1.addPitanje(pitanje12);

		
		Pitanje pitanje13 = new Pitanje("Koliko ima cijelih brojeva u skupu S \\sqrt{3},2,0,1, \\frac{2}{3}, \\frac{\\pi}{3}, \\sqrt{9}", new ArrayList<String>() {{
			add("2");
			add("3");
			add("4");
			add("5");
		}}, "4", ispit1);
		ispit1.addPitanje(pitanje12);

	////////////////////////////

		System.out.println(ispit1);
		ispitRepository.save(ispit1);
		
//		List<Pitanje> listaPitanja = new ArrayList<>();
//		listaPitanja.add(pitanjeRepository.findById(pitanje11.getPitanjeId()).get());
//		
//		ispit1.setPitanja(listaPitanja);
//		
//		ispitRepository.save(ispit1);

		//dodaj par instruktora
		Instruktor miljenko = new Instruktor("miljenko@gmail.com",encoder.encode("miljenko123"),"Miljenko","Krhen", "0988745236","Miljenkova ulica");
		instruktorRepository.save(miljenko);
		Termin termin1 = new Termin(new Date(122, 12, 19, 9, 0), new Date(122, 12, 19, 11, 0), miljenko);
		Termin termin2 = new Termin(new Date(122, 12, 19, 14, 0), new Date(122, 12, 19, 16, 0), miljenko);
		Termin termin3 = new Termin(new Date(122, 12, 19, 17, 0), new Date(122, 12, 19, 20, 0), miljenko);
		terminRepository.save(termin1);
		terminRepository.save(termin2);
		terminRepository.save(termin3);


//		Termin termin1 = new Termin(6, 8, instruktorRepository.findByEmail("miljenko@gmail.com").get());
//		System.out.println(termin1.toString());
//		terminRepository.save(termin1);
		System.out.println(miljenko.toString());
		instruktorRepository.save(new Instruktor("hrvoje@gmail.com",encoder.encode("hrvoje123"),"Hrvoje","Horvat", "0918375038","Hrvojeva ulica"));
		instruktorRepository.save(new Instruktor("mirko@gmail.com",encoder.encode("mirko123"),"Mirko","Miric", "0919187654","Mirkova ulica"));

		//dodaj par studenata
		Student mia = new Student("mia@gmail.com",encoder.encode("mia123"),"Mia","Mikic");
		mia.addFavouriteInstruktor(instruktorRepository.findByEmail("miljenko@gmail.com").get());
		studentRepository.save(mia);
		studentRepository.save(new Student("pero@gmail.com",encoder.encode("pero123"),"Pero","Peric"));
		studentRepository.save(new Student("luka@gmail.com",encoder.encode("luka123"),"Luka","Lukic"));

		///
	}
	
}
