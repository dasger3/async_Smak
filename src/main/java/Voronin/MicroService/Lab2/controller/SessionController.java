package Voronin.MicroService.Lab2.controller;

import Voronin.MicroService.Lab2.model.Session;
import Voronin.MicroService.Lab2.model.Exam;
import Voronin.MicroService.Lab2.model.Student;
import Voronin.MicroService.Lab2.model.responce.StatisticsResponse;
import Voronin.MicroService.Lab2.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/")
    public ResponseEntity<List<Session>> findAllSession () {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> findSessionById (@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }

    @PostMapping
    public ResponseEntity<List<Session>> saveSession(@RequestBody Session Session) {
        sessionService.saveSession(Session);
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Session>> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Session>> updateSession(@PathVariable Long id, @RequestBody Session session) {
        sessionService.updateSession(id, session);
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @PostMapping("addStudent/{id}")
    public ResponseEntity<List<Session>> addStudentToSession(@PathVariable Long id, @RequestBody Student student) {
        sessionService.addStudentToSession(student, id);
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @PostMapping("addExam/{id}")
    public ResponseEntity<List<Session>> addExamToSession(@PathVariable Long id, @RequestBody Exam exam) {
        sessionService.addExamToSession(exam, id);
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @GetMapping("statistics/")
    public ResponseEntity<StatisticsResponse> getStatistics() {
        return ResponseEntity.ok(sessionService.getStatistics());
    }
}
