package Voronin.MicroService.Lab2.service;

import Voronin.MicroService.Lab2.model.Exam;

import java.util.List;

public interface ExamService {

    List<Exam> getAllExams ();
    Exam getExamById (Long id);
    void saveExam (Exam exam);
    void deleteExam (Long id);
    void updateExam (Long id, Exam exam);
}
