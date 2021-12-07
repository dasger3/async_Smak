package Galushko.MicroService.Lab2.service.implementation;

import Galushko.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Galushko.MicroService.Lab2.exception.ObjectNotFoundException;
import Galushko.MicroService.Lab2.model.Exam;
import Galushko.MicroService.Lab2.repository.ExamRepository;
import Galushko.MicroService.Lab2.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams () {
        return new ArrayList<>(examRepository.findAll());
    }

    @Override
    public Exam getExamById (Long id) {
        Optional<Exam> Exam = examRepository.findById(id);
        if(Exam.isEmpty())
            throw new ObjectNotFoundException(Exam.class.getName(),id);
        return Exam.get();
    }

    @Override
    public void saveExam(Exam exam) {
        if(examRepository.findAll().contains(exam))
            throw new ObjectAlreadyExistsException(Exam.class.getName(), exam.getExamId());
        examRepository.save(exam);
    }

    @Override
    public void deleteExam(Long id) {
        if(examRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Exam.class.getName(),id);
        examRepository.deleteById(id);
    }

    @Override
    public void updateExam(Long id, Exam exam) {
        if(examRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Exam.class.getName(),id);
        exam.setExamId(id);
        examRepository.save(exam);
    }
}
