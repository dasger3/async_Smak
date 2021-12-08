package Voronin.MicroService.Lab2.service;

import Voronin.MicroService.Lab2.model.Student;

import java.util.List;


public interface StudentService {

    List<Student> getAllStudents ();
    Student getStudentById (Long id);
    void saveStudent (Student atu);
    void deleteStudent (Long id);
    void updateStudent (Long id, Student student);

}
