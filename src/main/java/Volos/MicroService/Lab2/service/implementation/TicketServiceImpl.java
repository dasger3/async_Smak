package Volos.MicroService.Lab2.service.implementation;

import Volos.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Volos.MicroService.Lab2.exception.ObjectNotFoundException;
import Volos.MicroService.Lab2.model.Ticket;
import Volos.MicroService.Lab2.repository.TicketRepository;
import Volos.MicroService.Lab2.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets () {
        return new ArrayList<>(ticketRepository.findAll());
    }

    @Override
    public Ticket getTicketById (Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty())
            throw new ObjectNotFoundException(Ticket.class.getName(),id);
        return ticket.get();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        if(ticketRepository.findAll().contains(ticket))
            throw new ObjectAlreadyExistsException(Ticket.class.getName(),ticket.getTicketId());
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        if(ticketRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Ticket.class.getName(),id);
        ticketRepository.deleteById(id);
    }

    @Override
    public void updateTicket(Long id, Ticket ticket) {
        if(ticketRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Ticket.class.getName(),id);
        ticket.setTicketId(id);
        ticketRepository.save(ticket);
    }
}
