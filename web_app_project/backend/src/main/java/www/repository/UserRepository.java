package www.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import www.models.User;

@Transactional
public interface UserRepository extends UserBasicRepository<User>{
	
	Optional<User> findByEmail(String email);

	
}
