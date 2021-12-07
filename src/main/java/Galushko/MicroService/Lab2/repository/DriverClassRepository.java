package Galushko.MicroService.Lab2.repository;

import Galushko.MicroService.Lab2.model.DriverClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverClassRepository extends JpaRepository<DriverClass, Long> {

}
