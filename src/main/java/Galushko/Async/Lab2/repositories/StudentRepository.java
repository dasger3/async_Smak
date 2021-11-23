package Galushko.Async.Lab2.repositories;

import Galushko.Async.Lab2.entities.Exam;
import Galushko.Async.Lab2.entities.Exam.Type;
import Galushko.Async.Lab2.entities.Student;

import java.util.Arrays;
import java.util.List;

public class StudentRepository {

  private List<Student> students = getStudents();

  private static List<Student> getStudents() {
    return Arrays.asList(
        Student.builder()
            .name("1")
            .rating(10)
            .exams(Arrays.asList(Exam.of(Type.ENGLISH, 181)))
            .build(),
        Student.builder()
            .name("2")
            .rating(11)
            .exams(Arrays.asList(Exam.of(Type.ENGLISH, 182),
                Exam.of(Type.MATH, 190)))
            .build(),
        Student.builder()
            .name("3")
            .rating(11)
            .exams(Arrays.asList(Exam.of(Type.ENGLISH, 183),
                Exam.of(Type.MATH, 190)))
            .build(),
        Student.builder()
            .name("4")
            .rating(11)
            .exams(Arrays.asList())
            .build()
    );
  }


  public List<Student> findAll(){
    return students;
  }

}
