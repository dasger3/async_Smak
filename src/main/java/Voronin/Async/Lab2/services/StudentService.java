package Voronin.Async.Lab2.services;


import Voronin.Async.Lab2.entities.Exam;
import Voronin.Async.Lab2.entities.Exam.Type;
import Voronin.Async.Lab2.entities.Student;
import Voronin.Async.Lab2.repositories.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Optional<Student> findWithMaxExam(Type type) {
        List<Student> students = studentRepository.findAll();
        OptionalDouble maxExam = students.stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == type)
                .mapToDouble(Exam::getScore)
                .max();

        if (!maxExam.isPresent())
            return Optional.empty();

        Exam exam = Exam.of(type, maxExam.getAsDouble());
        return students.stream()
                .filter(student -> student.getExams().contains(exam))
                .findFirst();
    }

    public List<Student> findWithEnoughExam(Type examType, double passRate) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .anyMatch(exam -> exam.getType() == examType &&
                                exam.getScore() >= passRate))
                .collect(Collectors.toList());
    }

    //3
    public List<Student> findWithExamSumLessThan300() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .mapToDouble(Exam::getScore)
                        .sum() < 300)
                .collect(Collectors.toList());
    }

    //5
    public List<Student> findWithTwoExam() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().size() == 2)
                .collect(Collectors.toList());
    }

    //7
    public List<Student> findWithRatingMoreThan11AndEnglishExam() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getRating() > 10 &&
                        student.getExams().stream()
                                .anyMatch(exam -> exam.getType() == Type.ENGLISH))
                .collect(Collectors.toList());
    }

    //8
    public List<Student> findWithExamsMoreThan180Each() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .anyMatch(exam -> exam.getType() == Type.ENGLISH) &&
                        student.getExams().stream()
                                .anyMatch(exam -> exam.getType() == Type.MATH) &&
                        student.getExams().stream()
                                .anyMatch(exam -> exam.getScore() > 180))
                .collect(Collectors.toList());
    }

    //13
    public Optional<Student> findWithMaxEnglishExam() {
        List<Student> students = studentRepository.findAll();
        OptionalDouble maxExam = students.stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == Type.ENGLISH)
                .mapToDouble(Exam::getScore)
                .max();

        if (!maxExam.isPresent())
            return Optional.empty();

        Exam exam = Exam.of(Type.ENGLISH, maxExam.getAsDouble());
        return students.stream()
                .filter(student -> student.getExams().contains(exam))
                .findFirst();
    }
}
