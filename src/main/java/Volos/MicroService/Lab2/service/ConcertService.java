package Volos.MicroService.Lab2.service;

import Volos.MicroService.Lab2.model.Actor;
import Volos.MicroService.Lab2.model.Concert;
import Volos.MicroService.Lab2.model.Ticket;
import Volos.MicroService.Lab2.model.responce.StatisticsResponse;

import java.util.List;

public interface ConcertService {

    List<Concert> getAllConcerts ();
    Concert getConcertById (Long id);
    void saveConcert (Concert concert);
    void deleteConcert (Long id);
    void updateConcert (Long id, Concert concert);

    void addActorToConcert(Actor actor, Long idConcert);
    void addTicketToConcert(Ticket ticket, Long idConcert);

    StatisticsResponse getStatistics(Long id);
}
