package Voronin.MicroService.Lab2.controller;

import Voronin.MicroService.Lab2.model.Exam;
import Voronin.MicroService.Lab2.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("exam")
public class ExamController {

    private final ExamService examService;

    @GetMapping("/")
    public ResponseEntity<List<Exam>> findAllExam () {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> findExamById (@PathVariable Long id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @PostMapping
    public ResponseEntity<List<Exam>> saveExam(@RequestBody Exam exam) {
        examService.saveExam(exam);
        return ResponseEntity.ok(examService.getAllExams());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Exam>> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.ok(examService.getAllExams());
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Exam>> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        examService.updateExam(id, exam);
        return ResponseEntity.ok(examService.getAllExams());
    }
}
