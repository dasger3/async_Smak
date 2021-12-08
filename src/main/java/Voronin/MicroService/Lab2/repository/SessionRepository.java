package Voronin.MicroService.Lab2.repository;

import Voronin.MicroService.Lab2.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
