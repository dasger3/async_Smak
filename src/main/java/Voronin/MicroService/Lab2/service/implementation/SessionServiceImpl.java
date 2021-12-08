package Voronin.MicroService.Lab2.service.implementation;

import Voronin.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Voronin.MicroService.Lab2.exception.ObjectNotFoundException;
import Voronin.MicroService.Lab2.model.Session;
import Voronin.MicroService.Lab2.model.Exam;
import Voronin.MicroService.Lab2.model.Student;
import Voronin.MicroService.Lab2.model.enums.TypeOfFaculty;
import Voronin.MicroService.Lab2.model.responce.StatisticsResponse;
import Voronin.MicroService.Lab2.repository.SessionRepository;
import Voronin.MicroService.Lab2.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public List<Session> getAllSessions () {
        return new ArrayList<>(sessionRepository.findAll());
    }

    @Override
    public Session getSessionById (Long id) {
        Optional<Session> Session = sessionRepository.findById(id);
        if(Session.isEmpty())
            throw new ObjectNotFoundException(Session.class.getName(),id);
        return Session.get();
    }

    @Override
    public void saveSession(Session session) {
        if(sessionRepository.findAll().contains(session))
            throw new ObjectAlreadyExistsException(Session.class.getName(), session.getSessionId());
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long id) {
        if(sessionRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Session.class.getName(),id);
        sessionRepository.deleteById(id);
    }

    @Override
    public void updateSession(Long id, Session session) {
        if(sessionRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Session.class.getName(),id);
        session.setSessionId(id);
        sessionRepository.save(session);
    }

    @Override
    public void addStudentToSession(Student student, Long id) {
        Optional<Session> Session = sessionRepository.findById(id);
        if(Session.isEmpty())
            throw new ObjectNotFoundException(Session.class.getName(),id);

        Session.get().getStudentList().add(student);

        sessionRepository.save(Session.get());
    }

    @Override
    public void addExamToSession(Exam exam, Long id) {
        Optional<Session> Session = sessionRepository.findById(id);
        if(Session.isEmpty())
            throw new ObjectNotFoundException(Session.class.getName(),id);

        Session.get().getExamList().add(exam);

        sessionRepository.save(Session.get());
    }

    @Override
    public StatisticsResponse getStatistics() {
        List<Session> sessionList = sessionRepository.findAll();
        if(sessionList.isEmpty())
            throw new ObjectNotFoundException(Session.class.getName());

        StatisticsResponse statisticsResponse = new StatisticsResponse();

        statisticsResponse.setCountOfClasses(sessionList.size());

        statisticsResponse.setCountOfFirstCategory(
                sessionList.stream().filter(Session ->
                        Session.getTypeOfFaculty().equals(TypeOfFaculty.TEF)).
                        toArray().length);

        statisticsResponse.setCountOfSecondCategory(
                sessionList.stream().filter(Session ->
                        Session.getTypeOfFaculty().equals(TypeOfFaculty.FIOT))
                        .toArray().length);

        statisticsResponse.setCountOfThirdCategory(
                sessionList.stream().filter(Session ->
                        Session.getTypeOfFaculty().equals(TypeOfFaculty.IPSA))
                        .toArray().length);


        return  statisticsResponse;
    }
}
