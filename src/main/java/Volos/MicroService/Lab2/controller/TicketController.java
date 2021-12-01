package Volos.MicroService.Lab2.controller;

import Volos.MicroService.Lab2.model.Ticket;
import Volos.MicroService.Lab2.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> findAllTicket () {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findTicketById (@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<List<Ticket>> saveTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Ticket>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Ticket>> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
