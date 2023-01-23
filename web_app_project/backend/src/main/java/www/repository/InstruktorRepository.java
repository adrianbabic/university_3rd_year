package www.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.models.Instruktor;
import www.models.User;

@Repository
public interface InstruktorRepository extends JpaRepository<Instruktor, Long> {

	public List<Instruktor> findAll();

	Optional<Instruktor> findByEmail(String email);

}
