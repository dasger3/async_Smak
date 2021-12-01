package Volos.MicroService.Lab2.service;

import Volos.MicroService.Lab2.model.Actor;

import java.util.List;


public interface ActorService {

    List<Actor> getAllActors ();
    Actor getActorById (Long id);
    void saveActor (Actor atu);
    void deleteActor (Long id);
    void updateActor (Long id, Actor atu);

}
