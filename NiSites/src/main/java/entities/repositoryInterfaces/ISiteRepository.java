package entities.repositoryInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.domain.Site;

@Repository
public interface ISiteRepository extends JpaRepository<Site, Integer> {

}
