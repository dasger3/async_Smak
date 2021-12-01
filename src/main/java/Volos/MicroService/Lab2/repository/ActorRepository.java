package Volos.MicroService.Lab2.repository;


import Volos.MicroService.Lab2.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    /*List<AdministrativeTerritorialUnit> findAll();

    Optional<AdministrativeTerritorialUnit> findById (Long id);

    Optional<AdministrativeTerritorialUnit> findByTitle(String title);*/


//    Object save (AdministrativeTerritorialUnit atu);
//
//    void updateATU (AdministrativeTerritorialUnit atu);
//
//    void deleteATU (Long id);

}
