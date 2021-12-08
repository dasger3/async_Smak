package Voronin.MicroService.Lab2.service;

import Voronin.MicroService.Lab2.model.Session;
import Voronin.MicroService.Lab2.model.Exam;
import Voronin.MicroService.Lab2.model.Student;
import Voronin.MicroService.Lab2.model.responce.StatisticsResponse;

import java.util.List;

public interface SessionService {

    List<Session> getAllSessions ();
    Session getSessionById (Long id);
    void saveSession (Session session);
    void deleteSession (Long id);
    void updateSession (Long id, Session session);

    void addStudentToSession(Student student, Long idSession);
    void addExamToSession(Exam exam, Long idSession);

    StatisticsResponse getStatistics();
}
