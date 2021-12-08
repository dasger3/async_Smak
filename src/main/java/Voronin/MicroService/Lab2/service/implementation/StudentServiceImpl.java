package Voronin.MicroService.Lab2.service.implementation;

import Voronin.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Voronin.MicroService.Lab2.exception.ObjectNotFoundException;
import Voronin.MicroService.Lab2.model.Student;
import Voronin.MicroService.Lab2.repository.StudentRepository;
import Voronin.MicroService.Lab2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents () {
        return new ArrayList<>(studentRepository.findAll());
    }

    @Override
    public Student getStudentById (Long id) {
        Optional<Student> Student = studentRepository.findById(id);
        if(Student.isEmpty())
            throw new ObjectNotFoundException(Voronin.MicroService.Lab2.model.Student.class.getName(),id);
        return Student.get();
    }

    @Override
    public void saveStudent(Student student) {
        if(studentRepository.findAll().contains(student))
            throw new ObjectAlreadyExistsException(Student.class.getName(), student.getStudentId());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if(studentRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Student.class.getName(),id);
        studentRepository.deleteById(id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        if(studentRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(Student.class.getName(),id);
        student.setStudentId(id);
        studentRepository.save(student);
    }

}
