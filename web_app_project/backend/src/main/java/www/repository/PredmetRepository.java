package www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.models.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Short>{

	Optional<Predmet> findByName(String name);
	
}
