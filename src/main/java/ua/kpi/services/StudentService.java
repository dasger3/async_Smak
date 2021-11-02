package ua.kpi.services;

import java.util.*;
import java.util.stream.Collectors;

import ua.kpi.entities.Exam;
import ua.kpi.entities.Exam.Type;
import ua.kpi.entities.Student;
import ua.kpi.repositories.StudentRepository;

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

    //2
    public Optional<Student> findFirstWithoutMath() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .noneMatch(exam -> exam.getType() == Type.MATH))
                .findFirst();
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

    //5
    public List<Student> findWithTwoExam() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().size() == 2)
                .collect(Collectors.toList());
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

    //9
    public OptionalDouble findAverageMathExamResult() {
        return studentRepository.findAll()
                .stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == Type.MATH)
                .mapToDouble(Exam::getScore)
                .average();
    }

    //10
    public List<Student> findMathExamResultMoreThenAverageAndHaveEnglish() {

        OptionalDouble average = studentRepository.findAll().stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType() == Type.MATH)
                .mapToDouble(Exam::getScore)
                .average();

        if (!average.isPresent()) return Collections.emptyList();

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .anyMatch(exam -> exam.getType() == Type.MATH) &&
                        student.getExams().stream()
                                .anyMatch(exam -> exam.getType() == Type.MATH &&
                                        exam.getScore() > average.getAsDouble()))
                .collect(Collectors.toList());
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

    //14
    public List<String> findAverageWithName() {
        return studentRepository.findAll().stream()
                .map(student ->  student.getName() + " " +
                        student.getExams().stream()
                                .mapToDouble(Exam::getScore).average())
                .collect(Collectors.toList());
    }

    //15
    public List<String> findSumWithNameAndRating() {
        return studentRepository.findAll().stream()
                .map(student ->  student.getExams().stream()
                        .mapToDouble(Exam::getScore).sum() + " " +  student.getRating() + " " +
                        student.getName())
                .collect(Collectors.toList());
    }
}
