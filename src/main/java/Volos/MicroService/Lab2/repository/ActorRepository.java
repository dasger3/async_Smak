package Volos.MicroService.Lab2.repository;


import Volos.MicroService.Lab2.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

}
