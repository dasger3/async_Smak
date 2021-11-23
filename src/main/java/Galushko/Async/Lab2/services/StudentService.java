package Galushko.Async.Lab2.services;

import Galushko.Async.Lab2.entities.Exam;
import Galushko.Async.Lab2.entities.Exam.Type;
import Galushko.Async.Lab2.entities.Student;
import Galushko.Async.Lab2.repositories.StudentRepository;

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

    //4
    public Optional<Student> findWithMaxExamAverage() {
        double maxAverage = studentRepository.findAll().stream()
                .map(student -> student.getExams().stream()
                        .mapToDouble(Exam::getScore)
                        .average())
                .filter(OptionalDouble::isPresent)
                .mapToDouble(OptionalDouble::getAsDouble).max()
                .getAsDouble();

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .mapToDouble(Exam::getScore)
                        .average().getAsDouble() == maxAverage)
                .findFirst();
    }

    //6
    public List<Student> findWithMathExamAndOneMore() {
        return studentRepository.findAll()
                .stream()
                .filter(student ->
                        student.getExams().size() == 2 &&
                                student.getExams().stream()
                                        .anyMatch(exam -> exam.getType() == Type.MATH))
                .collect(Collectors.toList());
    }

    //9
    public OptionalDouble findAverageMathExamResult() {
        return studentRepository.findAll()
                .stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == Type.MATH)
                .mapToDouble(Exam::getScore)
                .average();
    }

    //11
    public List<Student> findRatingMoreThanAverageAndPassMath() {

        double average = studentRepository.findAll().stream()
                .mapToDouble(Student::getRating).average().getAsDouble();

        return studentRepository.findAll().stream()
                .filter(student -> student.getExams().stream()
                        .anyMatch(exam -> exam.getType() == Type.MATH) && student.getRating() > average)
                .collect(Collectors.toList());
    }

    //14
    public List<String> findAverageWithName() {
        return studentRepository.findAll().stream()
                .map(student ->  student.getName() + " " +
                        student.getExams().stream()
                                .mapToDouble(Exam::getScore).average())
                .collect(Collectors.toList());
    }
}
