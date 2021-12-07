package Galushko.MicroService.Lab2.model;

import Galushko.MicroService.Lab2.model.enums.TypeOfCategory;
import Galushko.MicroService.Lab2.model.enums.TypeOfMark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private Date date;

    private String place;

    private Double mark;

    @Enumerated(EnumType.STRING)
    private TypeOfMark typeOfMark;

    @OneToMany
    private List<Student> studentList;


}
