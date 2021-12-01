package Volos.MicroService.Lab2.service.implementation;

import Volos.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Volos.MicroService.Lab2.exception.ObjectNotFoundException;
import Volos.MicroService.Lab2.model.Actor;
import Volos.MicroService.Lab2.repository.ActorRepository;
import Volos.MicroService.Lab2.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public List<Actor> getAllActors () {
        return new ArrayList<>(actorRepository.findAll());
    }

    @Override
    public Actor getActorById (Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isEmpty())
            throw new ObjectNotFoundException(Actor.class.getName(),id);
        return actor.get();
    }

    @Override
    public void saveActor(Actor actor) {
        if(actorRepository.findAll().contains(actor))
            throw new ObjectAlreadyExistsException(Actor.class.getName(),actor.getActorId());
        actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        if(actorRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Actor.class.getName(),id);
        actorRepository.deleteById(id);
    }

    @Override
    public void updateActor(Long id, Actor actor) {
        if(actorRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Actor.class.getName(),id);
        actor.setActorId(id);
        actorRepository.save(actor);
    }

}
