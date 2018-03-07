package entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.domain.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {

}
