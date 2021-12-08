package Voronin.MicroService.Lab2.model;

import Voronin.MicroService.Lab2.model.enums.TypeOfFaculty;
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
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private String name;

    private Date date;

    @Enumerated(EnumType.STRING)
    private TypeOfFaculty typeOfFaculty;

    @OneToMany
    private List<Student> studentList;

    @OneToMany
    private List<Exam> examList;
}
