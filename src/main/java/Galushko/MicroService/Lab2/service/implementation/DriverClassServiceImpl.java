package Galushko.MicroService.Lab2.service.implementation;

import Galushko.MicroService.Lab2.exception.ObjectAlreadyExistsException;
import Galushko.MicroService.Lab2.exception.ObjectNotFoundException;
import Galushko.MicroService.Lab2.model.Student;
import Galushko.MicroService.Lab2.model.DriverClass;
import Galushko.MicroService.Lab2.model.Exam;
import Galushko.MicroService.Lab2.model.enums.TypeOfCategory;
import Galushko.MicroService.Lab2.model.enums.TypeOfMark;
import Galushko.MicroService.Lab2.model.responce.StatisticsResponse;
import Galushko.MicroService.Lab2.repository.DriverClassRepository;
import Galushko.MicroService.Lab2.service.DriverClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverClassServiceImpl implements DriverClassService {

    private final DriverClassRepository driverClassRepository;

    @Override
    public List<DriverClass> getAllDriverClasses () {
        return new ArrayList<>(driverClassRepository.findAll());
    }

    @Override
    public DriverClass getDriverClassById (Long id) {
        Optional<DriverClass> DriverClass = driverClassRepository.findById(id);
        if(DriverClass.isEmpty())
            throw new ObjectNotFoundException(DriverClass.class.getName(),id);
        return DriverClass.get();
    }

    @Override
    public void saveDriverClass(DriverClass driverClass) {
        if(driverClassRepository.findAll().contains(driverClass))
            throw new ObjectAlreadyExistsException(DriverClass.class.getName(), driverClass.getIdDriverClass());
        driverClassRepository.save(driverClass);
    }

    @Override
    public void deleteDriverClass(Long id) {
        if(driverClassRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(DriverClass.class.getName(),id);
        driverClassRepository.deleteById(id);
    }

    @Override
    public void updateDriverClass(Long id, DriverClass driverClass) {
        if(driverClassRepository.findById(id).isEmpty())
            throw new ObjectNotFoundException(DriverClass.class.getName(),id);
        driverClass.setIdDriverClass(id);
        driverClassRepository.save(driverClass);
    }

    @Override
    public void addStudentToDriverClass(Student student, Long id) {
        Optional<DriverClass> DriverClass = driverClassRepository.findById(id);
        if(DriverClass.isEmpty())
            throw new ObjectNotFoundException(DriverClass.class.getName(),id);

        DriverClass.get().getStudentList().add(student);

        driverClassRepository.save(DriverClass.get());
    }

    @Override
    public void addExamToDriverClass(Exam exam, Long id) {
        Optional<DriverClass> DriverClass = driverClassRepository.findById(id);
        if(DriverClass.isEmpty())
            throw new ObjectNotFoundException(DriverClass.class.getName(),id);

        DriverClass.get().getExamList().add(exam);

        driverClassRepository.save(DriverClass.get());
    }

    @Override
    public StatisticsResponse getStatistics() {
        List<DriverClass> driverClassList = driverClassRepository.findAll();
        if(driverClassList.isEmpty())
            throw new ObjectNotFoundException(DriverClass.class.getName());

        StatisticsResponse statisticsResponse = new StatisticsResponse();

        statisticsResponse.setCountOfClasses(driverClassList.size());

        statisticsResponse.setCountOfFirstCategory(
                driverClassList.stream().filter(DriverClass ->
                        DriverClass.getTypeOfCategory().equals(TypeOfCategory.A))
                        .toArray().length);

        statisticsResponse.setCountOfSecondCategory(
                driverClassList.stream().filter(DriverClass ->
                        DriverClass.getTypeOfCategory().equals(TypeOfCategory.B))
                        .toArray().length);

        statisticsResponse.setCountOfThirdCategory(
                driverClassList.stream().filter(DriverClass ->
                        DriverClass.getTypeOfCategory().equals(TypeOfCategory.C))
                        .toArray().length);


        return  statisticsResponse;
    }
}
