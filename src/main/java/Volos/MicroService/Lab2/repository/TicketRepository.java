package Volos.MicroService.Lab2.repository;

import Volos.MicroService.Lab2.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
