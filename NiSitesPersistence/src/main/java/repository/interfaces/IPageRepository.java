package repository.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.domain.Page;

@Repository
public interface IPageRepository extends JpaRepository<Page, UUID> {

}
