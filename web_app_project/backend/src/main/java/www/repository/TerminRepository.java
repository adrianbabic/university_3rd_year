package www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.models.Instruktor;
import www.models.Pitanje;
import www.models.Termin;

import java.util.List;
import java.util.Optional;

public interface TerminRepository extends JpaRepository<Termin, Long> {

}
