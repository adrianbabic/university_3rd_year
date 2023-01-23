package www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.models.Razred;

public interface RazredRepository extends JpaRepository<Razred, Long>{

	Optional<Razred> findByRazina(String razina);
	
}
