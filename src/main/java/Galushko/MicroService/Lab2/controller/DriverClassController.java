package Galushko.MicroService.Lab2.controller;

import Galushko.MicroService.Lab2.model.Student;
import Galushko.MicroService.Lab2.model.DriverClass;
import Galushko.MicroService.Lab2.model.Exam;
import Galushko.MicroService.Lab2.model.responce.StatisticsResponse;
import Galushko.MicroService.Lab2.service.DriverClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("driver-class")
public class DriverClassController {

    private final DriverClassService driverClassService;

    @GetMapping("/")
    public ResponseEntity<List<DriverClass>> findAllDriverClass () {
        return ResponseEntity.ok(driverClassService.getAllDriverClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverClass> findDriverClassById (@PathVariable Long id) {
        return ResponseEntity.ok(driverClassService.getDriverClassById(id));
    }

    @PostMapping
    public ResponseEntity<List<DriverClass>> saveDriverClass(@RequestBody DriverClass DriverClass) {
        driverClassService.saveDriverClass(DriverClass);
        return ResponseEntity.ok(driverClassService.getAllDriverClasses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<DriverClass>> deleteDriverClass(@PathVariable Long id) {
        driverClassService.deleteDriverClass(id);
        return ResponseEntity.ok(driverClassService.getAllDriverClasses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<DriverClass>> updateDriverClass(@PathVariable Long id, @RequestBody DriverClass driverClass) {
        driverClassService.updateDriverClass(id, driverClass);
        return ResponseEntity.ok(driverClassService.getAllDriverClasses());
    }

    @PostMapping("addStudent/{id}")
    public ResponseEntity<List<DriverClass>> addStudentToDriverClass(@PathVariable Long id, @RequestBody Student student) {
        driverClassService.addStudentToDriverClass(student, id);
        return ResponseEntity.ok(driverClassService.getAllDriverClasses());
    }

    @PostMapping("addExam/{id}")
    public ResponseEntity<List<DriverClass>> addExamToDriverClass(@PathVariable Long id, @RequestBody Exam exam) {
        driverClassService.addExamToDriverClass(exam, id);
        return ResponseEntity.ok(driverClassService.getAllDriverClasses());
    }

    @GetMapping("statistics/")
    public ResponseEntity<StatisticsResponse> getStatistics() {
        return ResponseEntity.ok(driverClassService.getStatistics());
    }
}
