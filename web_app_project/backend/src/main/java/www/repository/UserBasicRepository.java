package www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import www.models.User;

@NoRepositoryBean
public interface UserBasicRepository<T extends User> extends JpaRepository<T, Long> {

	Optional<T> findByEmail(String email);
}

