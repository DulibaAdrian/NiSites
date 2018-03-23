package interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.domain.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

}
