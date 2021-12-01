package Volos.MicroService.Lab2.service.implementation;

import Volos.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Volos.MicroService.Lab2.exception.ObjectNotFoundException;
import Volos.MicroService.Lab2.model.Concert;
import Volos.MicroService.Lab2.repository.ConcertRepository;
import Volos.MicroService.Lab2.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;

    @Override
    public List<Concert> getAllConcerts () {
        return new ArrayList<>(concertRepository.findAll());
    }

    @Override
    public Concert getConcertById (Long id) {
        Optional<Concert> concert = concertRepository.findById(id);
        if(concert.isEmpty())
            throw new ObjectNotFoundException(Concert.class.getName(),id);
        return concert.get();
    }

    @Override
    public void saveConcert(Concert concert) {
        if(concertRepository.findAll().contains(concert))
            throw new ObjectAlreadyExistsException(Concert.class.getName(),concert.getIdConcert());
        concertRepository.save(concert);
    }

    @Override
    public void deleteConcert(Long id) {
        if(concertRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Concert.class.getName(),id);
        concertRepository.deleteById(id);
    }

    @Override
    public void updateConcert(Long id, Concert concert) {
        if(concertRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Concert.class.getName(),id);
        concert.setIdConcert(id);
        concertRepository.save(concert);
    }
}
