package Galushko.MicroService.Lab2.model;

import Galushko.MicroService.Lab2.model.enums.TypeOfCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver_class")
public class DriverClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDriverClass;

    private String name;

    private Date dateStart;
    private Date dateEnd;

    private Double price;

    @Enumerated(EnumType.STRING)
    private TypeOfCategory typeOfCategory;

    @OneToMany
    private List<Student> studentList;

    @OneToMany
    private List<Exam> examList;
}
