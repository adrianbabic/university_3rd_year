package www.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public List<Student> findAll();
	
	public Optional<Student> findByEmail(String email);
	
}
