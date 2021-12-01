package Volos.MicroService.Lab2.service;

import Volos.MicroService.Lab2.model.Concert;

import java.util.List;

public interface ConcertService {

    List<Concert> getAllConcerts ();
    Concert getConcertById (Long id);
    void saveConcert (Concert concert);
    void deleteConcert (Long id);
    void updateConcert (Long id, Concert concert);
}
