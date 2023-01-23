package www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.models.Ispit;
import www.models.Predmet;
import www.models.Razred;


public interface IspitRepository extends JpaRepository<Ispit, Long> {
	
	Optional<Ispit> findByPredmetAndRazred(Predmet predmet, Razred razred);
	
}
