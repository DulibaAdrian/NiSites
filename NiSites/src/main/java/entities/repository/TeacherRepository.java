package entities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.domain.Teacher;


public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	/**
	 * findByName
	 * @param name
	 * @return
	 */
	List<Teacher>findByName(String name);
}
