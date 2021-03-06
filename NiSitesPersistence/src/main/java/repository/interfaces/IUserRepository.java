package repository.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.domain.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
	
	public User findByName(String name);

	public User findByEmail(String email);

	public User findByEmailAndPassword(String email, String password);
}
