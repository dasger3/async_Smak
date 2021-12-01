package Volos.MicroService.Lab2.controller;

import Volos.MicroService.Lab2.model.Actor;
import Volos.MicroService.Lab2.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("actor")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/")
    public ResponseEntity<List<Actor>> findAllActor () {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> findActorById (@PathVariable Long id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @PostMapping
    public ResponseEntity<List<Actor>> saveActor(@RequestBody Actor Actor) {
        actorService.saveActor(Actor);
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Actor>> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Actor>> updateActor(@PathVariable Long id, @RequestBody Actor Actor) {
        actorService.updateActor(id, Actor);
        return ResponseEntity.ok(actorService.getAllActors());
    }
}
