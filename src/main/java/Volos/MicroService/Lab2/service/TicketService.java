package Volos.MicroService.Lab2.service;

import Volos.MicroService.Lab2.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets ();
    Ticket getTicketById (Long id);
    void saveTicket (Ticket ticket);
    void deleteTicket (Long id);
    void updateTicket (Long id, Ticket ticket);
}
