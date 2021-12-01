package Volos.MicroService.Lab2.controller;

import Volos.MicroService.Lab2.model.Concert;
import Volos.MicroService.Lab2.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("concert")
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/")
    public ResponseEntity<List<Concert>> findAllConcert () {
        return ResponseEntity.ok(concertService.getAllConcerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concert> findConcertById (@PathVariable Long id) {
        return ResponseEntity.ok(concertService.getConcertById(id));
    }

    @PostMapping
    public ResponseEntity<List<Concert>> saveConcert(@RequestBody Concert Concert) {
        concertService.saveConcert(Concert);
        return ResponseEntity.ok(concertService.getAllConcerts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Concert>> deleteConcert(@PathVariable Long id) {
        concertService.deleteConcert(id);
        return ResponseEntity.ok(concertService.getAllConcerts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Concert>> updateConcert(@PathVariable Long id, @RequestBody Concert concert) {
        concertService.updateConcert(id, concert);
        return ResponseEntity.ok(concertService.getAllConcerts());
    }
}
