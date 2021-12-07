package Galushko.MicroService.Lab2.repository;

import Galushko.MicroService.Lab2.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {


}
