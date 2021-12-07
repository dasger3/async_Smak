package Galushko.MicroService.Lab2.service;

import Galushko.MicroService.Lab2.model.Student;
import Galushko.MicroService.Lab2.model.DriverClass;
import Galushko.MicroService.Lab2.model.Exam;
import Galushko.MicroService.Lab2.model.responce.StatisticsResponse;

import java.util.List;

public interface DriverClassService {

    List<DriverClass> getAllDriverClasses ();
    DriverClass getDriverClassById (Long id);
    void saveDriverClass (DriverClass driverClass);
    void deleteDriverClass (Long id);
    void updateDriverClass (Long id, DriverClass driverClass);

    void addStudentToDriverClass(Student student, Long idDriverClass);
    void addExamToDriverClass(Exam exam, Long idDriverClass);

    StatisticsResponse getStatistics();
}
